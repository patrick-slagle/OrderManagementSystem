package org.patrickslagle.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.json.JsonArray;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patrickslagle.model.FormManager;
import org.patrickslagle.model.Order;

import com.google.gson.Gson;

/**
 * Servlet implementation class GetOrderServlet
 */
@WebServlet("/GetOrderServlet")
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormManager fm = new FormManager();
		List<Order> al = fm.getOrders();
		HttpSession session = request.getSession();
		session.setAttribute("orders", al);

		ArrayList<Double> prices = new ArrayList<Double>();
		for (int i = 0; i < al.size(); i++) {
			double price = al.get(i).getPrice();
			prices.add(price);
		}
		double priceTotal = priceTotal(prices);
		request.setAttribute("priceTotal", priceTotal);

	}

	public double priceTotal(ArrayList<Double> prices) {
		double total = 0.0;
		for (int i = 0; i < prices.size(); i++) {
			total = prices.get(i) + total;
		}
		return total;
	}

}
