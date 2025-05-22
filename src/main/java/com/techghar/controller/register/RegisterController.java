package com.techghar.controller.register;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.techghar.dao.RegisterDAO;
import com.techghar.model.UserModel;
import com.techghar.utility.EncryptDecryptUtil;
import com.techghar.utility.ValidatorUtility;

/**
 * Servlet implementation class RegisterController
 * Handles user registration functionality including form validation,
 * user creation, and error/success handling.
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Utility instance for password encryption
	private static final EncryptDecryptUtil passUtil = new EncryptDecryptUtil();

	// DAO object for database interactions related to user registration
	private static RegisterDAO daoRegister = null;

	/**
	 * Default constructor
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public RegisterController() throws ClassNotFoundException, SQLException {
		// Empty constructor, DAO initialization deferred to doPost
	}

	/**
	 * Handles GET requests to display the registration page.
	 * Sets the "pageContent" attribute to the registration JSP and forwards
	 * the request to the main home JSP for layout.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles POST requests for user registration.
	 * Validates form input, checks for existing user, encrypts password,
	 * adds new user to database, and handles success/error responses.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("got request");

		try {
			// Initialize DAO for registration operations
			daoRegister = new RegisterDAO();

			// Validate registration form inputs
			String validationMessage = validateRegistrationForm(request);
			System.out.println(validationMessage);
			if (validationMessage != null) {
				// If validation fails, show error and return
				handleError(request, response, validationMessage);
				return;
			}

			// Extract form parameters
			String username = request.getParameter("userName");
			String email = request.getParameter("email");
			String password = request.getParameter("password");

			// Encrypt the password before storing
			password = EncryptDecryptUtil.encrypt(password);

			// Create a new user model object
			UserModel user = new UserModel("", "", email, password, null, "", username, null, "");
			System.out.println(user.toString());

			// Check if user with the email already exists
			Boolean doesUserExists = daoRegister.doesUserExists(email);
			if (doesUserExists) {
				// If user exists, display error and return
				handleError(request, response, "Email already exists with email !" + email);
				return;
			}

			// Attempt to add the new user to the database
			Boolean isAdded = daoRegister.addNewUser(user, request, response);

			if (isAdded == null) {
				// If DAO returned null, likely a server error
				handleError(request, response, "Our server is under maintenance. Please try again later!");
			} else if (isAdded) {
				// On successful addition, show success message
				handleSuccess(request, response, "Your account is successfully created!", "/WEB-INF/pages/home.jsp");
			}
		} catch (Exception e) {
			// Catch any unexpected exceptions and display generic error
			handleError(request, response, "An unexpected error occurred. Please try again later!" + e.getMessage());
			e.printStackTrace(); // Log the exception for debugging
		}

	}

	/**
	 * Validates registration form parameters.
	 * Checks for required fields, correct formats, and matching passwords.
	 * 
	 * @param req HttpServletRequest object containing form parameters
	 * @return validation error message string if validation fails, null if all pass
	 */
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

		// Validate username format: must start with letter, letters and digits only
		if (!ValidatorUtility.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";

		// Validate email format
		if (!ValidatorUtility.isValidEmail(email))
			return "Invalid email format.";

		// Validate password strength requirements
		if (!ValidatorUtility.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";

		// Check if password and confirm password match
		if (!ValidatorUtility.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		// All validations passed
		return null;
	}

	/**
	 * Handles successful registration by setting success message and forwarding
	 * to the specified page.
	 * 
	 * @param request      HttpServletRequest object
	 * @param response     HttpServletResponse object
	 * @param message      success message to display
	 * @param redirectPage page to forward to after success
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleSuccess(HttpServletRequest request, HttpServletResponse response, String message, String redirectPage)
			throws ServletException, IOException {
		request.setAttribute("success", message);
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}

	/**
	 * Handles registration errors by setting error message and forwarding
	 * back to the registration page.
	 * 
	 * @param request  HttpServletRequest object
	 * @param response HttpServletResponse object
	 * @param message  error message to display
	 * @throws ServletException
	 * @throws IOException
	 */
	private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
			throws ServletException, IOException {
		request.setAttribute("error", message);
		request.setAttribute("pageContent", "/WEB-INF/pages/register.jsp");
		request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
	}
}
