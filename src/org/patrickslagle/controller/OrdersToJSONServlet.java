package org.patrickslagle.controller;

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

	enum ParserState {
		READING_OBJECT, READING_ARRAY
	};

	private ArrayList<String> extractObjects(String array) throws ParseException {
		ParserState state = ParserState.READING_ARRAY;
		StringBuilder currentObject = null;
		ArrayList<String> result = new ArrayList<String>();
		int i = 0;
		int parenthesisBalance = 0;
		for (char c : array.toCharArray()) {
			switch (c) {
			case '{': {
				if (state == ParserState.READING_ARRAY) {
					state = ParserState.READING_OBJECT;
					currentObject = new StringBuilder();
				}
				parenthesisBalance++;
				currentObject.append(c);
				break;
			}
			case '}': {
				if (state == ParserState.READING_ARRAY) {
					throw new ParseException("unexpected '}' ", i);
				} else {
					currentObject.append(c);
					parenthesisBalance--;
					if (parenthesisBalance == 0) {
						state = ParserState.READING_ARRAY;
						result.add(currentObject.toString());
					}
				}
				break;
			}
			default: {
				if (state == ParserState.READING_OBJECT) {
					currentObject.append(c);
				}
			}
			}
			i++;
		}
		return result;
	}

	private String formatString(String s) {
		List list = Arrays.asList(s);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == "\"") {
				list.add(i, "\\");
			}
		}
		return list.toString();
	}

}
