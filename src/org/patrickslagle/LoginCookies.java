package org.patrickslagle;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginCookies
 */
@WebServlet("/LoginCookies")
public class LoginCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginCookies() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		//get the username text
		String name = request.getParameter("username");
		//create a new cookie from the 
		//username
		Cookie cookie = new Cookie("username", name);

		//set cookie age
		cookie.setMaxAge(30 * 60);
		//place the cookie in the response
		response.addCookie(cookie);
		//set the view
		RequestDispatcher view = request.getRequestDispatcher("cookieresponse.jsp");
		view.forward(request, response);
	}

}