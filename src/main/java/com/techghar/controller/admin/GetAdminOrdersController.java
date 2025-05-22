package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.OrderDAO;
import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.OrderItem;
import com.techghar.model.OrderModel;

/**
 * Servlet implementation class GetAdminOrdersController
 */
@WebServlet("/admin-orders")
public class GetAdminOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private  Connection conn;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public GetAdminOrdersController() throws ClassNotFoundException, SQLException {
       
        conn = DatabaseConnection.getDatabaseConnection();
        
        if (conn == null) {
            throw new SQLException("Database connection failed");
        }
        // TODO Auto-generated constructor stub
    }

	
    /**
     * Handles the HTTP GET request to display all orders with their delivery details.
     *
     * @param request  the HttpServletRequest used to pass data to the view
     * @param response the HttpServletResponse used to forward or handle errors
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Initialize OrderDAO to interact with orders in the database
            OrderDAO orderDAO = new OrderDAO();

            // Retrieve all orders along with their delivery information
            List<OrderModel> orders = orderDAO.getAllOrdersWithDelivery();

            // For each order, fetch and set the associated order items
            for (OrderModel order : orders) {
                System.out.println(order.getId()); // Debug: print order ID

                // Get list of items for the current order
                List<OrderItem> items = orderDAO.getOrderItemsByOrderId(order.getId());

                System.out.println(items.get(0).getOrderId()); // Debug: print order ID from first item

                // Associate the order items with the current order object
                order.setOrderItems(items);
            }

            // Set orders list and UI attributes for rendering
            request.setAttribute("orders", orders);
            request.setAttribute("activePage", "admin-orders");
            request.setAttribute("pageContent", "orders.jsp");

            // Forward request to the admin dashboard layout JSP
            request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();

            // Set error message to show on the error page
            request.setAttribute("error", "Failed to load orders.");

            // Forward to a generic error page
            request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
        }
    }


    /**
     * Handles the HTTP POST request to update the delivery status of an order.
     *
     * @param request  the HttpServletRequest containing delivery ID and new status
     * @param response the HttpServletResponse used to redirect or forward in case of error
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Parse delivery ID from request parameter
            int deliveryId = Integer.parseInt(request.getParameter("deliveryId"));

            // Get the new delivery status from request parameter
            String deliveryStatus = request.getParameter("deliveryStatus");

            // Initialize DAO for order operations
            OrderDAO orderDAO = new OrderDAO();

            // Update the delivery status in the database for the given delivery ID
            orderDAO.updateDeliveryStatus(deliveryId, deliveryStatus);

            // Redirect to the orders admin page to reflect updated status
            response.sendRedirect("admin-orders");

        } catch (Exception e) {
            // Log the error stack trace for debugging
            e.printStackTrace();

            // Set error message attribute for the error page
            request.setAttribute("error", "Failed to update delivery status.");

            // Forward to a generic error page to inform the user/admin
            request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
        }
    }


}
