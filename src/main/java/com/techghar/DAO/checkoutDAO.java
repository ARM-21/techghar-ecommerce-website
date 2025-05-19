package com.techghar.DAO;


import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.CartItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;


public class checkoutDAO {
	private Connection conn;
	private CartDAO cartDAO;
	
	
	 public checkoutDAO() throws ClassNotFoundException, SQLException {
	        this.conn = DatabaseConnection.getDatabaseConnection();
	        this.cartDAO = new CartDAO();
	    }

	 public boolean checkout(int userId, String address, String phone) throws SQLException{

	   
	    
	    PreparedStatement cartClearStmt = null;


	        List<CartItem> cartItems = cartDAO.getCartItems(userId);
	        if (cartItems.isEmpty()) return false;

	        double totalAmount = cartDAO.getCartTotal(userId);

	        // 1. Insert into orders
	        String insertOrder = "INSERT INTO orders (user_id, order_date, total_price) VALUES (?, NOW(), ?)";
	        PreparedStatement orderStmt = conn.prepareStatement(insertOrder, 1); 

	        orderStmt.setInt(1, userId);
	        orderStmt.setDouble(2, totalAmount);
	        orderStmt.executeUpdate();

	        ResultSet orderRs = orderStmt.getGeneratedKeys();
	        if (!orderRs.next()) throw new SQLException("Failed to retrieve order ID.");

	        int orderId = orderRs.getInt(1);
	        
	        String insertItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
	        PreparedStatement itemStmt = conn.prepareStatement(insertItem);
	        
	        String updateStock = "UPDATE products SET stock = stock - ? WHERE product_id = ? AND stock >= ?";
	        PreparedStatement stockUpdateStmt = conn.prepareStatement(updateStock);
	        
	        String insertDelivery = "INSERT INTO delivery_details (order_id, address, phone, delivery_status, delivery_date) VALUES (?, ?, ?, ?, NOW())";
	        PreparedStatement deliveryStmt = conn.prepareStatement(insertDelivery);
	        deliveryStmt.setInt(1, orderId);
	        deliveryStmt.setString(2, address);
	        deliveryStmt.setString(3, phone);
	        deliveryStmt.setString(4, "Pending"); 
	        deliveryStmt.executeUpdate();

	        for (CartItem item : cartItems) {
	            itemStmt.setInt(1, orderId);
	            itemStmt.setInt(2, item.getProduct().getId());
	            itemStmt.setInt(3, item.getQuantity());
	            itemStmt.setDouble(4, item.getProduct().getPrice());
	            itemStmt.addBatch();
	            
	            // Reduce stock
	            stockUpdateStmt.setInt(1, item.getQuantity());
	            stockUpdateStmt.setInt(2, item.getProduct().getId());
	            stockUpdateStmt.setInt(3, item.getQuantity());
	            stockUpdateStmt.addBatch();
	        }
	        
	        itemStmt.executeBatch();
	        stockUpdateStmt.executeBatch();
	       
	        String clearCart = "DELETE FROM cart WHERE user_id = ?";
	        cartClearStmt = conn.prepareStatement(clearCart);
	        cartClearStmt.setInt(1, userId);
	        cartClearStmt.executeUpdate();
	        
	        return true;
   
	}
}