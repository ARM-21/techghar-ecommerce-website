package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.UserDAO;
import com.techghar.model.UserModel;

/**
 * Servlet implementation class GetCustomerController
 */
@WebServlet("/admin-customers")
public class GetCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /**
     * Handles HTTP GET request to retrieve and display all customers for the admin panel.
     *
     * @param request  the HttpServletRequest containing parameters and attributes
     * @param response the HttpServletResponse used for forwarding
     * @throws ServletException if an error occurs during request processing
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Create UserDAO instance to access user data
            UserDAO userDAO = new UserDAO();
            
            // Retrieve list of all customers from the database
            List<UserModel> customers = userDAO.getAllCustomers();
            
            // Set the list of customers as a request attribute for JSP access
            request.setAttribute("customers", customers);
            
            // Set the active page for navigation highlighting
            request.setAttribute("activePage", "admin-customers");
            
            // Specify the JSP page to be included in the dashboard content area
            request.setAttribute("pageContent", "customers.jsp");
            
            // Forward the request and response to the main admin dashboard JSP
            request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            // If database access or class loading fails, wrap and rethrow as ServletException
            throw new ServletException("Cannot retrieve customers", e);
        }
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
