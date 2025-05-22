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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			int id = Integer.parseInt(request.getParameter("brandId"));
			String updatedName = request.getParameter("updatedName");

			BrandDAO dao = new BrandDAO();
			;
			dao.updateBrand(id, updatedName);
			response.sendRedirect("view-brands");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occured While Updating brand");
		}
		;
	}

}
