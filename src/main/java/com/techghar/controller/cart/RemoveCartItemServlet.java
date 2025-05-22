package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.dao.CartDAO;
import com.techghar.utility.SessionUtil;

/**
 * Servlet to handle removing an item from the user's cart.
 * Supports asynchronous requests and is mapped to "/RemoveFromCart".
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/RemoveFromCart"})
public class RemoveCartItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // DAO for cart operations
    private CartDAO cartDAO;

    /**
     * Initializes the servlet and the CartDAO instance.
     *
     * @throws ServletException if initialization of CartDAO fails
     */
    @Override
    public void init() throws ServletException {
        try {
            cartDAO = new CartDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CartDAO", e);
        }
    }

    /**
     * Handles GET requests to remove a specific product from the user's cart.
     *
     * Retrieves the user ID from session and the product ID from request parameters,
     * attempts removal via CartDAO, updates cart count in session, and redirects to cart page.
     * On failure, sets an error message and redirects to an error page.
     *
     * @param request  the HttpServletRequest containing session and parameters
     * @param response the HttpServletResponse for redirecting
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Retrieve logged-in user's ID from session
        int userId = (int) SessionUtil.getAttribute(request, "id");
        System.out.println(userId); // Debug: print userId

        try {
            // Parse product ID to remove from request parameter
            int productId = Integer.parseInt(request.getParameter("id"));

            // Attempt to remove the product from the user's cart
            boolean removed = cartDAO.removeCartItem(userId, productId);

            if (removed) {
                // Update cart count in session after successful removal
                int cartCount = cartDAO.getCartItemCount(userId);
                session.setAttribute("cartCount", cartCount);
            } else {
                // Set error message if removal failed
                session.setAttribute("errorMessage", "Failed to remove item.");
            }

            // Redirect back to the cart page after removal attempt
            response.sendRedirect("cart");

        } catch (SQLException | NumberFormatException e) {
            // Handle exceptions, log stack trace and set error message in session
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
}
