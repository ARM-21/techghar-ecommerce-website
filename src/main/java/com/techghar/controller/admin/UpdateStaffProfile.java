package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.UserDAO;

/**
 * Servlet implementation class UpdateStaffProfile
 */

@WebServlet(asyncSupported = true, urlPatterns = { "/update-staff-profile"})
public class UpdateStaffProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserDAO dao = new UserDAO();
            
            int id = Integer.parseInt(request.getParameter("id"));
            request.setAttribute("userDetails", dao.getUserById(id));
			request.setAttribute("pageContent", "/WEB-INF/pages/admin/updateStaffProfile.jsp");
			request.setAttribute("activePage", "admin-staff");
			request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            // Handle invalid id format
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid user ID.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            // Handle database errors
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error loading user profile.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


	

}
