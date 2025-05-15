package com.techghar.controller.product;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;
import com.techghar.model.Product;

/**
 * Servlet implementation class GetProductDetailsServlet
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/product-details" })
public class GetProductDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetProductDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int productId = Integer.parseInt(request.getParameter("id"));
		Product product;
		try {

			ProductDAO dao = new ProductDAO();
			product = dao.getProductById(productId);
			request.setAttribute("product", product);
			request.setAttribute("pageContent", "/WEB-INF/pages/productDetails.jsp");
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		} catch (Exception e) {
			handleError(request, response, "Error Occur While Fetching data! Try Again later");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		// Set the error message as a request attribute
		request.setAttribute("error", message);

		// Set page content to error page
		request.setAttribute("pageContent", "/WEB-INF/pages/error.jsp");

		// Forward the request to the main page (home.jsp or error.jsp)
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

}
