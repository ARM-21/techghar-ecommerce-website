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

public class ProductDAO {
	private Connection conn = null;

	public ProductDAO() throws ClassNotFoundException, SQLException {
		
			this.conn = DatabaseConnection.getDatabaseConnection();
		
	}

	public ArrayList<Product> getAllProducts() throws SQLException {
		ArrayList<Product> productList = new ArrayList<Product>();

	
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT p.product_id, p.name, p.price, p.description, p.stock, pi.image_url, p.rating, b.brand_name AS brand, c.name AS category_name FROM products p JOIN brands b ON p.brand_id = b.brand_id JOIN categories c ON p.category_id = c.category_id LEFT JOIN product_images pi ON p.product_id = pi.product_id GROUP BY p.product_id");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Product p = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("stock"), rs.getString("image_url"), rs.getInt("rating"),
						rs.getString("brand"), rs.getString("category_name"));
				productList.add(p);
			}

		

		return productList;
	}

	public Product getProductById(int id) throws SQLException {
		Product product = null;

		
			PreparedStatement stmt = conn.prepareStatement("SELECT  p.product_id,p.name, p.price,p.description, p.stock,  p.rating, b.brand_name AS brand,c.name AS category_name, pi.image_url FROM products p JOIN brands b ON p.brand_id = b.brand_id JOIN categories c ON p.category_id = c.category_id LEFT JOIN (SELECT product_id, MIN(image_url) AS image_url FROM product_images GROUP BY product_id) pi  ON p.product_id = pi.product_id WHERE p.product_id = ?");
			stmt.setInt(1, id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				product = new Product(rs.getInt("product_id"), rs.getString("name"), rs.getDouble("price"),
						rs.getString("description"), rs.getInt("stock"), rs.getString("image_url"), rs.getInt("rating"),
						rs.getString("brand"), rs.getString("category_name"));
			}
	

		return product;
	}

	
	
	 public ArrayList<Category> getAllCategories() throws SQLException {
	        ArrayList<Category> categories = new ArrayList<>();
	        String sql = "SELECT category_id, name FROM categories";

	        PreparedStatement stmt = conn.prepareStatement(sql);
	             ResultSet rs = stmt.executeQuery() ;

	            while (rs.next()) {
	                Category category = new Category();
	                category.setId(rs.getInt("category_id"));
	                category.setName(rs.getString("name"));
	                categories.add(category);
	            }

	    
	        return categories;
	    }
	 
	 public List<Brand> getAllBrands() throws SQLException {
		    ArrayList<Brand> brands = new ArrayList<Brand>();
		    String sql = "SELECT * FROM brands";

		 

	    		PreparedStatement stmt = conn.prepareStatement(sql);
	    		ResultSet rs = stmt.executeQuery();
		    	while (rs.next()) {
		            Brand brand = new Brand();
		            brand.setId(rs.getInt("brand_id"));
		            brand.setName(rs.getString("brand_name"));
		            brands.add(brand);
		        }
		   
		    return brands;
		}


}
