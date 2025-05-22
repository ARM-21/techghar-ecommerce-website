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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
            OrderDAO orderDAO = new OrderDAO();
            List<OrderModel> orders = orderDAO.getAllOrdersWithDelivery();

            // Get items for each order
            for (OrderModel order : orders) {
            	System.out.println(order.getId());
                List<OrderItem> items = orderDAO.getOrderItemsByOrderId(order.getId());
                System.out.println(items.get(0).getOrderId());
                order.setOrderItems(items);
            }

            request.setAttribute("orders", orders);
	        request.setAttribute("activePage", "admin-orders");
	        request.setAttribute("pageContent", "orders.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e ) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load orders.");
            request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  try {
		        int deliveryId = Integer.parseInt(request.getParameter("deliveryId"));
		        String deliveryStatus = request.getParameter("deliveryStatus");

		        OrderDAO orderDAO = new OrderDAO();
		        orderDAO.updateDeliveryStatus(deliveryId, deliveryStatus);

		        response.sendRedirect("admin-orders");
		    } catch (Exception e) {
		        e.printStackTrace();
		        request.setAttribute("error", "Failed to update delivery status.");
		        request.getRequestDispatcher("WEB-INF/pages/error.jsp").forward(request, response);
		    }
	}

}
