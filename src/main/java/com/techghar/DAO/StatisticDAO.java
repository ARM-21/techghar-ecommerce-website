package com.techghar.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.OrderModel; 

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

    public int getNewOrdersCount() {
        return 20; 
    }

    public double getTotalRevenue() {
        return 12345.67;
    }

    public List<OrderModel> getRecentOrders() {
        List<OrderModel> orders = new ArrayList<>();

        // Sample data
 
        return orders;
    }
}
