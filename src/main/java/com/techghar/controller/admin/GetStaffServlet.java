package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.dao.UserDAO;
import com.techghar.model.UserModel;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class GetStaffServlet
 */
@WebServlet("/admin-staff")
public class GetStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetStaffServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    /**
     * Handles HTTP GET request to fetch and display all staff users in admin panel.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Instantiate DAO to access user data
            UserDAO dao = new UserDAO();

            // Fetch list of all staff users
            List<UserModel> staffList = dao.getAllStaff();

            // Set attributes for JSP rendering
            request.setAttribute("staffList", staffList);
            request.setAttribute("activePage", "admin-staff");
            request.setAttribute("pageContent", "staff.jsp");

            // Forward to the admin dashboard JSP to display the staff list
            request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            // Log exception and handle error gracefully
            e.printStackTrace();
            ErrorHandlerUtilty.handleErrorAdmin(request, response, e.getMessage());
        }
    }

    

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
