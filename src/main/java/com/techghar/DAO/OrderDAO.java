package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.techghar.model.DeliveryModel;
import com.techghar.model.OrderItem;
import com.techghar.model.OrderModel;
import com.techghar.model.Product;

import java.util.List;
import com.techghar.databaseConnection.DatabaseConnection;

public class OrderDAO {
	private Connection conn;

	public OrderDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getDatabaseConnection();

	}

	public List<OrderModel> getOrdersByUserId(int userId) throws SQLException {
		List<OrderModel> orders = new ArrayList<>();

		String orderQuery = "SELECT * FROM orders WHERE user_id = ? ORDER BY order_date DESC";
		PreparedStatement orderStmt = conn.prepareStatement(orderQuery);
		orderStmt.setInt(1, userId);
		ResultSet orderRs = orderStmt.executeQuery();

		while (orderRs.next()) {
			OrderModel order = new OrderModel(userId, userId, userId, null, null, null);
			int orderId = orderRs.getInt("order_id");

			order.setId(orderId);
			order.setUserId(orderRs.getInt("user_id"));
			order.setTotalAmount(orderRs.getDouble("total_price"));
			order.setOrderDate(orderRs.getTimestamp("order_date"));

			// Get order items for this order
			order.setOrderItems(getOrderItems(orderId));

			orders.add(order);
		}

		return orders;
	}

	public List<OrderItem> getOrderItems(int orderId) throws SQLException {
		List<OrderItem> items = new ArrayList<>();

		String itemQuery = "SELECT oi.*, p.name, p.description FROM order_items oi "
				+ "JOIN products p ON oi.product_id = p.product_id " + "WHERE oi.order_id = ?";
		PreparedStatement stmt = conn.prepareStatement(itemQuery);
		stmt.setInt(1, orderId);
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			OrderItem item = new OrderItem();
			item.setOrderItemId(rs.getInt("order_item_id"));
			item.setOrderId(rs.getInt("order_id"));
			item.setProductId(rs.getInt("product_id"));
			item.setQuantity(rs.getInt("quantity"));
			item.setPrice(rs.getDouble("price"));
			item.setSubtotal(rs.getDouble("quantity") * rs.getDouble("price"));

			Product product = new Product();
			product.setId(rs.getInt("product_id"));
			product.setName(rs.getString("name"));
			product.setDescription(rs.getString("description"));

			item.setProduct(product);
			items.add(item);
		}

		return items;
	}

	public OrderModel getOrderWithDeliveryById(int orderId) throws SQLException {
		OrderModel order = null;
		String sql = "SELECT o.*, d.delivery_id, d.address, d.phone, d.delivery_status, d.delivery_date "
				+ "FROM orders o LEFT JOIN delivery_details d ON o.delivery_id = d.delivery_id "
				+ "WHERE o.order_id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderId);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			order = new OrderModel();
			order.setId(rs.getInt("order_id"));
			order.setUserId(rs.getInt("user_id"));
			order.setTotalAmount(rs.getDouble("total_price"));
			order.setOrderDate(rs.getDate("order_date"));

			DeliveryModel delivery = new DeliveryModel();
			delivery.setDeliveryId(rs.getInt("delivery_id"));
			delivery.setOrderId(rs.getInt("order_id"));
			delivery.setAddress(rs.getString("address"));
			delivery.setPhone(rs.getString("phone"));
			delivery.setDeliveryStatus(rs.getString("delivery_status"));
			delivery.setDeliveryDate(rs.getDate("delivery_date"));

			order.setDeliveryModel(delivery);
		}

		return order;
	}

	public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
		List<OrderItem> items = new ArrayList<>();
		String sql = "SELECT oi.*, p.name AS product_name FROM order_items oi "
				+ "JOIN products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";

		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, orderId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			OrderItem item = new OrderItem();
			item.setOrderItemId(rs.getInt("order_item_id"));
			item.setOrderId(rs.getInt("order_id"));
			item.setProductId(rs.getInt("product_id"));
			item.setProductName(rs.getString("product_name"));
			item.setQuantity(rs.getInt("quantity"));
			item.setPrice(rs.getDouble("price"));
			item.setSubtotal(rs.getDouble("subtotal"));
			items.add(item);
		}

		return items;
	}

	public List<OrderModel> getAllOrdersWithDelivery() throws SQLException {
		List<OrderModel> orders = new ArrayList<>();

		String sql = "SELECT o.*, d.delivery_id, d.address, d.phone, d.delivery_status, d.delivery_date "
				+ "FROM orders o LEFT JOIN delivery_details d ON o.delivery_id = d.delivery_id";

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			OrderModel order = new OrderModel();
			order.setId(rs.getInt("order_id"));
			order.setUserId(rs.getInt("user_id"));
			order.setTotalAmount(rs.getInt("total_price"));
			order.setOrderDate(rs.getDate("order_date"));
			System.out.println(order.getOrderID());
			// Set delivery details
			DeliveryModel delivery = new DeliveryModel();
			delivery.setDeliveryId(rs.getInt("delivery_id"));
			delivery.setOrderId(rs.getInt("order_id"));
			delivery.setAddress(rs.getString("address"));
			delivery.setPhone(rs.getString("phone"));
			delivery.setDeliveryStatus(rs.getString("delivery_status"));
			delivery.setDeliveryDate(rs.getDate("delivery_date"));

			order.setDeliveryModel(delivery);

			orders.add(order);
		}

		return orders;
	}

public void updateDeliveryStatus(int orderId, String status) throws SQLException, ClassNotFoundException {
    String sql = "UPDATE delivery_details SET delivery_status = ? WHERE delivery_id = ?";
        	 PreparedStatement stmt = conn.prepareStatement(sql);
        	 Connection conn = DatabaseConnection.getDatabaseConnection();
        stmt.setString(1, status);
        stmt.setInt(2, orderId);
        stmt.executeUpdate();
    
}
}