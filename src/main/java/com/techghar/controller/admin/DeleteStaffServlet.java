package com.techghar.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.UserDAO;

/**
 * Servlet implementation class DeleteStaffServlet
 */
@WebServlet("/admin-staff-delete")
public class DeleteStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * Handles the HTTP POST request to delete a staff user by their ID.
     *
     * @param request  the HttpServletRequest containing the staff ID parameter
     * @param response the HttpServletResponse used to forward or display messages
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean deleted;
        try {
            // Retrieve the 'id' parameter from the request (staff user ID to delete)
            String id = request.getParameter("id");

            // Initialize UserDAO to perform staff deletion
            UserDAO staffDAO = new UserDAO();

            // Initialize deleted flag to false before attempting deletion
            deleted = false;

            // Attempt to delete the staff user with the given ID
            deleted = staffDAO.deleteStaff(Integer.parseInt(id));

        } catch (Exception e) {
            // Log exception stack trace for debugging
            e.printStackTrace();

            // Set error message attribute to notify user/admin of failure
            request.setAttribute("errorMessage", "Added Un Successful redirecting....." + e.getMessage());

            // Set attributes to maintain page state on forwarding
            request.setAttribute("activePage", "admin-staff");
            request.setAttribute("pageContent", "staff.jsp");

            // Add JavaScript code to redirect back to admin-staff page after 2 seconds
            String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
            request.setAttribute("js", jsCode);

            // Forward to the admin dashboard page showing the message
            request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

            // Exit method after forwarding
            return;
        }

        if (deleted) {
            // If deletion succeeded, set success message
            request.setAttribute("message", "Staff Deleted Successfully redirecting.........");

        } else {
            // If deletion failed or staff not found, set error message
            request.setAttribute("errorMessage", "Staff not found or could not be deleted. redirecting .....");
        }

        // Set attributes to maintain page state on forwarding
        request.setAttribute("activePage", "admin-staff");
        request.setAttribute("pageContent", "staff.jsp");

        // Add JavaScript code to redirect back to admin-staff page after 2 seconds
        String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
        request.setAttribute("js", jsCode);

        // Forward to the admin dashboard page showing the appropriate message
        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }

	}

