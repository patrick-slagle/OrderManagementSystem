package org.patrickslagle.controller.orders;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import jdk.nashorn.internal.runtime.ParserException;

/**
 * Servlet implementation class OrdersToJSONServlet
 */
@WebServlet("/OrdersToJSONServlet")
public class OrdersToJSONServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrdersToJSONServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object orders = request.getSession().getAttribute("orders");
		String json = new Gson().toJson(orders);
		try {
			response.getWriter().write(json);
		} catch (Exception e) {
			System.out.println("problem parsing JSON");
		}
	}

}
