package com.techghar.controller.aboutContact;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ContactServlet
 * Handles requests for the Contact page.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/contact"})
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Handles GET requests for the Contact page.
     * Sets attributes for page content and active page, then forwards to home.jsp.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set the path to the contact.jsp page for dynamic content loading
		request.setAttribute("pageContent", "./contact.jsp");

		// Indicate that the "contact" page is currently active (for navigation or UI highlighting)
		request.setAttribute("activePage", "contact");

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Delegate POST request handling to doGet to maintain consistent behavior
		doGet(request, response);
	}
}
