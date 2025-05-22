package com.techghar.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.UserDAO;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/user-profile", "/update-profile", "/update-admin-profile","/admin-profile" })
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			System.out.println("got request");
			System.out.println(request.getRequestURI());
			if (request.getRequestURI().equals("/update-profile") || request.getRequestURI().equals("/update-admin-profile") ) {
				UserDAO dao = new UserDAO();
				request.setAttribute("userDetails", dao.getUserById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
			}

			else  {
				UserDAO dao = new UserDAO();
				request.setAttribute("userDetails", dao.getUserById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageContent", "/WEB-INF/pages/profile.jsp");
			}

			if (request.getRequestURI().equals("/admin-profile") || request.getRequestURI().equals("/update-admin-profile")) {
				request.setAttribute("activePage", "admin-profile");
				request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
			} else {

				request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
			}
		} catch (IOException | ServletException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			
			if(request.getRequestURI().equals("/admin-profile") || request.getRequestURI().equals("/update-admin-profile")) {
				ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured While fetching user details");
			}
			else {
				ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured While fetching user details");
				e.printStackTrace();
			}
		
		}
	}

}
