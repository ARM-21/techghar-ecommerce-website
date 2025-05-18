package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.BrandDAO;
import com.techghar.model.Brand;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class AddNewBrandController
 */
@WebServlet("/add-brand")
public class AddNewBrandController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			try {
				 BrandDAO dao;
				dao = new BrandDAO();
				 String name = request.getParameter("brandName");
			        Brand brand = new Brand();
			        brand.setName(name);
			        dao.addBrand(brand);
			        response.sendRedirect("view-brands");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error While adding Brands");
			}
	       

	      
	}

}
