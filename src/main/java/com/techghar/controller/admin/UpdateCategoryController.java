package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.CategoryDAO;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class UpdateCategoryController
 */
@WebServlet("/update-category")
public class UpdateCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * Handles the HTTP POST request to update a category's name based on the provided ID.
	 *
	 * @param request  the HttpServletRequest containing the category ID and new name
	 * @param response the HttpServletResponse used to redirect or forward errors
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // TODO Auto-generated method stub

	    try {
	        // Retrieve the category ID from the request and parse it as an integer
	        int id = Integer.parseInt(request.getParameter("categoryId"));

	        // Retrieve the updated category name from the request
	        String name = request.getParameter("updatedName");

	        // Instantiate the DAO and update the category in the database
	        CategoryDAO dao;
	        dao = new CategoryDAO();
	        dao.updateCategory(id, name);

	        // Redirect to the category view page after successful update
	        response.sendRedirect("/view-categories");
	    } catch (ClassNotFoundException | SQLException e) {
	        // Handle exceptions such as JDBC driver not found or SQL errors
	        e.printStackTrace();

	        // Use a custom utility to forward to an error page with the message
	        ErrorHandlerUtilty.handleErrorAdmin(request, response, e.getMessage());
	    }
	}


}
