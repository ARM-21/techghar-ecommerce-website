package com.techghar.controller.home;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techghar.dao.CarouselDAO;
import com.techghar.dao.CartDAO;
import com.techghar.dao.ProductDAO;
import com.techghar.model.CarouselItem;
import com.techghar.model.CartItem;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.utility.SessionUtil;

/**
 * Servlet implementation class GetHomeServlet
 * Handles requests for the home and products pages, 
 * fetching carousel, product, and cart data.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/home", "/products" })
public class GetHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Handles GET requests for the home and products pages.
	 * Retrieves carousel items, products, and cart info, 
	 * sets request attributes, then forwards to home.jsp.
	 *
	 * @param request  the HttpServletRequest object
	 * @param response the HttpServletResponse object
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// List to hold product data
		List<Product> products = null;
		String sort = null;
		
		try {
			// Retrieve user id from session
			int userId = (int) SessionUtil.getAttribute(request, "id");
			
			// Instantiate DAO objects for database access
			ProductDAO productDAO = new ProductDAO();
			CarouselDAO carouselDAO = new CarouselDAO();
			CartDAO cartDAO = new CartDAO();
			
			// Retrieve carousel items to display on homepage
			ArrayList<CarouselItem> carouselItems = carouselDAO.getCarouselItems();
			
			// Retrieve cart items for the current user
			List<CartItem> cartItems = cartDAO.getCartItems(userId);
			
			if (userId > 0) {
				// Get total amount and item count in user's cart
				double cartTotal = cartDAO.getCartTotal(userId);
				int cartCount = cartDAO.getCartItemCount(userId);
				
				// Set cart total as attribute (note: this uses cartTotal, but sets cartCount attribute? Preserving as is)
				request.setAttribute("cartCount", (int) cartTotal);
			}
			
			// Debug messages for server logs
			System.out.println("Database Connected Successfully");
			System.out.println(request.getRequestURI());
			
			// Determine which page is requested and set active page accordingly
			if (request.getRequestURI().equals("/products")) {
				request.setAttribute("activePage", "products");
			} else {
				// For home page, set carousel and active page attributes
				request.setAttribute("carouselItems", carouselItems);
				request.setAttribute("activePage", "home");
				
				// Check if a sort parameter is provided
				sort = request.getParameter("sort");
				System.out.println(sort);
				
				if (sort != null) {
					// Retrieve products sorted by the given parameter
					products = productDAO.getAllProducts(sort);
					
					// Set products and sort info as request attributes
					request.setAttribute("products", products);
					request.setAttribute("sort", sort);
				}
			}
			
			// If no sort parameter provided, default to "price-low" sorting
			if (sort == null) {
				products = productDAO.getAllProducts("price-low");
			}

			// Set products attribute (overwrites previous set if any)
			request.setAttribute("products", products);
			
			// Set main content page for the view
			request.setAttribute("pageContent", "./main.jsp");
			
			// Forward to the main layout JSP page
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
			
		} catch (SQLException | ClassNotFoundException | IOException | ServletException e) {
			e.printStackTrace();
			// Use utility to handle errors and show user-friendly message
			ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured Try Again Later");
		}
	}
}
