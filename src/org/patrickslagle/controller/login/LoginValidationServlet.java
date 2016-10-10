/**
 *<h1>validates the user's login against the database.</h1>
 */

package org.patrickslagle.controller.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.DatabaseManager;
import org.patrickslagle.model.LoginManager;

import java.sql.*;

/**
 * Servlet implementation class FormValidation
 */
@WebServlet("/FormValidation")
public class LoginValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidationServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		LoginManager lm = new LoginManager();
		RequestDispatcher rd = null;;
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		if (lm.validate(username, password)) {
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("invalid-user.jsp");
			rd.forward(request, response);
		}
	}
}
