package com.techghar.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.UserModel;
import com.techghar.utility.EncryptDecryptUtil;
import com.techghar.utility.SessionUtil;

public class LoginDAO {
	private Connection dbConn;
	private boolean isConnectionError = false;

	/**
	 * Constructor initializes the database connection. Sets the connection error
	 * flag if the connection fails.
	 */
	public LoginDAO() {
		try {
			dbConn = DatabaseConnection.getDatabaseConnection();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			isConnectionError = true;
		}
	}

	/**
	 * Validates the user credentials against the database records.
	 *
	 * @param String username, String password
	 * @return true if the user credentials are valid, false otherwise; null if a
	 *         connection error occurs
	 */
	public Boolean logUserIN(HttpServletRequest request, String email, String password) {
		if (isConnectionError) {
			System.out.println("Connection Error!");
			return null;
		}

		String query = "SELECT email,username,user_id, password, role FROM users WHERE email = ?";
		try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
			stmt.setString(1, email);
			ResultSet result = stmt.executeQuery();

			if (result.next()) {

				return validateUserAndPassword(result, request, email, password, result.getString("username"),
						result.getInt("user_id"), result.getString("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

		return false;
	}

	/**
	 * Validates the password and Username retrieved from the database.
	 *
	 * @param result       the ResultSet containing the username and password from
	 *                     the database
	 * @param studentModel the StudentModel object containing user credentials
	 * @return true if the passwords match, false otherwise
	 * @throws SQLException if a database access error occurs
	 */
	private boolean validateUserAndPassword(ResultSet result, HttpServletRequest request, String email, String password,
			String username, int id, String role) throws SQLException {
		String dbEmail = result.getString("email");
		String dbPassword = result.getString("password");

		Boolean isCorrect = dbEmail.equals(email) && EncryptDecryptUtil.decrypt(dbPassword).equals(password);

		if (isCorrect) {
			SessionUtil.setAttribute(request, "username", username);
			SessionUtil.setAttribute(request, "id", id);
			SessionUtil.setAttribute(request, "role", role.toLowerCase());
			System.out.println("from role "+ SessionUtil.getAttribute(request,"role"));
		}
		return isCorrect;	
	}
}
