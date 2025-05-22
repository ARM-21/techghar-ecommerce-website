package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.ProductDAO;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/product-delete" })
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/**
	 * Handles the HTTP POST request to delete a product by its ID.
	 *
	 * @param request  the HttpServletRequest containing the product ID parameter
	 * @param response the HttpServletResponse used for redirection or error handling
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException      if an I/O error occurs
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	        // Create DAO instance to perform product operations
	        ProductDAO dao = new ProductDAO();

	        // Retrieve the 'id' parameter from the request (product ID to delete)
	        String id = request.getParameter("id");

	        // Check if ID is provided
	        if (id != null) {
	            // Parse the product ID from string to integer
	            int idParam = Integer.parseInt(id);
	            System.out.println(idParam); // Debug: print the product ID

	            // Attempt to delete the product with the specified ID
	            Boolean isDeleted = dao.deleteProductById(idParam);
	            System.out.println("product is deleted " + isDeleted); // Debug: print deletion result

	            // If deletion was successful, redirect with success message
	            if (isDeleted) {
	                response.sendRedirect("admin-products?message=Product+deleted");
	                return; // exit after redirect
	            }
	        }

	        // If no ID is provided or deletion failed, set HTTP 404 Not Found status
	        response.setStatus(404);
	        return;

	    } catch (ClassNotFoundException | SQLException e) {
	        // On server-side exception, send HTTP 501 error response
	        response.sendError(501, "server Side error occured");

	        // Log the stack trace for debugging
	        e.printStackTrace();
	    }
	}
}
