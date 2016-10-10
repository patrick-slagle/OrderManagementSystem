
/**
 * 
 * <h1>Servlet for user authentication across sessions.</h1> 
 * <h3>Essential class for security </h3>
 * 
 */

package org.patrickslagle.controller.commons;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AuthenticationServlet
 */
@WebServlet("/AuthenticationServlet")
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    /*
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String requestedPage = request.getParameter(Constants.REQUEST);
		if(session != null) {
			Boolean isAuthenticated = (Boolean) session.getValue(Constants.AUTHENTICATION);
			if(!isAuthenticated.booleanValue()) {
				unauthenticatedUser(response, requestedPage);
			}
		}
	}
*/
}
