package com.techghar.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;
import com.techghar.model.Brand;
import com.techghar.model.Category;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class UpdateProductServlet
 */

@WebServlet(asyncSupported = true, urlPatterns = {"/admin-update"})
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
        super();
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
			ProductDAO dao = new ProductDAO();
			ArrayList<Category> categories = dao.getAllCategories();
			brands = dao.getAllBrands();
			request.setAttribute("categories", categories);
			request.setAttribute("brands", brands);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./updateProduct.jsp");
			product = dao.getProductById(productId);
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
		doGet(request, response);
	}
	
	

}
