package com.techghar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.Category;

public class CategoryDAO {
	private Connection conn;
	public CategoryDAO() throws ClassNotFoundException, SQLException
	{	
		 conn = DatabaseConnection.getDatabaseConnection();
		
	}

	public void addCategory(Category category) throws SQLException {
	    String sql = "INSERT INTO categories (name) VALUES (?)";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, category.getName());
	    stmt.executeUpdate();
	}

	public void updateCategory(int id, String name) throws SQLException {
	    String sql = "UPDATE categories SET name = ? WHERE category_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setString(1, name);
	    stmt.setInt(2, id);
	    stmt.executeUpdate();
	}

	public void deleteCategory(int id) throws SQLException {
	    String sql = "DELETE FROM categories WHERE category_id = ?";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    stmt.setInt(1, id);
	    stmt.executeUpdate();
	}

	public List<Category> getAllCategories() throws SQLException {
	    List<Category> list = new ArrayList<>();
	    String sql = "SELECT * FROM categories";
	    PreparedStatement stmt = conn.prepareStatement(sql);
	    ResultSet rs = stmt.executeQuery();
	    while (rs.next()) {
	        Category cat = new Category();
	        cat.setId(rs.getInt("category_id"));
	        cat.setName(rs.getString("name"));
	        list.add(cat);
	    }
	    return list;
	}

}
