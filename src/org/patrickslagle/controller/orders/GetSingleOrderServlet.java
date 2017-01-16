package org.patrickslagle.controller.orders;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.Order;


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
        if (searchIds == null) {
            Order selectedOrder = (Order) orders.get(Integer.parseInt(index) - 1);
            request.getSession().setAttribute("selectedOrder", selectedOrder);
        } else {

            for (int i = 0; i < orders.size(); i++) {
                for (int j = 0; j < searchIds.size(); j++) {
                    if (orders.get(i).getId() == searchIds.get(Integer.parseInt(index) - 1)) {
                        Order selectedOrder = (Order) orders.get(i);
                        request.getSession().setAttribute("selectedOrder", selectedOrder);
                        request.getSession().removeAttribute("searchIds");
                    }
                }
            }
        }
    }
}
