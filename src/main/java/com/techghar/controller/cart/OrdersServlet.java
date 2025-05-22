package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.techghar.dao.OrderDAO;
import com.techghar.model.DeliveryModel;
import com.techghar.model.OrderItem;
import com.techghar.model.OrderModel;
import com.techghar.utility.SessionUtil;

/**
 * Servlet to display user orders and their details.
 * Supports asynchronous requests and maps to "/orders".
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to fetch and display all orders for the logged-in user.
     *
     * For each order, fetches the associated order items and delivery information,
     * then forwards the data to the orders JSP page for rendering.
     *
     * @param request  the HttpServletRequest containing session info
     * @param response the HttpServletResponse used to forward or redirect
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user ID from session
        int userId = (int) SessionUtil.getAttribute(request, "id");

        try {
            // Instantiate OrderDAO to interact with order data
            OrderDAO orderDAO = new OrderDAO();

            // Get list of all orders for the user
            List<OrderModel> orders = orderDAO.getOrdersByUserId(userId);

            // For each order, fetch and set the order items and delivery info
            for (OrderModel order : orders) {
                List<OrderItem> items = orderDAO.getOrderItems(order.getId());
                order.setOrderItems(items);

                OrderModel delivery = orderDAO.getOrderWithDeliveryById(order.getId());
                if (delivery != null) {
                    order.setDeliveryModel(delivery.getDeliveryModel());
                }
            }

            // Set orders list as request attribute for JSP
            request.setAttribute("orders", orders);

            // Forward to orders JSP page for rendering
            request.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            // Log exception and redirect to error page
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
