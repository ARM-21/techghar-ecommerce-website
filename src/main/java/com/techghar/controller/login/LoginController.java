package com.techghar.controller.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.DAO.LoginDAO;
import com.techghar.utility.CookieUtility;
import com.techghar.utility.SessionUtil;


/**
 * Servlet implementation class LoginContoller
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/login" })
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final LoginDAO loginDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		System.out.println("connecting");
		this.loginDAO = new LoginDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setAttribute("pageContent", "./login.jsp");
		request.getRequestDispatcher(request.getContextPath() + "/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * Handles POST requests for user login.
	 *
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String email = request.getParameter("email");
		String rememberMe = request.getParameter("rememberMe");
		String password = request.getParameter("password");
		Boolean loginStatus = loginDAO.logUserIN(request,email, password);
		if(rememberMe != null && rememberMe.equals("on")) {
			rememberUser(true, 48, loginStatus, email, resp, request);
		}
		else {
			rememberUser(true, 1, loginStatus, email, resp, request);
		}
		
	}
	
	
	private void rememberUser(Boolean isRemember,int time, Boolean loginStatus, String email, HttpServletResponse resp, HttpServletRequest request) throws ServletException, IOException {
		
		if (loginStatus != null && loginStatus) {
			if (email.equals("admin@gmail.com")) {
				CookieUtility.addCookie(resp, "role", "admin", time *  60 * 60);
				resp.sendRedirect( "/dashboard"); // Redirect to /dashboard
			} else {
				CookieUtility.addCookie(resp, "role", "user", time *  60 * 60);
				resp.sendRedirect("/home"); // Redirect to /home
			}
		} else {
			handleFailure(request, resp, loginStatus);
		}
	}
	
	/**
	 * Handles login failures by setting attributes and forwarding to the login
	 * page.
	 *
	 * @param request         HttpServletRequest object
	 * @param resp        HttpServletResponse object
	 * @param loginStatus Boolean indicating the login status
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	private void handleFailure(HttpServletRequest request, HttpServletResponse resp, Boolean loginStatus)
			throws ServletException, IOException {
		String errorMessage;
		if (loginStatus == null) {
			errorMessage = "Our server is under maintenance. Please try again later!";
		} else {
			errorMessage = "User credential mismatch. Please try again!";
		}
		request.setAttribute("message", errorMessage);
		request.setAttribute("pageContent","/WEB-INF/pages/login.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, resp);
	}


}
