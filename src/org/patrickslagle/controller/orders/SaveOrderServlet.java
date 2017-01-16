package org.patrickslagle.controller.orders;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.FormManager;
import org.patrickslagle.model.Order;
import org.patrickslagle.calendar.CalendarEventServlet;
/**
 * <h1>Servlet implementation for saving orders</h1>
 */
public class SaveOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = null;
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String dueDate = request.getParameter("due-date");
        String product = request.getParameter("product");
        String price = request.getParameter("price");
        String comments = request.getParameter("comments");

        String[] list = {firstName, lastName, phone, email, dueDate, product, comments};

        boolean fieldEmpty = false;
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals("") || list[i].equals("")) {
                fieldEmpty = true;
            }
        }
        if (fieldEmpty == true) {
            System.out.println("Empty field!");
            rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        } else {
            FormManager fm = new FormManager();
            fm.saveOrder(firstName, lastName, phone, email, dueDate, product, price, comments);
            List<Order> al = fm.getOrders();

            request.setAttribute("orders", al);
            
           response.sendRedirect("logout.do");
        }
    }

}
