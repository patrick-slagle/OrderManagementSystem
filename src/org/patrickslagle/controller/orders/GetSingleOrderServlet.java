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
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormManager fm = new FormManager();
		String index = request.getParameter("index");
		ArrayList al = (ArrayList) request.getSession().getAttribute("orders");		
                Order order = (Order) al.get(Integer.parseInt(index) - 1);
		request.getSession().setAttribute("selectedOrder", order);
	}

}
