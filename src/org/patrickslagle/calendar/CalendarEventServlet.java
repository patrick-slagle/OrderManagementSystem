package org.patrickslagle.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.EventAttachment;
import java.util.List;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.patrickslagle.model.Order;

/**
 * <h1>Create calendar events for each order that has been saved in the
 * database</h1>
 */
public class CalendarEventServlet extends HttpServlet {

    /**
     * Processes requests for GET method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //get the calendar
        Calendar service
                = CalendarAuthorization.getCalendarService();

        //get the orders objects
        ArrayList<Order> orders = (ArrayList) request.getSession().getAttribute("orders");

        //get the events
        Events events = service.events().list("primary").setPageToken(null).execute();
        List<Event> items = events.getItems();

        //Here I add images to the order objects if the events have them.
        //add each order as an event unless it is already an event
        Date startDate = new Date();
        Date endDate;
        DateFormat dateFormat;
        String startDateStr;
        String endDateStr;

        if (orders.size() > items.size()) {

            for (int i = items.size(); i < orders.size(); i++) {
                String date = "";
                try {
                    date = orders.get(i).getDueDate();
                    System.out.println(date);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                try {
                    startDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                endDate = new Date(startDate.getTime() + 86400000);
                endDateStr = new SimpleDateFormat().format(endDate);

                DateTime startDateTime = new DateTime(startDate);
                DateTime endDateTime = new DateTime(endDate);

                EventDateTime startEventDateTime = new EventDateTime().setDateTime(startDateTime);
                EventDateTime endEventDateTime = new EventDateTime().setDateTime(endDateTime);
                System.out.println(orders.get(i).getId());
                //create event, save to calendar
                Event event = new Event()
                        .setSummary(orders.get(i).getProduct() + " for " + orders.get(i).getFirstName())
                        .setDescription("A " + orders.get(i).getProduct() + " for "
                                + orders.get(i).getFirstName()
                                + " " + orders.get(i).getLastName())
                        .setStart(startEventDateTime)
                        .setEnd(endEventDateTime);
                try {
                    event = service.events().insert("primary", event).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                event.setId(String.valueOf(orders.get(i).getId()));
                try {
                    event = service.events().update("primary", event.getId(), event).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addOrderAttachments(items, orders);

            }
        }

        response.sendRedirect("home.jsp");
    }

    public void addOrderAttachments(List<Event> items, List<Order> orders) {
        for (int i = 0; i < items.size(); i++) {
            Event event = items.get(i);
            List<EventAttachment> attachments = event.getAttachments();

            if (attachments != null) {
                for (int j = 0; j < orders.size(); j++) {
                    event.setId(String.valueOf(orders.get(i).getId()));
                    if (orders.get(j).getId() == Integer.parseInt(event.getId())) {
                        for (int k = 0; k < attachments.size(); k++) {
                            orders.get(j).setImageTitle(attachments.get(k).getTitle());
                            orders.get(j).setImageUrl(attachments.get(k).getFileUrl());
                        }
                    }
                }
            }

        }
    }
}
