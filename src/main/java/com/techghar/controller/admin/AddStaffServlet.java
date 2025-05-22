package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.dao.UserDAO;
import com.techghar.model.UserModel;
import com.techghar.utility.EncryptDecryptUtil;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.utility.SessionUtil;

;

/**
 * Servlet implementation class AddNewStaffServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/admin-staff-add", "/admin-staff-save" })
public class AddStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddStaffServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("activePage", "admin-staff");
		request.setAttribute("pageContent", "add-staff.jsp");
		request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

	}

	
	@Override
	/**
	 * Handles the HTTP POST request to add a new staff member.
	 *
	 * @param request  the HttpServletRequest containing staff form data
	 * @param response the HttpServletResponse used to forward or handle errors
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        // Create a new UserModel to store staff details
	        UserModel user = new UserModel();
	        UserDAO userDao = new UserDAO();

	        // Populate user details from request parameters
	        user.setFirstName(request.getParameter("firstName"));
	        user.setLastName(request.getParameter("lastName"));
	        user.setEmail(request.getParameter("email"));

	        // Encrypt the password before storing it
	        String password = request.getParameter("password");
	        String encPassword = EncryptDecryptUtil.encrypt(password);
	        user.setPassword(encPassword);

	        user.setPhone(request.getParameter("phone"));
	        user.setAddress(request.getParameter("address"));
	        user.setUsername(request.getParameter("username"));
	        user.setDob(Date.valueOf(request.getParameter("dob")));
	        user.setGender(request.getParameter("gender"));
	        user.setRole("STAFF"); // Set default role as STAFF

	        // Save the new staff member to the database
	        userDao.saveStaff(user);

	        // Set success message and attributes for the dashboard
	        request.setAttribute("message", "Staff Added Successfully");
	        request.setAttribute("activePage", "admin-staff");
	        request.setAttribute("pageContent", "staff.jsp");

	        // JavaScript to redirect to staff list after a short delay
	        String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
	        request.setAttribute("js", jsCode);

	        // Forward the request to the admin dashboard
	        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

	    } catch (Exception e) {
	        // Log the exception and prepare an error response
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Added Un Successful" + e.getMessage());
	        request.setAttribute("activePage", "admin-staff");
	        request.setAttribute("pageContent", "staff.jsp");

	        // JavaScript to redirect to staff list after a short delay
	        String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
	        request.setAttribute("js", jsCode);

	        // Forward the request back to the dashboard with error message
	        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

	        // Optionally, use the utility for consistent error handling
	        // ErrorHandlerUtilty.handleErrorAdmin(request, response, e.getMessage());
	    }
	}


}
