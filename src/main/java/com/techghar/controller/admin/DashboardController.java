package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.StatisticDAO;

/**
 * Servlet implementation class DashboardController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/dashboard","/view-stat"})
public class DashboardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public DashboardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		 try {

				StatisticDAO dao = new StatisticDAO();
				request.setAttribute("activePage", "dashboard");
				request.setAttribute("totalProducts", dao.getTotalProducts());
				 request.setAttribute("newOrders", dao.getRecentOrders());
				 request.setAttribute("totalCustomers", dao.getTotalCustomers());
				 request.setAttribute("totalRevenue", dao.getTotalCustomers());
					//main section
					request.setAttribute("pageContent", "/WEB-INF/pages/admin/stats.jsp");
					request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
			} catch (SQLException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
