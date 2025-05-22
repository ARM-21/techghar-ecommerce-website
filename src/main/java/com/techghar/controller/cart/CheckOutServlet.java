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
import com.techghar.dao.checkoutDAO;
import com.techghar.model.CartItem;
import com.techghar.utility.SessionUtil;

/**
 * Servlet that handles displaying the checkout page and processing the checkout action.
 * Supports asynchronous requests and is mapped to "/cart-check-out".
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/cart-check-out"})
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to load the checkout page.
     *
     * Retrieves the current user's cart items and total, then forwards to the checkout JSP.
     *
     * @param request  the HttpServletRequest containing session info
     * @param response the HttpServletResponse used to forward or respond
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Retrieve user ID from session
            int userId = (int) SessionUtil.getAttribute(request, "id");

            // Instantiate CartDAO to get cart data
            CartDAO cartDAO = new CartDAO();

            // Get list of items in cart and total price
            List<CartItem> cartItems = cartDAO.getCartItems(userId);
            double cartTotal = cartDAO.getCartTotal(userId);

            // Set cart details as request attributes for JSP access
            request.setAttribute("cartProducts", cartItems);
            request.setAttribute("cartTotal", cartTotal);

            // Forward to checkout JSP page
            request.getRequestDispatcher("/WEB-INF/pages/cart-check-out.jsp").forward(request, response);

        } catch (SQLException e) {
            // Handle SQL exceptions by throwing ServletException
            throw new ServletException("Database error while loading cart" + e.getMessage());
        } catch (ClassNotFoundException e) {
            // Handle class loading exceptions
            e.printStackTrace();
        }
    }

    /**
     * Handles POST requests to process the checkout.
     *
     * Retrieves the user's shipping details from the request, combines them into an address,
     * and calls the checkout DAO to process the order. Sets session messages based on success or failure.
     *
     * @param request  the HttpServletRequest containing form data and session info
     * @param response the HttpServletResponse used to redirect after processing
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve shipping and contact details from form parameters
        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String zip = request.getParameter("zip");

        // Construct full address string from components
        String address = street + ", " + city + " - " + zip;

        // Get user session and user ID
        HttpSession session = request.getSession();
        int userId = (int) SessionUtil.getAttribute(request, "id");

        boolean success;
        try {
            // Create checkout DAO and attempt to process checkout
            checkoutDAO checkoutDao = new checkoutDAO();
            success = checkoutDao.checkout(userId, address, phone);
        } catch (Exception e) {
            e.printStackTrace();
            success = false;
        }

        // Set session attributes and redirect based on checkout result
        if (success) {
            session.setAttribute("orderSuccess", "Your order has been placed successfully!");
            session.setAttribute("cartCount", 0); // Reset cart count after successful order
            response.sendRedirect("orders");
        } else {
            session.setAttribute("errorMessage", "Checkout failed. Your cart may be empty.");
            response.sendRedirect("cart");
        }
    }
}
