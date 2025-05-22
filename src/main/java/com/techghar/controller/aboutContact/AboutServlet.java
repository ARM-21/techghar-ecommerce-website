package com.techghar.controller.aboutContact;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutServlet
 * Handles requests for the About page.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/about" })
public class AboutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Handles GET requests for the About page.
     * Sets attributes for page content and active page, then forwards to home.jsp.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Set the path to the about.jsp page for dynamic content loading
		request.setAttribute("pageContent", "./about.jsp");

		// Indicate that the "about" page is currently active (for navigation or UI highlighting)
		request.setAttribute("activePage", "about");

		// Forward the request to the main home.jsp layout page
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
     * Handles POST requests by delegating to the doGet method.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Delegate POST request handling to doGet to maintain consistent behavior
		doGet(request, response);
	}
}
