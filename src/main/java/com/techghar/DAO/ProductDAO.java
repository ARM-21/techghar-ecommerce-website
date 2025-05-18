package com.techghar.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.techghar.model.Brand;
import com.techghar.model.Category;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;

public class ProductDAO {
	private Connection conn = null;

	public ProductDAO() throws ClassNotFoundException, SQLException {

		this.conn = DatabaseConnection.getDatabaseConnection();

	}

	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT p.product_id, p.name, p.price, p.description, p.stock, p.created_at, p.imageURL, p.rating,b.brand_id,c.category_id, b.brand_name AS brand, c.name AS category FROM products p JOIN brands b ON p.brand_id = b.brand_id JOIN categories c ON p.category_id = c.category_id  GROUP BY p.product_id");
		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
//			Product p = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"),
//					rs.getString("description"), rs.getInt("stock"), rs.getString("image_url"), rs.getInt("rating"),
//					rs.getInt("brand_id"), rs.getInt("category_id"));
//			p.setCreatedAt(rs.getString("created_at"));
			Product product = new Product();
			product.setId(rs.getInt("product_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStock(rs.getInt("stock"));
			product.setImageURL(rs.getString("imageURL"));
			product.setRating(rs.getInt("rating"));
			product.setBrandName(rs.getString("brand"));
			product.setCategoryName(rs.getString("category"));
			product.setCreatedAt(rs.getString("created_at"));
			productList.add(product);
		}

		return productList;
	}

	public Product getProductById(int id) throws SQLException {
		Product product = null;

		PreparedStatement stmt = conn.prepareStatement(
				"SELECT  p.product_id,p.name, p.price,p.description, p.stock,  p.rating, b.brand_name AS brand ,c.name AS category, p.imageURL FROM products p JOIN brands b ON p.brand_id = b.brand_id JOIN categories c ON p.category_id = c.category_id  WHERE p.product_id = ?");
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			System.out.println(rs.getString("brand"));
			System.out.println(rs.getString("category"));
			product = new Product();
			product.setId(rs.getInt("product_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			product.setStock(rs.getInt("stock"));
			product.setImageURL(rs.getString("imageURL"));
			product.setBrandName(rs.getString("brand"));
			product.setCategoryName(rs.getString("category"));
		}

		return product;
	}


	public List<Integer> getAllRatings() throws SQLException {
		List<Integer> ratings = new ArrayList<>();
		String sql = "SELECT DISTINCT rating FROM products ORDER BY rating ASC";

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			ratings.add(rs.getInt("rating"));
		}

		return ratings;
	}

	public boolean addProduct(Product p) {

		boolean result = false;

		try {
			String query = "INSERT INTO products (name, price, description, stock, imageURL, rating, brand_id, category_id) "

					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, p.getName());
			stmt.setDouble(2, p.getPrice());
			stmt.setString(3, p.getDescription());
			stmt.setInt(4, p.getStock());
			stmt.setString(5, p.getImageURL());
			stmt.setInt(6, p.getRating());
			stmt.setInt(7, p.getBrand());
			stmt.setInt(8, p.getCategory());
			result = stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateProduct(Product p) throws SQLException {
		String query = "UPDATE products SET name=?, description=?, price=?, stock=?, brand_id=?, category_id=?, rating=?, imageURL=? WHERE product_id=?";
		PreparedStatement stmt = conn.prepareStatement(query);
		stmt.setString(1, p.getName());
		stmt.setString(2, p.getDescription());
		stmt.setDouble(3, p.getPrice());
		stmt.setInt(4, p.getStock());
		stmt.setInt(5, p.getBrand());
		stmt.setInt(6, p.getCategory());
		stmt.setDouble(7, p.getRating());
		stmt.setString(8, p.getImageURL());
		stmt.setInt(9, p.getId());
		return stmt.executeUpdate() > 0;
	}

	public boolean deleteProductById(int id) throws SQLException {
	    String sql = "DELETE FROM products WHERE product_id = ?";
	    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        int rowsAffected = stmt.executeUpdate();
	        return rowsAffected > 0; // true if a row was deleted
	    }
	}
	public List<Product> getFilteredProducts(String categoryId, String brandId, Integer minPrice, Integer maxPrice,
			Integer minRating) throws SQLException {
		List<Product> products = new ArrayList<>();

		String sql = "SELECT * FROM products WHERE category_id = ? AND brand_id = ?";

		if (minPrice != null) {
			sql += " AND price >= ?";
		}
		if (maxPrice != null) {
			sql += " AND price <= ?";
		}
		if (minRating != null && minRating > 0) {
			sql += " AND rating >= ?";
		}

		PreparedStatement stmt = conn.prepareStatement(sql);

		// Setting parameters in correct order
		int index = 1;
		stmt.setInt(index++, Integer.parseInt(categoryId));
		stmt.setInt(index++, Integer.parseInt(brandId));

		if (minPrice != null) {
			stmt.setInt(index++, minPrice);
		}
		if (maxPrice != null) {
			stmt.setInt(index++, maxPrice);
		}
		if (minRating != null && minRating > 0) {
			stmt.setInt(index++, minRating);
		}

		// Execute the query
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Product product = new Product();
			product.setId(rs.getInt("product_id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getDouble("price"));
			product.setStock(rs.getInt("stock"));
			product.setRating(rs.getInt("rating"));
			product.setBrand(rs.getInt("brand_id"));
			product.setCategory(rs.getInt("category_id"));
			products.add(product);
		}

		return products;
	}

	public ArrayList<Product> searchProductsByName(String query) {
	    ArrayList<Product> result = new ArrayList<>();
	    String sql = "SELECT p.product_id, p.name, p.price, p.description, b.brand_name, c.name as cname, p.created_at, p.imageURL, p.stock FROM products p join brands b on p.brand_id = b.brand_id join categories c on p.category_id = c.category_id  WHERE p.name LIKE ?";
	    try  {
	         PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, "%" + query + "%");
	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	        	Product product = new Product();
				product.setId(rs.getInt("product_id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setStock(rs.getInt("stock"));
				product.setImageURL(rs.getString("imageURL"));
				product.setBrandName(rs.getString("brand_name"));
				product.setCategoryName(rs.getString("cname"));
				product.setCreatedAt(rs.getString("created_at"));
				result.add(product);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}

}
