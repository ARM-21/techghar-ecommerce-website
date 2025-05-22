package com.techghar.controller.search;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.BrandDAO;
import com.techghar.dao.CategoryDAO;
import com.techghar.dao.ProductDAO;
import com.techghar.model.Category;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class FilterProductController
 */
@WebServlet("/search-catalog")
public class FilterProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	BrandDAO brandDAO;
	ProductDAO prodDAO;
	CategoryDAO catDAO;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	public FilterProductController() throws ClassNotFoundException, SQLException {
		brandDAO = new BrandDAO();
		prodDAO = new ProductDAO();
		catDAO = new CategoryDAO();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ProductDAO dao;
		try {

			request.setAttribute("brands", brandDAO.getAllBrands());
			request.setAttribute("categories", catDAO.getAllCategories());
			request.setAttribute("ratings", prodDAO.getAllRatings());
			request.setAttribute("products", prodDAO.getAllProducts());

			request.setAttribute("pageContent", "/WEB-INF/pages/search.jsp");
			request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException e) {
			e.printStackTrace();
			ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured ! Try Again Later");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		// Retrieve filter parameters from the request
		String categoryId = request.getParameter("category"); // Category ID
		String brandId = request.getParameter("brand"); // Brand ID
		String minPriceStr = request.getParameter("minPrice"); // Min price
		String maxPriceStr = request.getParameter("maxPrice"); // Max price
		String minRatingStr = request.getParameter("rating"); // Min rating

		Integer minPrice = minPriceStr != null && !minPriceStr.isEmpty() ? Integer.parseInt(minPriceStr) : null;
		Integer maxPrice = maxPriceStr != null && !maxPriceStr.isEmpty() ? Integer.parseInt(maxPriceStr) : null;
		Integer minRating = minRatingStr != null && !minRatingStr.isEmpty() ? Integer.parseInt(minRatingStr) : null;

		ProductDAO dao;
		try {
			dao = new ProductDAO();
			BrandDAO brandDAO = new BrandDAO();

			request.setAttribute("brands", brandDAO.getAllBrands());
			CategoryDAO catDAO = new CategoryDAO();
			request.setAttribute("categories", (ArrayList<Category>) catDAO.getAllCategories());
			request.setAttribute("ratings", dao.getAllRatings());
			request.setAttribute("products",
					dao.getFilteredProducts(categoryId, brandId, minPrice, maxPrice, minRating));
			request.setAttribute("pageContent", "/WEB-INF/pages/search.jsp");
			request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
		} catch (SQLException | ServletException | IOException | ClassNotFoundException e) {
			e.printStackTrace();
			ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured ! Try Again Later");
		}

	}

}
