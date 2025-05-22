package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.BrandSalesReport;
import com.techghar.model.CategorySalesReport;
import com.techghar.model.CustomerPurchaseReport;

/**
 * Data Access Object for generating sales and customer reports. This class
 * handles database operations related to analytics and reporting for the admin
 * dashboard.
 */
public class ReportDAO {
	private Connection conn = null;

	/**
	 * Constructor that initializes the database connection.
	 * 
	 * @throws ClassNotFoundException if the database driver class is not found
	 * @throws SQLException           if a database access error occurs
	 */
	public ReportDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getDatabaseConnection();
	}

	/**
	 * Retrieves sales data grouped by product categories.
	 * 
	 * @param startDate The start date for the report period
	 * @param endDate   The end date for the report period
	 * @return List of CategorySalesReport objects containing category sales data
	 * @throws SQLException if a database access error occurs
	 */
	public List<CategorySalesReport> getCategorySalesReport(Date startDate, Date endDate) throws SQLException {
		List<CategorySalesReport> report = new ArrayList<>();

		// Get total sales for the period to calculate percentages
		double totalSales = getTotalSales(startDate, endDate);

		String query = """
				    SELECT c.name AS category_name,
				           COUNT(oi.order_item_id) AS products_sold,
				           SUM(oi.price * oi.quantity) AS total_revenue
				    FROM order_items oi
				    JOIN products p ON oi.product_id = p.product_id
				    JOIN categories c ON p.category_id = c.category_id
				    JOIN orders o ON oi.order_id = o.order_id
				    WHERE o.order_date BETWEEN ? AND ?
				    GROUP BY c.category_id
				    ORDER BY total_revenue DESC
				""";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(endDate.getTime()));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String categoryName = rs.getString("category_name");
				int productsSold = rs.getInt("products_sold");
				double totalRevenue = rs.getDouble("total_revenue");
				double percentageOfSales = (totalSales > 0) ? (totalRevenue / totalSales) * 100 : 0;

				report.add(new CategorySalesReport(categoryName, productsSold, totalRevenue, percentageOfSales));
			}
		}

		return report;
	}

	/**
	 * Retrieves sales data grouped by product brands.
	 * 
	 * @param startDate The start date for the report period
	 * @param endDate   The end date for the report period
	 * @return List of BrandSalesReport objects containing brand sales data
	 * @throws SQLException if a database access error occurs
	 */
	public List<BrandSalesReport> getBrandSalesReport(Date startDate, Date endDate) throws SQLException {
		List<BrandSalesReport> report = new ArrayList<>();

		// Get total sales for the period to calculate percentages
		double totalSales = getTotalSales(startDate, endDate);

		String query = """
				    SELECT b.brand_name AS brand_name,
				           COUNT(oi.order_item_id) AS products_sold,
				           SUM(oi.price * oi.quantity) AS total_revenue
				    FROM order_items oi
				    JOIN products p ON oi.product_id = p.product_id
				    JOIN brands b ON p.brand_id = b.brand_id
				    JOIN orders o ON oi.order_id = o.order_id
				    WHERE o.order_date BETWEEN ? AND ?
				    GROUP BY b.brand_id
				    ORDER BY total_revenue DESC
				""";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(endDate.getTime()));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				String brandName = rs.getString("brand_name");
				int productsSold = rs.getInt("products_sold");
				double totalRevenue = rs.getDouble("total_revenue");
				double percentageOfSales = (totalSales > 0) ? (totalRevenue / totalSales) * 100 : 0;

				report.add(new BrandSalesReport(brandName, productsSold, totalRevenue, percentageOfSales));
			}
		}

		return report;
	}

	/**
	 * Retrieves customer purchase data for the current week.
	 * 
	 * @return List of CustomerPurchaseReport objects containing daily purchase data
	 * @throws SQLException if a database access error occurs
	 */
	public List<CustomerPurchaseReport> getWeeklyCustomerPurchaseReport() throws SQLException {
		List<CustomerPurchaseReport> report = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();

		// Today is the end date
		Date endDate = calendar.getTime();

		// Go back 6 days
		calendar.add(Calendar.DAY_OF_MONTH, -6);
		Date startDate = calendar.getTime();

		String query = "SELECT DATE(o.order_date) AS order_date,  COUNT(DISTINCT o.user_id) AS number_of_customers ,COUNT(DISTINCT o.order_id) AS number_of_orders, SUM(oi.subtotal) AS total_revenue FROM orders o JOIN order_items oi ON o.order_id = oi.order_id WHERE o.order_date BETWEEN ? AND ? GROUP BY DATE(o.order_date) ORDER BY DATE(o.order_date);";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(endDate.getTime()));

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Date date = rs.getDate("order_date");
				int numberOfCustomers = rs.getInt("number_of_customers");
				int numberOfOrders = rs.getInt("number_of_orders");
				double totalRevenue = rs.getDouble("total_revenue");

				report.add(new CustomerPurchaseReport(date, numberOfCustomers, numberOfOrders, totalRevenue));
			}
		}

		return report;
	}

	/**
	 * Helper method to calculate total sales for a given period.
	 * 
	 * @param startDate The start date for the period
	 * @param endDate   The end date for the period
	 * @return Total sales amount for the period
	 * @throws SQLException if a database access error occurs
	 */
	private double getTotalSales(Date startDate, Date endDate) throws SQLException {
		String query = """
				    SELECT SUM(total_price) AS total_sales
				    FROM orders
				    WHERE order_date BETWEEN ? AND ?
				""";

		try (PreparedStatement stmt = conn.prepareStatement(query)) {
			stmt.setDate(1, new java.sql.Date(startDate.getTime()));
			stmt.setDate(2, new java.sql.Date(endDate.getTime()));

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return rs.getDouble("total_sales");
			}
		}

		return 0;
	}
}