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
import com.techghar.DAO.RegisterDAO;
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

			// Assuming password validation is already done in validateRegistrationForm
			password = passUtil.encrypt(password);

//			Part image = req.getPart("image");
//			String imageUrl = imageUtil.getImageNameFromPart(image);

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
//		String firstName = req.getParameter("firstName");
//		String lastName = req.getParameter("lastName");
		String username = req.getParameter("userName");
//		String dobStr = req.getParameter("dob");
//		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
//		String number = req.getParameter("phoneNumber");
		String password = req.getParameter("password");
		String retypePassword = req.getParameter("confirmPassword");
//		String address = req.getParameter("address");

		// Check for null or empty fields first
//		if (ValidatorUtility.isNullOrEmpty(firstName))
//			return "First name is required.";
//		if (ValidatorUtility.isNullOrEmpty(lastName))
//			return "Last name is required.";
		if (ValidatorUtility.isNullOrEmpty(username))
			return "Username is required.";
//		if (ValidatorUtility.isNullOrEmpty(dobStr))
//			return "Date of birth is required.";
//		if (ValidatorUtility.isNullOrEmpty(gender))
//			return "Gender is required.";
		if (ValidatorUtility.isNullOrEmpty(email))
			return "Email is required.";
//		if (ValidatorUtility.isNullOrEmpty(number))
//			return "Phone number is required.";
		if (ValidatorUtility.isNullOrEmpty(password))
			return "Password is required.";
		if (ValidatorUtility.isNullOrEmpty(retypePassword))
			return "Please retype the password.";
//		if (ValidatorUtility.isNullOrEmpty(address))
//			return "Please retype the address.";

		// Convert date of birth
//		LocalDate dob;
//		try {
//			dob = LocalDate.parse(dobStr);
//		} catch (Exception e) {
//			return "Invalid date format. Please use YYYY-MM-DD.";
//		}

		// Validate fields
		if (!ValidatorUtility.isAlphanumericStartingWithLetter(username))
			return "Username must start with a letter and contain only letters and numbers.";
//		if (!ValidatorUtility.isValidGender(gender))
//			return "Gender must be 'male' or 'female'.";
		if (!ValidatorUtility.isValidEmail(email))
			return "Invalid email format.";
//		if (!ValidatorUtility.isValidPhoneNumber(number))
//			return "Phone number must be 10 digits and start with 98.";
		if (!ValidatorUtility.isValidPassword(password))
			return "Password must be at least 8 characters long, with 1 uppercase letter, 1 number, and 1 symbol.";
		if (!ValidatorUtility.doPasswordsMatch(password, retypePassword))
			return "Passwords do not match.";

		// Check if the date of birth is at least 16 years before today
//		if (!ValidatorUtility.isAgeAtLeast16(dob))
//			return "You must be at least 16 years old to register.";

//		try {
//			Part image = req.getPart("image");
//			if (!ValidatorUtility.isValidImageExtension(image))
//				return "Invalid image format. Only jpg, jpeg, png, and gif are allowed.";
//		} catch (IOException | ServletException e) {
//			return "Error handling image file. Please ensure the file is valid.";
//		}

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

//req.setAttribute("phoneNumber", req.getParameter("phoneNumber"));
//req.setAttribute("subject", req.getParameter("subject"));
//req.setAttribute("dob", req.getParameter("dob"));
//req.setAttribute("gender", req.getParameter("gender"));
//req.setAttribute("firstName", req.getParameter("firstName"));
//req.setAttribute("lastName", req.getParameter("lastName"));
//String firstName = request.getParameter("firstName");
//String lastName = request.getParameter("lastName");
//String address = request.getParameter("address");
//String phone = request.getParameter("phoneNumber");
//String gender = request.getParameter("gender");

//SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//Date dob = null;
//try {
//	java.util.Date utilDate = formatter.parse(request.getParameter("dob"));
//	 dob = new java.sql.Date(utilDate.getTime());
//} catch (ParseException e) {
//	e.printStackTrace();
//}


//try {
//if (uploadImage(request)) {
//	handleSuccess(req, resp, "Your account is successfully created!", "/WEB-INF/pages/login.jsp");
//} else {
//	handleError(req, resp, "Could not upload the image. Please try again later!");
//}
//} catch (IOException | ServletException e) {
//handleError(req, resp, "An error occurred while uploading the image. Please try again later!");
//e.printStackTrace(); // Log the exception
//}
