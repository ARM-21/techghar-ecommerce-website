package com.techghar.DAO;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.CartItem;
import com.techghar.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {
    private Connection conn;
    private ProductDAO productDAO;

    public CartDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getDatabaseConnection();
        this.productDAO = new ProductDAO();
    }

  
    public boolean addToCart(int userId, int productId, int quantity) throws SQLException {
        // First check if the product already exists in user's cart
        String checkSql = "SELECT cart_id, quantity FROM cart WHERE user_id = ? AND product_id = ?";
        
        PreparedStatement checkStmt = conn.prepareStatement(checkSql);
        checkStmt.setInt(1, userId);
        checkStmt.setInt(2, productId);
        
        ResultSet rs = checkStmt.executeQuery();
        
        if (rs.next()) {
            // Product exists in cart, update quantity
            int cartId = rs.getInt("cart_id");
            int currentQuantity = rs.getInt("quantity");
            int newQuantity = currentQuantity + quantity;
            
            String updateSql = "UPDATE cart SET quantity = ? WHERE cart_id = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateSql);
            updateStmt.setInt(1, newQuantity);
            updateStmt.setInt(2, cartId);
            
            return updateStmt.executeUpdate() > 0;
        } else {
            // Product not in cart, add new entry
            String insertSql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement insertStmt = conn.prepareStatement(insertSql);
            insertStmt.setInt(1, userId);
            insertStmt.setInt(2, productId);
            insertStmt.setInt(3, quantity);
            
            return insertStmt.executeUpdate() > 0;
            
            
        }
    }
    
    public List<CartItem> getCartItems(int userId) throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        
        String sql = "SELECT c.cart_id, c.product_id, c.quantity FROM cart c WHERE c.user_id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        
        ResultSet rs = stmt.executeQuery();
        
        while (rs.next()) {
            CartItem item = new CartItem();
            int productId = rs.getInt("product_id");
            
            
            // Get product details using your existing ProductDAO
            Product product = productDAO.getProductById(productId);
            item.setProduct(product);
            item.setQuantity(rs.getInt("quantity"));
            
            cartItems.add(item);
            
        }
        
        return cartItems;
    }
    
    public boolean removeCartItem(int userId, int productId) throws SQLException {
        String sql = "DELETE FROM cart WHERE user_id = ? AND product_id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        stmt.setInt(2, productId);
        
        return stmt.executeUpdate() > 0;
    }



    public int getCartItemCount(int userId) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cart WHERE user_id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        
        return 0;
    }
    public double getCartTotal(int userId) throws SQLException {
        String sql = "SELECT SUM(p.price * c.quantity) as total FROM cart c " +
                     "JOIN products p ON c.product_id = p.product_id " +
                     "WHERE c.user_id = ?";
        
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, userId);
        
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getDouble("total");
        }
        
        return 0.0;
    }

}