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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean deleted;
	    try {
	    	String id = request.getParameter("id");
		    UserDAO staffDAO = new UserDAO();
		     deleted = false;
	        deleted = staffDAO.deleteStaff(Integer.parseInt(id));
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Added Un Successful redirecting....." + e.getMessage());
			request.setAttribute("activePage", "admin-staff");
			request.setAttribute("pageContent", "staff.jsp");
			
			String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
			request.setAttribute("js", jsCode);
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
	        return;
	    }
	    
	    if (deleted) {
	    	request.setAttribute("message", "Staff Deleted Successfully redirecting.........");
			request.setAttribute("activePage", "admin-staff");
			request.setAttribute("pageContent", "staff.jsp");
			String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
			request.setAttribute("js", jsCode);
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
	    } else {
	        request.setAttribute("errorMessage", "Staff not found or could not be deleted. redirecting .....");
	        request.setAttribute("activePage", "admin-staff");
			request.setAttribute("pageContent", "staff.jsp");
			String jsCode = "setTimeout(function() { window.location.href = 'admin-staff'; }, 2000);";
			request.setAttribute("js", jsCode);
	        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
	    }
	}
	}

