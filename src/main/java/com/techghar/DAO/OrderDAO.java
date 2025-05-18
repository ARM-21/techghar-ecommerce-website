package com.techghar.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.DeliveryModel;
import com.techghar.model.OrderItem;
import com.techghar.model.OrderModel;;


public class OrderDAO {
    private Connection conn;

    public OrderDAO() throws ClassNotFoundException, SQLException {
        this.conn = DatabaseConnection.getDatabaseConnection();
    }

    // Get all orders with user and delivery details
    public List<OrderModel> getAllOrdersWithDelivery() throws SQLException {
        List<OrderModel> orders = new ArrayList<>();
        String sql = "SELECT o.order_id, o.user_id, u.first_name, u.last_name, o.total_price, o.order_date, " +
                     "d.delivery_id, d.address, d.phone, d.delivery_status, d.delivery_date " +
                     "FROM orders o " +
                     "JOIN users u ON o.user_id = u.user_id " +
                     "JOIN delivery_details d ON o.delivery_id = d.delivery_id";

        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            OrderModel order = new OrderModel();
            order.setOrderId(rs.getInt("order_id"));
            order.setUserId(rs.getInt("user_id"));
            order.setUserName(rs.getString("first_name") + " " + rs.getString("last_name"));
            order.setTotalPrice(rs.getDouble("total_price"));
            order.setOrderDate(rs.getDate("order_date"));

            // Delivery details
            DeliveryModel delivery = new DeliveryModel();
            delivery.setDeliveryId(rs.getInt("delivery_id"));
            delivery.setAddress(rs.getString("address"));
            delivery.setPhone(rs.getString("phone"));
            delivery.setDeliveryStatus(rs.getString("delivery_status"));
            delivery.setDeliveryDate(rs.getDate("delivery_date"));

            order.setDelivery(delivery);
            orders.add(order);
        }

        return orders;
    }
    public boolean updateDeliveryStatus(int orderId, String newStatus) throws SQLException {
        String sql = "UPDATE delivery SET delivery_status = ? WHERE order_id = ?";

        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newStatus);
            stmt.setInt(2, orderId);
            return stmt.executeUpdate() > 0;
 
    }
    // Order items for each order
    public List<OrderItem> getOrderItemsByOrderId(int orderId) throws SQLException {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT oi.*, p.name AS product_name FROM order_items oi " +
                     "JOIN products p ON oi.product_id = p.product_id WHERE oi.order_id = ?";

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
}
