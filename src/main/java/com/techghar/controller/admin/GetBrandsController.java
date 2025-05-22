package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.BrandDAO;
import com.techghar.dao.CategoryDAO;
import com.techghar.dao.ProductDAO;
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
    /**
     * Handles the HTTP GET request to fetch and display all brands.
     *
     * @param request  the HttpServletRequest to retrieve data and set attributes
     * @param response the HttpServletResponse used to forward the request
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Declare list to hold brand objects fetched from database
        List<Brand> brands;
        try {
            // Retrieve all brands from database using brandDAO
            brands = brandDAO.getAllBrands();

            // Set the brand list as a request attribute for the JSP page
            request.setAttribute("brandList", brands);

            // Set the active page indicator for UI navigation highlighting
            request.setAttribute("activePage", "view-brands");

            // Set the content page to be included in the dashboard layout
            request.setAttribute("pageContent", "manageBrands.jsp");

            // Forward request and response to the admin dashboard JSP for rendering
            request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (SQLException e) {
            // Log the exception stack trace for debugging
            e.printStackTrace();

            // Handle error by forwarding to an error handler utility with a custom message
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
