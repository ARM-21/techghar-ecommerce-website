package com.techghar.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.OrderModel;
import com.techghar.model.RecentOrderModel; 

public class StatisticDAO {
	private static ProductDAO prodDAO;
	private static Connection conn = null;
	public StatisticDAO() throws ClassNotFoundException, SQLException {
		 prodDAO = new ProductDAO();
		 this.conn = DatabaseConnection.getDatabaseConnection();
		
	}
	public int getTotalProducts() throws SQLException {
		int totalProducts = prodDAO.getAllProducts().size();
        return totalProducts; 
    }

    public int getTotalCustomers() {
    	 int count = 0;
    	    String sql = "SELECT COUNT(*) AS count FROM users WHERE role = 'CUSTOMER'";

    	    try  {
    	    	PreparedStatement stmt = conn.prepareStatement(sql);
   	         ResultSet rs = stmt.executeQuery();	
    	        if (rs.next()) {
    	            count = rs.getInt("count"); // first column of result
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }

    	    return count;
    }

    public int getNewOrdersCount() throws SQLException, ClassNotFoundException {
        String sql = "SELECT COUNT(*) AS new_orders FROM orders o " +
                     "LEFT JOIN delivery_details d ON o.delivery_id = d.delivery_id " +
                     "WHERE d.delivery_status IS NULL OR d.delivery_status IN ('Pending', 'Processing')";

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("new_orders");
            }
        }

        return 0; // default if no result
    }

    
    public double getTotalRevenue() throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(total_price) AS revenue FROM orders o " +
                     "JOIN delivery_details d ON o.delivery_id = d.delivery_id " +
                     "WHERE d.delivery_status = 'Delivered'"; // Filter for only completed orders

        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getDouble("revenue");
            }
            
        }

        return 0.0;
    }


    public List<RecentOrderModel> getRecentOrders() throws SQLException, ClassNotFoundException {
        List<RecentOrderModel> list = new ArrayList<>();

        String sql = """
            SELECT o.order_id , 
                   u.first_name, 
                   u.last_name,
                   p.name AS product_name,
                   o.order_date,
                   o.total_price,
                   d.delivery_status
            FROM orders o
            JOIN users u ON o.user_id = u.user_id
            JOIN order_items oi ON o.order_id = oi.order_id
            JOIN products p ON oi.product_id = p.product_id
            LEFT JOIN delivery_details d ON o.delivery_id = d.delivery_id
            ORDER BY o.order_date DESC
            LIMIT 5
        """;

       
        	Connection conn = DatabaseConnection.getDatabaseConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RecentOrderModel order = new RecentOrderModel();
                order.setId(rs.getInt("order_id"));
                order.setCustomerName(rs.getString("first_name") + " " + rs.getString("last_name"));
                order.setProductName(rs.getString("product_name"));
                order.setDate(rs.getDate("order_date"));
                order.setAmount(rs.getDouble("total_price"));
                order.setStatus(rs.getString("delivery_status") != null ? rs.getString("delivery_status") : "Pending");
                list.add(order);
            }
        

        return list;
    }
}

