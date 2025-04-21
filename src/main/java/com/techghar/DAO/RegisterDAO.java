package com.techghar.DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.techghar.databaseConnection.DatabaseConnection;
import com.techghar.model.UserModel;
import com.techghar.utility.ErrorHandlerUtilty;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RegisterDAO {
private  Connection conn = null;
	
	public RegisterDAO()  {
		System.out.println("connecting");
			try {
				this.conn = DatabaseConnection.getDatabaseConnection();
				System.out.println("success");
			} catch (ClassNotFoundException | SQLException e ) {
				
				
				e.printStackTrace();
			} 
			
		
	}
		
	/**
	 * Adds a new user to the 'user_table' in the database.
	 * 
	 * @param user The UserModel object containing all user details.
	 * @return true if the user was successfully added, false otherwise.
	 */
	public Boolean addNewUser(UserModel user, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Adding");
		System.out.println(conn);
	    // Functional level: return flag for insertion status
	    Boolean hasAdded = false;

	    // Check if the database connection is valid
	    if (conn == null) {
	        return false;
	    }

	    // SQL query to insert a new user (excluding user_id, role, and created_at)
	    String query = "INSERT INTO users (first_name, last_name, email, password, phone, address, username, dob, gender) " +
	                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    // Functional level: Handle SQL operations safely using try-with-resources
	    try (PreparedStatement ps = conn.prepareStatement(query)) {

	    		System.out.println(user.getDob());
	        // Set values into the prepared statement from the user object
	        ps.setString(1, user.getFirstName());  // First name
	        ps.setString(2, user.getLastName());   // Last name
	        ps.setString(3, user.getEmail());      // Email
	        ps.setString(4, user.getPassword());   // Password
	        ps.setString(5, user.getPhone());      // Phone number
	        ps.setString(6, user.getAddress());    // Address
	        ps.setString(7, user.getUsername());   // Username
	        
	        ps.setDate(8, user.getDob());        // Date of birth
	        ps.setString(9, user.getGender());     // Gender

	        // Execute the insert command and check the number of affected rows
	        int rowsAffected = ps.executeUpdate();

	        // If at least one row is inserted, set flag to true
	        hasAdded = rowsAffected > 0;

	    } catch (SQLException e) {
	        // Handle any SQL-related exceptions
	        e.printStackTrace();
	        try {
				handleError(request,response,"Error Occured! "+ e.getMessage());
			} catch (ServletException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				ErrorHandlerUtilty.handleError(request, response, "An Unexpected Error Occured! TRy Again Later");
			}
	    }
	    System.out.println(hasAdded);

	    // Return whether the user was added successfully
	    return hasAdded;
	}


	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("error", message);
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
	
	/**
	 * Checks if a user with the given email already exists in the 'user_table'.
	 *
	 * @param email The email address to be checked.
	 * @return true if the email exists in the database, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public Boolean doesUserExists(String email) throws SQLException {

	    // Functional level: Check if connection is valid
	    if (conn != null) {
	        // SQL query to check existence of user by email
	        String checkQuery = "SELECT * FROM  users WHERE email = ?";

	        // Create a PreparedStatement to safely inject email into the query
	        PreparedStatement psCheck = conn.prepareStatement(checkQuery);
	        psCheck.setString(1, email);  // Set the email parameter

	        // Execute the query and store the result
	        ResultSet result = psCheck.executeQuery();

	        // If a result is returned, the user exists
	        if (result.next()) {
	            System.out.println(email + " already exists in database");
	            return true;  // User found
	        }
	    }

	    // Return false if connection is null or user not found
	    return false;
	}

	
//	public Boolean checkUser(String username, String password ) {
//		Boolean isValidUser = false;
//		if(conn != null) {
//			 try {
//			        String sql = "SELECT * FROM user_table WHERE username = ? AND password = ?";
//			      PreparedStatement  statement = conn.prepareStatement(sql);
//	
//			        statement.setString(1, username);
//			        statement.setString(2, password);
//			        
//			        ResultSet resultSet = statement.executeQuery();
//
//	
//			        if (resultSet.next()) {
//			            isValidUser = true;
//			        }
//
//			    } catch (SQLException e) {
//			        e.printStackTrace();
//			    }
//		}
//		return isValidUser;
//		
//		
//	}
	
//	public  ArrayList<UserModel> getAllStudentModels() throws SQLException {
//		ArrayList<UserModel> allStudentModelList = new ArrayList<UserModel>();
//		if (conn == null) {
//			System.out.println("Database Not Connected...");
//			return null;
//		}
//
//		String query = "Select * from user_table";
//		PreparedStatement ps = conn.prepareStatement(query);
//		ResultSet rs = ps.executeQuery();
//		while (rs.next()) {
//			UserData student = new UserData(
//					rs.getString("username"),
//					rs.getString("password")
//					);
//			allStudentModelList.add(student);
//		}
//		return allStudentModelList;
//	}
	


	
}
