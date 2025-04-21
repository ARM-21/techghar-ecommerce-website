package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.model.Category;
import com.techghar.model.Brand;
/**
 * Servlet implementation class AddProductController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/add-product","/save-product"})
public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("addNewProduct", true);
		
		List<Brand> brands;
		try {
			ProductDAO dao = new ProductDAO();
			ArrayList<Category> categories = dao.getAllCategories(); 
			brands = dao.getAllBrands();
			request.setAttribute("categories", categories);
			request.setAttribute("brands", brands);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./component/products.jsp");
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Oops! Error Occured While Fetching Product related data");
			e.printStackTrace();
		} 

	
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ProductDAO dao = new ProductDAO();
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Integer.parseInt(request.getParameter("price")));
			product.setBrand(Integer.parseInt(request.getParameter("brand")));
			product.setCategory(Integer.parseInt(request.getParameter("category")));
			product.setDescription(request.getParameter("description"));
			product.setImageUrl(request.getParameter("imageUrl"));
			product.setStock(Integer.parseInt(request.getParameter("stock")));
			product.setRating(Integer.parseInt(request.getParameter("rating")));
			 Boolean productAddedSuccessfully = dao.addProduct(product);
			  
			  if (productAddedSuccessfully) {
				    request.setAttribute("message", "✅ Product added successfully!");
				    request.setAttribute("messageType", "success");
				} else {
				    request.setAttribute("message", "❌ Failed to add product.");
				    request.setAttribute("messageType", "error");
				}
				request.getRequestDispatcher("admin-product.jsp").forward(request, response);

		} catch (ClassNotFoundException | SQLException e) {
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occured While Updating the Products");
			e.printStackTrace();
		} 
	
	}

}
