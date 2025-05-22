package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.BrandDAO;
import com.techghar.model.Brand;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class AddNewBrandController
 */
@WebServlet("/add-brand")
public class AddNewBrandController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * Handles the HTTP POST request to add a new brand.
	 *
	 * @param request  the HttpServletRequest containing form data
	 * @param response the HttpServletResponse used to redirect or handle errors
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    try {
	        // Create a new instance of BrandDAO to interact with the database
	        BrandDAO dao;
	        dao = new BrandDAO();

	        // Retrieve the brand name from the form parameter
	        String name = request.getParameter("brandName");

	        // Create a new Brand object and set its name
	        Brand brand = new Brand();
	        brand.setName(name);

	        // Add the new brand to the database
	        dao.addBrand(brand);

	        // Redirect the user to the brand list page after successful addition
	        response.sendRedirect("view-brands");

	    } catch (ClassNotFoundException | SQLException e) {
	        // Log the exception and delegate error handling to the utility method
	        e.printStackTrace();
	        ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error While adding Brands");
	    }
	}


}
