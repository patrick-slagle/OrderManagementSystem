package org.patrickslagle.calendar;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.client.util.DateTime;
import java.util.List;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.patrickslagle.model.Order;

/**
 * <h1>Create calendar events for each 
 * order that has been saved in the database</h1>
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

        //add each order as an event unless it is already an event
        Date startDate = new Date();
        Date endDate;
        DateFormat dateFormat;
        String startDateStr;
        String endDateStr;

        boolean match = false;
        for (int i = 0; i < orders.size(); i++) {
            for (int j = 0; j < items.size(); j++) {
                
                //match orders to events based on the ids
                if (String.valueOf(orders.get(i).getId()).equals(items.get(j).getId())) {
                    match = true;
                    System.out.println(match);
                } else {
                    match = false;
                    System.out.println(match);
                    System.out.println("Event id: " + items.get(j).getId() + " " + "order id: " + orders.get(i).getId());
                }
            }
            if (!match) {
                System.out.println("here");
                String date = orders.get(i).getDueDate();
                try {
                    startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                endDate = new Date(startDate.getTime() + 86400000);
                endDateStr = new SimpleDateFormat().format(endDate);

                DateTime startDateTime = new DateTime(startDate);
                DateTime endDateTime = new DateTime(endDate);

                EventDateTime startEventDateTime = new EventDateTime().setDateTime(startDateTime);
                EventDateTime endEventDateTime = new EventDateTime().setDateTime(endDateTime);
                
                //create event, save to calendar
                Event event = new Event()
                        .setSummary(orders.get(i).getProduct() + " for " + orders.get(i).getFirstName())
                        .setDescription("A " + orders.get(i).getProduct() + " for "
                                + orders.get(i).getFirstName()
                                + " " + orders.get(i).getLastName())
                        .setId(String.valueOf(orders.get(i).getId()))
                        .setStart(startEventDateTime)
                        .setEnd(endEventDateTime);
                try {
                    event = service.events().insert("primary", event).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
