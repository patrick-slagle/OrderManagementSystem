package org.patrickslagle.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.FormManager;

/**
 * Servlet implementation class SaveOrderServlet
 */
@WebServlet("/SaveOrderServlet")
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
	 *      response)
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
		String comments = request.getParameter("comments");

		String[] list = { firstName, lastName, phone, email, dueDate, product, comments };

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
			fm.saveOrder(firstName, lastName, phone, email, dueDate, product, comments);
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		}
	}

}
