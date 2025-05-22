package com.techghar.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.Brand;

public class BrandDAO {
	private Connection conn;
	public BrandDAO() throws ClassNotFoundException, SQLException
	{	
		 conn = DatabaseConnection.getDatabaseConnection();
		
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
    public void addBrand(Brand brand) throws SQLException {
        String sql = "INSERT INTO brands (brand_name) VALUES (?)";
     
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, brand.getName());
            stmt.executeUpdate();
    }

    public void updateBrand(int id, String name) throws SQLException {
        String sql = "UPDATE brands SET brand_name = ? WHERE brand_id = ?";
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setInt(2, id);
            stmt.executeUpdate();
       
    }	
	
}
