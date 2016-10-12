package org.patrickslagle.controller.commons;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * <h1>Servlet for user authentication across sessions.</h1>
 * <h3>Essential class for security </h3>
 *
 */
public class Authentication {

    public boolean authenticate(HttpSession session) {
        boolean isAuthenticated;
        if (session != null) {
            isAuthenticated = true;
        } else {
            isAuthenticated = false;
        }
        return isAuthenticated;
    }

}
