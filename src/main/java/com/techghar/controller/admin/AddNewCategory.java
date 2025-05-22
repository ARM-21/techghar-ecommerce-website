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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		try {
			String name = request.getParameter("categoryName");
			Category category = new Category();
			category.setName(name);
			CategoryDAO dao;
			dao = new CategoryDAO();
			dao.addCategory(category);
			response.sendRedirect("view-categories");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Cannot Add New Category");
		}
		

		 // adjust based on your page name
	}

}
