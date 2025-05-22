package com.techghar.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.LoginDAO;
import com.techghar.utility.CookieUtility;
import com.techghar.utility.SessionUtil;

/**
 * Servlet implementation class LoginController
 * Handles user login requests and manages session and cookies for authentication.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final LoginDAO loginDAO;

	/**
	 * Constructor initializes the LoginDAO instance.
	 */
	public LoginController() {
		System.out.println("connecting");
		this.loginDAO = new LoginDAO();
	}

	/**
	 * Handles GET requests to show the login page.
	 * Sets the login.jsp as the main content and forwards to home.jsp layout.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("pageContent", "./login.jsp");
		// Forward request to the main layout page to display login content
		request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for user login.
	 * Retrieves email, password, and rememberMe flag from request parameters.
	 * Validates login and manages user session and cookies accordingly.
	 *
	 * @param request  HttpServletRequest object
	 * @param resp     HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		// Extract user credentials and remember-me flag from the request
		String email = request.getParameter("email");
		String rememberMe = request.getParameter("rememberMe");
		String password = request.getParameter("password");

		// Attempt to log in the user with the provided credentials
		Boolean loginStatus = loginDAO.logUserIN(request, email, password);

		// Determine cookie expiration time based on rememberMe checkbox
		if (rememberMe != null && rememberMe.equals("on")) {
			rememberUser(true, 48, loginStatus, email, resp, request);
		} else {
			rememberUser(true, 1, loginStatus, email, resp, request);
		}
	}

	/**
	 * Manages user session and cookies after login attempt.
	 * Sets cookies based on user role and redirects accordingly.
	 *
	 * @param isRemember indicates if remember me option is selected
	 * @param time       cookie expiration time in hours
	 * @param loginStatus indicates if login succeeded
	 * @param email      user's email
	 * @param resp       HttpServletResponse object
	 * @param request    HttpServletRequest object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void rememberUser(Boolean isRemember, int time, Boolean loginStatus, String email, HttpServletResponse resp,
			HttpServletRequest request) throws ServletException, IOException {

		if (loginStatus != null && loginStatus) {
			// Retrieve user role from session
			System.out.println(SessionUtil.getAttribute(request, "role"));
			Object role = SessionUtil.getAttribute(request, "role");

			// If user is admin or staff, set role cookie and redirect to dashboard
			if (role.equals("admin") || role.equals("staff")) {
				if (role.equals("admin") || role.equals("staff")) {
					CookieUtility.addCookie(resp, "role", "admin", time * 60 * 60);
				} else {
					CookieUtility.addCookie(resp, "role", "staff", time * 60 * 60);
				}
				resp.sendRedirect("/dashboard"); // Redirect to /dashboard
			} else {
				// For normal users, set role cookie and redirect to home
				CookieUtility.addCookie(resp, "role", "user", time * 60 * 60);
				resp.sendRedirect("/home"); // Redirect to /home
			}
		} else {
			// Handle login failure by forwarding to login page with error message
			handleFailure(request, resp, loginStatus);
		}
	}

	/**
	 * Handles login failures by setting error messages and forwarding back to login page.
	 *
	 * @param request     HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleFailure(HttpServletRequest request, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			// Indicates a server issue preventing login
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			// Indicates user credential mismatch
			errorMessage = "User credential mismatch. Please try again!";
		}
		// Set error message and login page for the view
		request.setAttribute("message", errorMessage);
		request.setAttribute("pageContent", "/WEB-INF/pages/login.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, resp);
	}

}
