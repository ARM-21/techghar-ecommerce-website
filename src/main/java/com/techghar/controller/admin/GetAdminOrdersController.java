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

import com.techghar.DAO.OrderDAO;
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
        super();
        conn = DatabaseConnection.getDatabaseConnection();
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
                List<OrderItem> items = orderDAO.getOrderItemsByOrderId(order.getOrderId());
                order.setItems(items);
            }

            request.setAttribute("orders", orders);
	        request.setAttribute("activePage", "admin-orders");
	        request.setAttribute("pageContent", "orders.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Failed to load orders.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
