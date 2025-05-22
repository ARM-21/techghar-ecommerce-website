package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.dao.CartDAO;
import com.techghar.model.CartItem;
import com.techghar.utility.SessionUtil;

/**
 * Servlet that handles displaying the user's shopping cart contents.
 * Supports asynchronous requests and maps to the "/cart" URL pattern.
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to display the user's cart page.
     *
     * Retrieves the current user's cart items, total price, and item count
     * then forwards the request to the cart JSP for rendering.
     *
     * @param request  the HttpServletRequest containing session info
     * @param response the HttpServletResponse to forward or respond with
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve user ID from session using utility
            int userId = (int) SessionUtil.getAttribute(request, "id");

            // Create DAO instance to interact with cart data
            CartDAO cartDAO = new CartDAO();

            // Fetch list of cart items for the user
            List<CartItem> cartItems = cartDAO.getCartItems(userId);

            // Calculate total price of all items in the cart
            double cartTotal = cartDAO.getCartTotal(userId);

            // Fetch total number of items in the cart
            int cartCount = cartDAO.getCartItemCount(userId);

            // Set cart data as request attributes for JSP access
            request.setAttribute("cartProducts", cartItems);
            request.setAttribute("cartTotal", cartTotal);

            // Forward to cart JSP page for rendering
            request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);

        } catch (SQLException e) {
            // Wrap SQL exceptions as ServletException for container handling
            throw new ServletException("Database error while loading cart", e);
        } catch (ClassNotFoundException e) {
            // Handle class loading issues (e.g., JDBC driver missing)
            e.printStackTrace();
        }
    }

    /**
     * Handles POST requests by delegating to doGet method.
     *
     * @param request  the HttpServletRequest
     * @param response the HttpServletResponse
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
