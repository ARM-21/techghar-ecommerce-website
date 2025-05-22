package com.techghar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.CarouselItem;

/**
 * Data Access Object for carousel items. This class handles database operations
 * related to carousel items displayed on the homepage.
 */
public class CarouselDAO {
	private Connection conn = null;

	/**
	 * Constructor that initializes the database connection.
	 * 
	 * @throws ClassNotFoundException if the database driver class is not found
	 * @throws SQLException if a database access error occurs
	 */
	public CarouselDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getDatabaseConnection();
	}

	/**
	 * Retrieves all carousel items from the database.
	 * 
	 * @return ArrayList of CarouselItem objects containing all carousel items
	 * @throws SQLException if a database access error occurs
	 */
	public ArrayList<CarouselItem> getCarouselItems() throws SQLException {
		ArrayList<CarouselItem> items = new ArrayList<CarouselItem>();

		PreparedStatement ps = conn.prepareStatement("SELECT * FROM carousel_items");
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			CarouselItem item = new CarouselItem(
				rs.getInt("id"),
				rs.getString("title"), 
				rs.getString("description"),
				rs.getString("image_url")
			);
			items.add(item);
		}

		return items;
	}
	
	/**
	 * Retrieves a specific carousel item by its ID.
	 * 
	 * @param id The ID of the carousel item to retrieve
	 * @return CarouselItem object if found, null otherwise
	 * @throws SQLException if a database access error occurs
	 */
	public CarouselItem getCarouselItemById(int id) throws SQLException {
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM carousel_items WHERE id = ?");
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			return new CarouselItem(
				rs.getInt("id"),
				rs.getString("title"), 
				rs.getString("description"),
				rs.getString("image_url")
			);
		}
		
		return null;
	}
	
	/**
	 * Adds a new carousel item to the database.
	 * 
	 * @param item The CarouselItem object to add
	 * @return true if the operation was successful, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	public boolean addCarouselItem(CarouselItem item) throws SQLException {
		String query = "INSERT INTO carousel_items (title, description, image_url) VALUES (?, ?, ?)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, item.getTitle());
		ps.setString(2, item.getDescription());
		ps.setString(3, item.getImageUrl());
		
		int rowsAffected = ps.executeUpdate();
		return rowsAffected > 0;
	}
	
	/**
	 * Updates an existing carousel item in the database.
	 * 
	 * @param id The ID of the carousel item to update
	 * @param item The CarouselItem object with updated values
	 * @return true if the operation was successful, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	public boolean updateCarouselItem(int id, CarouselItem item) throws SQLException {
		String query = "UPDATE carousel_items SET title = ?, description = ?, image_url = ? WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, item.getTitle());
		ps.setString(2, item.getDescription());
		ps.setString(3, item.getImageUrl());
		ps.setInt(4, id);
		
		int rowsAffected = ps.executeUpdate();
		return rowsAffected > 0;
	}
	
	/**
	 * Deletes a carousel item from the database.
	 * 
	 * @param id The ID of the carousel item to delete
	 * @return true if the operation was successful, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	public boolean deleteCarouselItem(int id) throws SQLException {
		String query = "DELETE FROM carousel_items WHERE id = ?";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setInt(1, id);
		
		int rowsAffected = ps.executeUpdate();
		return rowsAffected > 0;
	}
}
