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
 * Servlet implementation class UpdateBrandsController
 */
@WebServlet("/update-brand")
public class UpdateBrandsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    try {
	        // Parse brand ID and updated brand name from request parameters
	        int id = Integer.parseInt(request.getParameter("brandId"));
	        String updatedName = request.getParameter("updatedName");

	        // Create DAO instance and update the brand
	        BrandDAO dao = new BrandDAO();
	        dao.updateBrand(id, updatedName);

	        // Redirect to brand listing page after successful update
	        response.sendRedirect("view-brands");
	    } catch (ClassNotFoundException | SQLException e) {
	        e.printStackTrace();
	        // Handle errors gracefully and show admin error page
	        ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occurred While Updating brand");
	    }
	}


}
