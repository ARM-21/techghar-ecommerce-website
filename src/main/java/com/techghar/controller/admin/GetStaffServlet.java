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

import com.techghar.DAO.UserDAO;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO dao;
		try {
			dao = new UserDAO();
	        List<UserModel> staffList =dao.getAllStaff() ;
	        request.setAttribute("staffList", staffList);
	        request.setAttribute("activePage", "admin-staff");
	        request.setAttribute("pageContent", "staff.jsp");
	        request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
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
