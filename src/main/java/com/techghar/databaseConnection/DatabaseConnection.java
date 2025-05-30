package com.techghar.databaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	// Database configuration information
		private static final String DB_NAME = "ecommerce_db";
		private static final String URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
		private static final String USERNAME = "tech_user";
		private static final String PASSWORD = "tech@ghar123";

		/**
		 * Establishes a connection to the database.
		 *
		 * @return Connection object for the database
		 * @throws SQLException           if a database access error occurs
		 * @throws ClassNotFoundException if the JDBC driver class is not found
		 */
		public static Connection getDatabaseConnection() throws SQLException, ClassNotFoundException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		}
		
		public static void main(String[] args) {
			try {
				DatabaseConnection.getDatabaseConnection();
				System.out.println("Database Connected Successfully");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}

