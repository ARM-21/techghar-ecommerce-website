package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.UserModel;
import com.techghar.utility.ErrorHandlerUtilty;

public class UserDAO {

	private Connection conn = null;

	public UserDAO() throws ClassNotFoundException, SQLException {

		this.conn = DatabaseConnection.getDatabaseConnection();

	}

	public UserModel getUserById(int id) throws SQLException {
		UserModel user = null;
		String sql = "SELECT * FROM users WHERE user_id = ?";

		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			user = extractUser(rs);
		}
		return user;
	}

	public List<UserModel> getAllCustomers() throws SQLException {
		List<UserModel> list = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE role = 'CUSTOMER'";

		try (PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
				user.setUsername(rs.getString("username"));
				user.setDob(rs.getDate("dob"));
				user.setGender(rs.getString("gender"));
				user.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
				list.add(user);
			}
		}
		return list;
	}

	//
	private UserModel extractUser(ResultSet rs) throws SQLException {
		UserModel user = new UserModel();
		user.setId(rs.getInt("user_id"));
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

	// Update user details
	public boolean updateUser(UserModel user, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE users SET ");
		System.out.println("Staff-id"+user.getId());
		List<Object> params = new ArrayList<>();

		if (user.getFirstName() != null) {
			sql.append("first_name = ?, ");
			params.add(user.getFirstName());
		}
		if (user.getLastName() != null) {
			sql.append("last_name = ?, ");
			params.add(user.getLastName());
		}
		if (user.getEmail() != null) {
			sql.append("email = ?, ");
			params.add(user.getEmail());
		}
		if (user.getPhone() != null) {
			sql.append("phone = ?, ");
			params.add(user.getPhone());
		}
		if (user.getAddress() != null) {
			sql.append("address = ?, ");
			params.add(user.getAddress());
		}
		if (user.getUsername() != null) {
			sql.append("username = ?, ");
			params.add(user.getUsername());
		}
		if (user.getDob() != null) {
			sql.append("dob = ?, ");
			params.add(user.getDob());
		}
		if (user.getGender() != null) {
			sql.append("gender = ?, ");
			params.add(user.getGender());
		}

		if (params.isEmpty()) {
			return false;
		}

		sql.setLength(sql.length() - 2);
		sql.append(" WHERE user_id = ?");
		params.add(user.getId());
		System.out.println("user-id" + request.getParameter("id"));

		PreparedStatement stmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}
			return stmt.executeUpdate() > 0;

	}

	public List<UserModel> getAllStaff() throws SQLException {
		List<UserModel> staffList = new ArrayList<>();
		String sql = "SELECT * FROM users WHERE role = ?";


			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "STAFF");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				UserModel staff = extractUser(rs);

				staffList.add(staff);
			}



		return staffList;
	}

	public void saveStaff(UserModel staff) throws SQLException {
		String sql = "INSERT INTO users (first_name, last_name, email, password, phone, address, role, username, dob, gender) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setString(1, staff.getFirstName());
		stmt.setString(2, staff.getLastName());
		stmt.setString(3, staff.getEmail());
		stmt.setString(4, staff.getPassword());
		stmt.setString(5, staff.getPhone());
		stmt.setString(6, staff.getAddress());
		stmt.setString(7, "STAFF");
		stmt.setString(8, staff.getUsername());
		stmt.setDate(9, staff.getDob());
		stmt.setString(10, staff.getGender());

		stmt.executeUpdate();

	}

	public boolean deleteStaff(int id) throws SQLException, ClassNotFoundException {
		String sql = "DELETE FROM users WHERE user_id = ? AND role = 'STAFF'";

		Connection conn = DatabaseConnection.getDatabaseConnection();
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setInt(1, id);
		return stmt.executeUpdate() > 0;
	}

}
