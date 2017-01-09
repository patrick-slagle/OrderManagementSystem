package org.patrickslagle.controller.orders;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patrickslagle.model.FormManager;
import org.patrickslagle.model.Order;


/**
 *
 * <h1>Servlet for getting the data from all orders in the database</h1>
 *
 * <h3>Returns a list of orders as a session attribute</h3>
 *
 */
public class GetOrderServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @param request
     * @param response
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FormManager fm = new FormManager();
        List<Order> al = fm.getOrders();
                
        java.util.Date date = new java.util.Date();
        String fmtDate = new java.text.SimpleDateFormat("yyyy-MM-dd").format(date);

        HttpSession session = request.getSession();

        
        //Setting some date attributes for determining the date of JSP
        String parseHelper = fmtDate.substring(fmtDate.indexOf("-") + 1);
        String year = fmtDate.substring(0, fmtDate.indexOf("-"));
        String day = parseHelper.substring(parseHelper.indexOf("-") + 1);
        String month = parseHelper.substring(0, parseHelper.indexOf("-"));
        
        session.setAttribute("year", year);
        session.setAttribute("month", month);
        session.setAttribute("day", day);
        
        session.setAttribute("orders", al);
        
        ArrayList<Double> prices = new ArrayList<Double>();
        for (int i = 0; i < al.size(); i++) {
            String stringPrice = al.get(i).getPrice();
            double price = Double.parseDouble(stringPrice);
            prices.add(price);
        }
        double total = priceTotal(prices);
        String priceTotal = fmtTotal(total);
        session.setAttribute("priceTotal", priceTotal);
        
        

    }

    /**
     * Gives us the total of all order prices
     *
     * @param prices
     * @return
     */
    public double priceTotal(ArrayList<Double> prices) {
        double total = 0.0;
        for (int i = 0; i < prices.size(); i++) {
            total = prices.get(i) + total;
        }
        return total;
    }
    
    /**
     * properly formats the price total
     * 
     * @param total
     * @return 
     */
    private String fmtTotal(double total) {
        DecimalFormat dFormat = new DecimalFormat("#.00");
        return dFormat.format(total);
    } 

}
