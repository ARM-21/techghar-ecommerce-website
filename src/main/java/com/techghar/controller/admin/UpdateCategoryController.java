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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			int id = Integer.parseInt(request.getParameter("categoryId"));
			String name =request.getParameter("updatedName");
	        CategoryDAO dao;
			dao = new CategoryDAO();
			dao.updateCategory(id,name);
	        response.sendRedirect("/view-categories");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorHandlerUtilty.handleErrorAdmin(request, response, e.getMessage());
			
		}
        
	}

}
