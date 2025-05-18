package com.techghar.controller.admin;

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

import com.techghar.DAO.BrandDAO;
import com.techghar.DAO.CategoryDAO;
import com.techghar.DAO.ProductDAO;
import com.techghar.model.Brand;
import com.techghar.model.Category;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.utility.ImageUtility;

/**
 * Servlet implementation class UpdateProductServlet
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/admin-update","/admin-update-save"})
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024,  // 1MB
	    maxFileSize = 1024 * 1024 * 5,    // 5MB
	    maxRequestSize = 1024 * 1024 * 10 // 10MB
	)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	BrandDAO brandDAO;
	ProductDAO prodDAO;
	CategoryDAO catDAO;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProductServlet() throws ClassNotFoundException, SQLException {
		brandDAO = new BrandDAO();
		prodDAO = new ProductDAO();
		catDAO = new CategoryDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int productId = Integer.parseInt(request.getParameter("id"));
		Product product;
		List<Brand> brands;
		try {
			ArrayList<Category> categories = (ArrayList<Category>) catDAO.getAllCategories();
			brands = brandDAO.getAllBrands();
			request.setAttribute("categories", categories);
			request.setAttribute("brands", brands);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./updateProduct.jsp");
			product = prodDAO.getProductById(productId);
			request.setAttribute("product", product);
			request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (Exception e) {
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occur While Fetching data! Try Again later");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		 
		try {
			int productId = Integer.parseInt(request.getParameter("id"));
			   // Get form fields
		    String name = request.getParameter("name");
		    String description = request.getParameter("description");
		    double price = Double.parseDouble(request.getParameter("price"));
		    int stock = Integer.parseInt(request.getParameter("stock"));
		    int brandId = Integer.parseInt(request.getParameter("brandId"));
		    int categoryId = Integer.parseInt(request.getParameter("categoryId"));
		    Part image = request.getPart("imageFile");
		  String filePath = ImageUtility.fileWriter(request, response, image);

			if (filePath == null) {
				request.setAttribute("message", "❌ Failed to Upload the product image.");
				request.setAttribute("messageType", "error");

			}
			else {
			
			    Product updatedProduct = new Product();
			    updatedProduct.setName(name);
			    updatedProduct.setDescription(description);
			    updatedProduct.setPrice(price);
			    updatedProduct.setStock(stock);
			    updatedProduct.setBrand(brandId);
			    updatedProduct.setCategory(categoryId);
			    updatedProduct.setImageURL(filePath);
			    updatedProduct.setId(productId);
			    ProductDAO dao = new ProductDAO();
			   Boolean productUpdatedSuccessfully;
				productUpdatedSuccessfully = dao.updateProduct(updatedProduct);
				
				
				System.out.println("product Added" + productUpdatedSuccessfully);
				if (productUpdatedSuccessfully) {
					request.setAttribute("message", "✅ Product added successfully!");
					request.setAttribute("messageType", "success");
					
				} else {
					request.setAttribute("message", "❌ Failed to add product.");
					request.setAttribute("messageType", "error");
					
				}
			}
		   
		    response.sendRedirect("admin-products");
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occured While Updating Product");
			e.printStackTrace();
		}
		    
		    
		}

	
	

}
