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
	        	OrderModel order = new OrderModel(userId, userId, userId, null, null,null);
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

	        String itemQuery = "SELECT oi.*, p.name, p.description FROM order_items oi " +
	                           "JOIN products p ON oi.product_id = p.product_id " +
	                           "WHERE oi.order_id = ?";
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
	 	
	
	 public DeliveryModel getDeliveryByOrderId(int orderId) {
		 DeliveryModel delivery = null;
		    String sql = "SELECT * FROM delivery_details WHERE order_id = ?";
		    try (PreparedStatement ps = conn.prepareStatement(sql)) {
		        ps.setInt(1, orderId);
		        ResultSet rs = ps.executeQuery();
		        if (rs.next()) {
		            delivery = new DeliveryModel();
		            delivery.setDeliveryId(rs.getInt("delivery_id"));
		            delivery.setOrderId(rs.getInt("order_id"));
		            delivery.setAddress(rs.getString("address"));
		            delivery.setPhone(rs.getString("phone"));
		            delivery.setDeliveryStatus(rs.getString("delivery_status"));
		            delivery.setDeliveryDate(rs.getDate("delivery_date"));
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return delivery;
		}

	 
	

		public List<OrderModel> getAllOrdersWithDelivery() {
			// TODO Auto-generated method stub
			return null;
		}
	}