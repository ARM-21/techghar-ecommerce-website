package com.techghar.controller.logout;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.utility.CookieUtility;
import com.techghar.utility.SessionUtil;

/**
 * Servlet implementation class LogoutController
 * Handles user logout by deleting relevant cookies and invalidating the session.
 */
@WebServlet("/logout")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Handles POST request for logging out the user.
     * Deletes the 'role' cookie, invalidates the HTTP session,
     * and redirects the user to the login page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("logginOut");

		// Delete the 'role' cookie to remove user role info from the browser
		CookieUtility.deleteCookie(response, "role");

		// Invalidate the current user session to clear session data
		SessionUtil.invalidateSession(request);

		// Redirect the user to the login page after logout
		response.sendRedirect(request.getContextPath() + "/login");
	}

}
