package com.techghar.controller.admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.utility.ImageUtility;
import com.techghar.model.Category;
import com.techghar.dao.BrandDAO;
import com.techghar.dao.CategoryDAO;
import com.techghar.dao.ProductDAO;
import com.techghar.model.Brand;

/**
 * Servlet implementation class AddProductController
 */


@WebServlet(asyncSupported = true, urlPatterns = { "/add-product", "/save-product" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	BrandDAO brandDAO;
	ProductDAO prodDAO;
	CategoryDAO catDAO;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	 public AddProductController() throws ClassNotFoundException, SQLException {
		brandDAO = new BrandDAO();
		prodDAO = new ProductDAO();
		catDAO = new CategoryDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	 /**
	  * Handles the HTTP GET request to display the add new product page.
	  *
	  * @param request  the HttpServletRequest used to pass data to the view
	  * @param response the HttpServletResponse used to forward or handle errors
	  * @throws ServletException if a servlet-specific error occurs
	  * @throws IOException      if an I/O error occurs
	  */
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {

	     // Set a flag to indicate that the add new product form should be shown
	     request.setAttribute("addNewProduct", true);

	     List<Brand> brands;
	     try {
	         // Retrieve all categories from the database using catDAO
	         ArrayList<Category> categories = (ArrayList<Category>) catDAO.getAllCategories();

	         // Retrieve all brands from the database using brandDAO
	         brands = brandDAO.getAllBrands();

	         // Set the retrieved categories and brands in the request attributes
	         request.setAttribute("categories", categories);
	         request.setAttribute("brands", brands);

	         // Set the active page and the content page for the dashboard layout
	         request.setAttribute("activePage", "admin-products");
	         request.setAttribute("pageContent", "./products.jsp");

	         // Forward the request to the admin dashboard JSP
	         request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

	     } catch (SQLException e) {
	         // Handle database-related errors and delegate to error handler utility
	         ErrorHandlerUtilty.handleErrorAdmin(request, response,
	                 "Oops! Error Occured While Fetching Product related data");
	         e.printStackTrace();
	     }
	 }


	 /**
	  * Handles the HTTP POST request to add a new product.
	  *
	  * @param request  the HttpServletRequest containing form and file data
	  * @param response the HttpServletResponse used to forward or handle errors
	  * @throws ServletException if a servlet-specific error occurs
	  * @throws IOException      if an I/O error occurs
	  */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	         throws ServletException, IOException {
	     try {
	         // Debug: print selected brand ID to console
	         System.out.println(request.getParameter("brandId"));

	         // Initialize DAO objects for product and category operations
	         ProductDAO dao = new ProductDAO();
	         CategoryDAO catDAO = new CategoryDAO();

	         // Create a new Product object
	         Product product = new Product();

	         // Retrieve the uploaded image file from the request
	         Part image = request.getPart("imageFile");

	         // Check if the image file was provided; if not, return 401 error
	         if (image == null) {
	             response.sendError(401, "No Image Uploaded");
	         }

	         // Set product fields from form parameters
	         product.setName(request.getParameter("name"));
	         product.setPrice(Double.parseDouble(request.getParameter("price")));
	         product.setBrand(Integer.parseInt(request.getParameter("brandId")));
	         product.setCategory(Integer.parseInt(request.getParameter("categoryId")));
	         product.setDescription(request.getParameter("description"));
	         product.setImageURL(request.getParameter("imageUrl"));
	         product.setStock(Integer.parseInt(request.getParameter("stock")));

	         // Upload the image and get its saved file path
	         String pathFile = ImageUtility.fileWriter(request, response, image);
	         System.out.println(pathFile);

	         // If image upload fails, set error message and stop further processing
	         if (pathFile == null) {
	             request.setAttribute("message", "❌ Failed to Upload the product image.");
	             request.setAttribute("messageType", "error");
	             return;
	         }

	         // Set the uploaded image path in the product object
	         product.setImageURL(pathFile);

	         // Attempt to add the product to the database
	         Boolean productAddedSuccessfully = dao.addProduct(product);
	         System.out.println("product Added" + productAddedSuccessfully);

	         // Set appropriate success or failure messages in the request
	         if (productAddedSuccessfully) {
	             request.setAttribute("message", "✅ Product added successfully!");
	             request.setAttribute("messageType", "success");
	         } else {
	             request.setAttribute("message", "❌ Failed to add product.");
	             request.setAttribute("messageType", "error");
	         }

	         // Fetch updated list of categories and brands for the form
	         List<Brand> brands;
	         ArrayList<Category> categories = (ArrayList<Category>) catDAO.getAllCategories();
	         brands = brandDAO.getAllBrands();

	         // Set required attributes to re-display the add product form
	         request.setAttribute("categories", categories);
	         request.setAttribute("brands", brands);
	         request.setAttribute("activePage", "admin-products");
	         request.setAttribute("pageContent", "./products.jsp");
	         request.setAttribute("addNewProduct", true);

	         // Forward the request back to the admin dashboard with form pre-filled
	         request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

	     } catch (SQLException | ClassNotFoundException e) {
	         // Handle database or class loading exceptions
	         ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occured While Adding the Products");
	         e.printStackTrace();
	     }
	 }

	
}
