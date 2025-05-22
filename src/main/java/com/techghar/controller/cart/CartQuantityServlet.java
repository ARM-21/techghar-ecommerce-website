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
 * Servlet that handles updating the quantity of items in the user's cart
 * by incrementing or decrementing based on the "action" parameter.
 */
@WebServlet("/Cartquantity")
public class CartQuantityServlet extends HttpServlet {

    private CartDAO cartDAO;

    /**
     * Initializes the servlet and creates an instance of CartDAO for database operations.
     *
     * @throws ServletException if DAO initialization fails
     */
    @Override
    public void init() throws ServletException {
        try {
            cartDAO = new CartDAO();
        } catch (Exception e) {
            throw new ServletException("Error initializing DAO", e);
        }
    }

    /**
     * Handles GET requests to update the quantity of a cart item.
     *
     * @param request  the HttpServletRequest containing user session and parameters
     * @param response the HttpServletResponse used to redirect or report errors
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Retrieve user ID from session using SessionUtil
        int userId = (int) SessionUtil.getAttribute(request, "id");

        // Get action type (inc/dec) and product ID from request parameters
        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            boolean result = false;

            // Perform quantity update based on action type
            if ("inc".equals(action)) {
                result = cartDAO.incrementQuantity(userId, productId);
            } else if ("dec".equals(action)) {
                result = cartDAO.decrementQuantity(userId, productId);
            }

            // If operation fails, set an error message in the session
            if (!result) {
                session.setAttribute("errorMessage", "Failed to update quantity.");
            }

            // Redirect user to the cart page after operation
            response.sendRedirect("cart");

        } catch (SQLException | NumberFormatException e) {
            // Handle SQL and input format exceptions
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error updating quantity: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
}
