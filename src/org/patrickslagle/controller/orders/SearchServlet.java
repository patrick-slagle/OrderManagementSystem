package org.patrickslagle.controller.orders;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.patrickslagle.model.FormManager;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FormManager fm = new FormManager();
		String query = request.getParameter("query");
		
		if(fm.getOrderBySearch(query) == null) {
			System.out.println("null");
		} else {
			System.out.println("not null");
		}
		response.setContentType("application/json");
		String json = new Gson().toJson("{" + "name:" + name + "}");
		response.getWriter().write(json);
	}

}
