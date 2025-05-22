package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.CategoryDAO;
import com.techghar.model.Category;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class AddNewCategory
 */
@WebServlet("/add-category")
public class AddNewCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddNewCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/**
	 * Handles the HTTP POST request to add a new category.
	 *
	 * @param request  the HttpServletRequest containing form data
	 * @param response the HttpServletResponse used to redirect or handle errors
	 * @throws IOException if an input or output error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

	    try {
	        // Retrieve the category name from the form parameter
	        String name = request.getParameter("categoryName");

	        // Create a new Category object and set its name
	        Category category = new Category();
	        category.setName(name);

	        // Create a new instance of CategoryDAO to interact with the database
	        CategoryDAO dao;
	        dao = new CategoryDAO();

	        // Add the new category to the database
	        dao.addCategory(category);

	        // Redirect the user to the categories list page after successful addition
	        response.sendRedirect("view-categories");

	    } catch (ClassNotFoundException | SQLException e) {
	        // Log the exception and delegate error handling to the utility method
	        e.printStackTrace();
	        ErrorHandlerUtilty.handleErrorAdmin(request, response, "Cannot Add New Category");
	    }
	}


}
