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

	 public boolean checkout(int userId, String address, String phone) throws SQLException {
		    PreparedStatement cartClearStmt = null;

		    List<CartItem> cartItems = cartDAO.getCartItems(userId);
		    if (cartItems.isEmpty()) return false;

		    double totalAmount = cartDAO.getCartTotal(userId);

		    // 1. Insert into delivery_details first
		    String insertDelivery = "INSERT INTO delivery_details (address, phone, delivery_status, delivery_date) VALUES (?, ?, ?, NOW())";
		    PreparedStatement deliveryStmt = conn.prepareStatement(insertDelivery, java.sql.Statement.RETURN_GENERATED_KEYS);
		    deliveryStmt.setString(1, address);
		    deliveryStmt.setString(2, phone);
		    deliveryStmt.setString(3, "Pending");
		    deliveryStmt.executeUpdate();

		    ResultSet deliveryRs = deliveryStmt.getGeneratedKeys();
		    if (!deliveryRs.next()) throw new SQLException("Failed to retrieve delivery ID.");
		    int deliveryId = deliveryRs.getInt(1);

		    // 2. Insert into orders with delivery_id
		    String insertOrder = "INSERT INTO orders (user_id, order_date, total_price, delivery_id) VALUES (?, NOW(), ?, ?)";
		    PreparedStatement orderStmt = conn.prepareStatement(insertOrder, java.sql.Statement.RETURN_GENERATED_KEYS);
		    orderStmt.setInt(1, userId);
		    orderStmt.setDouble(2, totalAmount);
		    orderStmt.setInt(3, deliveryId);
		    orderStmt.executeUpdate();

		    ResultSet orderRs = orderStmt.getGeneratedKeys();
		    if (!orderRs.next()) throw new SQLException("Failed to retrieve order ID.");
		    int orderId = orderRs.getInt(1);
		    System.out.println(orderId);

		    // 3. Insert into order_items
		    String insertItem = "INSERT INTO order_items (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
		    PreparedStatement itemStmt = conn.prepareStatement(insertItem);

		    // 4. Update stock
		    String updateStock = "UPDATE products SET stock = stock - ? WHERE product_id = ? AND stock >= ?";
		    PreparedStatement stockUpdateStmt = conn.prepareStatement(updateStock);

		    for (CartItem item : cartItems) {
		        itemStmt.setInt(1, orderId);
		        itemStmt.setInt(2, item.getProduct().getId());
		        itemStmt.setInt(3, item.getQuantity());
		        itemStmt.setDouble(4, item.getProduct().getPrice());
		        itemStmt.addBatch();

		        stockUpdateStmt.setInt(1, item.getQuantity());
		        stockUpdateStmt.setInt(2, item.getProduct().getId());
		        stockUpdateStmt.setInt(3, item.getQuantity());
		        stockUpdateStmt.addBatch();
		    }

		    itemStmt.executeBatch();
		    stockUpdateStmt.executeBatch();

		    // 5. Clear cart
		    String clearCart = "DELETE FROM cart WHERE user_id = ?";
		    cartClearStmt = conn.prepareStatement(clearCart);
		    cartClearStmt.setInt(1, userId);
		    cartClearStmt.executeUpdate();

		    return true;
		}

}