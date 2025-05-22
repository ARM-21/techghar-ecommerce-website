package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.UserDAO;
import com.techghar.model.UserModel;

/**
 * Servlet implementation class GetCustomerController
 */
@WebServlet("/admin-customers")
public class GetCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCustomerController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UserDAO userDAO = new UserDAO();
            List<UserModel> customers = userDAO.getAllCustomers();
            request.setAttribute("customers", customers);
            request.setAttribute("activePage", "admin-customers");
	        request.setAttribute("pageContent", "customers.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
        } catch (SQLException | ClassNotFoundException e) {
            throw new ServletException("Cannot retrieve customers", e);
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
