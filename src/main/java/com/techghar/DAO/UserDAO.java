package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.UserModel;

public class UserDAO {
	
	private Connection conn = null;

	public UserDAO() throws ClassNotFoundException, SQLException {
		
			this.conn = DatabaseConnection.getDatabaseConnection();
		
	}

	public UserModel getUserById(int id) {
		UserModel user = null;
		String sql = "SELECT * FROM users WHERE user_id = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				 user = extractUser(rs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	//
    private UserModel extractUser(ResultSet rs) throws SQLException {
        UserModel user = new UserModel();
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhone(rs.getString("phone"));
        user.setAddress(rs.getString("address"));
        user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
        user.setUsername(rs.getString("username"));
        user.setDob(rs.getDate("dob"));
        user.setGender(rs.getString("gender"));
        return user;
    }
}
