package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub

		try {

			ProductDAO productDAO = new ProductDAO();
			ArrayList<Product> products = productDAO.getAllProducts();

			request.setAttribute("products", products);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./products.jsp");
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (ServletException | IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
