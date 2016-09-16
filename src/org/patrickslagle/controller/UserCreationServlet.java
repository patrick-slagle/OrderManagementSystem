package org.patrickslagle.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.patrickslagle.model.FormManager;

/**
 * Servlet implementation class FormHandlerServlet
 */
@WebServlet("/FormHandlerServlet")
public class UserCreationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserCreationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FormManager fm = new FormManager();
		RequestDispatcher rd;
		
		String firstName = request.getParameter("first-name");
		String lastName = request.getParameter("last-name");
		String email = request.getParameter("email");
		String pass1 = request.getParameter("password");
		String pass2 = request.getParameter("re-enter-pw");
		
		
		if(fm.testPassword(pass1, pass2)) {
			fm.createUser(firstName, lastName, email, pass1);
			rd = request.getRequestDispatcher("home.jsp");
			rd.forward(request, response);
		} else {
			rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

}
