package org.patrickslagle.controller.orders;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.FormManager;
import org.patrickslagle.model.Order;

import com.google.gson.Gson;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <h1>Servlet implementation for getting a single order</h1>
 */
public class GetSingleOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSingleOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<Order> orders = (ArrayList<Order>) request.getSession().getAttribute("orders");
        ArrayList<Integer> searchIds = (ArrayList) request.getSession().getAttribute("searchIds");
        String index = request.getParameter("index");
        System.out.println(searchIds == null);
        if (searchIds == null) {
            Order selectedOrder = (Order) orders.get(Integer.parseInt(index) - 1);
            System.out.println(selectedOrder.getFirstName());
            request.getSession().setAttribute("selectedOrder", selectedOrder);
        } else {
            System.out.println(index);

            for (int i = 0; i < orders.size(); i++) {
                for (int j = 0; j < searchIds.size(); j++) {
                    if (orders.get(i).getId() == searchIds.get(Integer.parseInt(index) - 1)) {
                        Order selectedOrder = (Order) orders.get(i);
                        System.out.println(selectedOrder.getId());
                        request.getSession().setAttribute("selectedOrder", selectedOrder);
                        request.getSession().removeAttribute("searchIds");
                    }
                }
            }

            /**
             *
             *
             *
             * for(int i = 0; i < searchIds.size(); i++) {
             * System.out.println(searchIds.get(i)); }
             *
             * //the id at index should be same as the correct order String
             * orderId = (String) searchIds.get(Integer.parseInt(index) - 1);
             * Order order = (Order) orders.get(Integer.parseInt(index) - 1);
             * request.getSession().setAttribute("selectedOrder", order);
             */
        }
    }
}

/**
 *
 * 1. find out a way to get the order id from the table click 2. use the order
 * id in the servlet to display the data back to the user
 *
 *
 * possible ways to get the order id:
 *
 * by row index?
 *
 *
 */
