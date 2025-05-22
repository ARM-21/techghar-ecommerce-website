package com.techghar.controller.register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.techghar.dao.RegisterDAO;
import com.techghar.model.UserModel;
import com.techghar.utility.EncryptDecryptUtil;
import com.techghar.utility.ValidatorUtility;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final EncryptDecryptUtil passUtil = new EncryptDecryptUtil();
	private static RegisterDAO daoRegister = null;
	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterController() throws ClassNotFoundException, SQLException {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("got request");
		
		try {
			daoRegister = new RegisterDAO();
			String validationMessage = validateRegistrationForm(request);
			System.out.println(validationMessage);
			if (validationMessage != null) {
				handleError(request, response, validationMessage);
				return;
			}
			

			String username = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");	
			password = EncryptDecryptUtil.encrypt(password);
			UserModel user = new UserModel( "", "" ,  email, password,  null,  "", username, null,  "");
			System.out.println(user.toString());			
			Boolean doesUserExists = daoRegister.doesUserExists(email);		
			if(doesUserExists) {
				handleError(request, response, "Email already exists with email !"+ email);
				return ;
			}			
			
			Boolean isAdded = daoRegister.addNewUser(user,request,response);
			if (isAdded == null) {
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {			
				handleSuccess(request, response, "Your account is successfully created!", "/WEB-INF/pages/home.jsp");

			}
		} 
	catch (Exception e) {
			handleError(request, response, "An unexpected error occurred. Please try again later!" +e.getMessage());
			e.printStackTrace(); // Log the exception
		}

	}

	private String validateRegistrationForm(HttpServletRequest req) {

		String username = req.getParameter("userName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("confirmPassword");

		if (ValidatorUtility.isNullOrEmpty(username))
			return "Username is required.";

		if (ValidatorUtility.isNullOrEmpty(email))
			return "Email is required.";

		if (ValidatorUtility.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidatorUtility.isNullOrEmpty(retypePassword))
			return "Please retype the password.";

		// Validate fields
		if (!ValidatorUtility.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";

		if (!ValidatorUtility.isValidEmail(email))
			return "Invalid email format.";
		if (!ValidatorUtility.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidatorUtility.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		return null; // All validations passed
	}
	
	
	private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
			throws ServletException, IOException {
		request.setAttribute("success", message);
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("error", message);
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
}


