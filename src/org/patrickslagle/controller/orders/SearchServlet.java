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

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/SearchServlet" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormManager fm = new FormManager();
		String query = request.getParameter("query");

		@SuppressWarnings("unchecked")
		ArrayList<Order> orders = (ArrayList<Order>) request.getSession().getAttribute("orders");
		Object resultOrders = fm.getOrdersBySearch(orders, query);

		if (resultOrders == null) {
			System.out.println("null");
		} else {
			response.setContentType("application/json");
			String json = new Gson().toJson(resultOrders);
			System.out.println(json);
			response.getWriter().write(json);
		}
	}

}