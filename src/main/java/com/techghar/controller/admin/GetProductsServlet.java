package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.ProductDAO;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class GetProductsServlet
 */
@WebServlet("/admin-products")
public class GetProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProductsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/**
	 * Handles HTTP GET request to retrieve and display products for the admin panel.
	 * Supports optional search by product name.
	 *
	 * @param request  the HttpServletRequest containing parameters and attributes
	 * @param response the HttpServletResponse used for forwarding
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        // Create ProductDAO instance to interact with product data
	        ProductDAO productDAO = new ProductDAO();
	        ArrayList<Product> products;

	        // Get the 'search' parameter from the request
	        String searchQuery = request.getParameter("search");
	        
	        // If search query is provided and not empty, fetch products matching the name
	        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
	            products = productDAO.searchProductsByName(searchQuery);
	            System.out.println(products.toString()); // Debug: print matching products
	        } else {
	            // Otherwise, fetch all products
	            products = productDAO.getAllProducts();
	        }
	        
	        // Set products list as a request attribute for the JSP page to access
	        request.setAttribute("products", products);
	        
	        // Set active page and content JSP to be rendered inside the admin dashboard
	        request.setAttribute("activePage", "admin-products");
	        request.setAttribute("pageContent", "./products.jsp");
	        
	        // Forward the request to the admin dashboard JSP for rendering
	        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
	    } catch (ServletException | IOException | ClassNotFoundException | SQLException e) {
	        // Handle errors by logging and showing a user-friendly message
	        ErrorHandlerUtilty.handleErrorAdmin(request, response, "Oops! Error While fetching data, Try Again Later");
	        e.printStackTrace();
	    }
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
