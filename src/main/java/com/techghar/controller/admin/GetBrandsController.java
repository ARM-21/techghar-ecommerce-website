package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.BrandDAO;
import com.techghar.DAO.CategoryDAO;
import com.techghar.DAO.ProductDAO;
import com.techghar.model.Brand;
import com.techghar.model.Category;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class GetBrandsController
 */
@WebServlet("/view-brands")
public class GetBrandsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BrandDAO brandDAO;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public GetBrandsController() throws ClassNotFoundException, SQLException {
    	 brandDAO = new BrandDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Brand> brands;
		try {
			
			brands = brandDAO.getAllBrands();
	        request.setAttribute("brandList", brands);
	        request.setAttribute("activePage", "view-brands");
	        request.setAttribute("pageContent", "manageBrands.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Server is facing issue while fetching category");
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
