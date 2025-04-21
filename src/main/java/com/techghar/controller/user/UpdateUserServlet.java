package com.techghar.controller.user;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.techghar.DAO.UserDAO;
import com.techghar.model.UserModel;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/update-profile-post", "/update-ad-profile-post"})
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
		
			UserDAO dao = new UserDAO();
			UserModel user = new UserModel();
			user.setFirstName(request.getParameter("firstName"));
			user.setLastName(request.getParameter("lastName"));
			String dateStr = request.getParameter("dob");
			Date sqlDate = Date.valueOf(dateStr);
			user.setDob(sqlDate);
//			user.setDob((java.sql.Date) from.parse(request.getParameter("dob")));
			user.setEmail(request.getParameter("email"));
			user.setPhone(request.getParameter("phone"));
			user.setGender(request.getParameter("gender"));
			user.setAddress(request.getParameter("address"));
			user.setPassword(request.getParameter("password"));
		if(dao.updateUser(user, request, response)) {
			
			if(request.getRequestURI().equals("/update-ad-profile-post")) {
				System.out.println("Admin update request");
				request.setAttribute("success", "User Details updated successfully!");
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
				request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request,response);
			}
			else {
				request.setAttribute("success", "User Details updated successfully!");
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
				request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request,response);
			}
		
		}
			
		} catch (ClassNotFoundException | SQLException | ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			if(request.getRequestURI().equals("/update-ad-profile-post")) {
				request.setAttribute("error", "Update User Details unsuccessfully! Try Again Later");
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
				request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request,response);
			}
			else {
				request.setAttribute("error", "Update User Details unsuccessfully! Try Again Later");
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
				request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request,response);
			}
			
//			ErrorHandlerUtilty.handleError(request, response, e.getMessage());
		}
		
	}
   
}



