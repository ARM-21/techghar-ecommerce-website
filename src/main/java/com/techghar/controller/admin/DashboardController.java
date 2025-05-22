package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.OrderDAO;
import com.techghar.dao.StatisticDAO;

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
     * Handles the HTTP GET request to load the admin dashboard statistics.
     *
     * @param request  the HttpServletRequest to pass data to the view
     * @param response the HttpServletResponse used to forward the request
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Create a DAO to fetch statistics data from the database
            StatisticDAO dao = new StatisticDAO();

            // Set the current active page for UI highlighting
            request.setAttribute("activePage", "dashboard");

            // Fetch and set various dashboard statistics as request attributes
            request.setAttribute("totalProducts", dao.getTotalProducts());
            request.setAttribute("totalOrders", dao.getNewOrdersCount());
            request.setAttribute("newOrders", dao.getRecentOrders());
            request.setAttribute("totalCustomers", dao.getTotalCustomers());
            request.setAttribute("totalRevenue", dao.getTotalRevenue());

            // Specify the content JSP for the main section of the dashboard
            request.setAttribute("pageContent", "/WEB-INF/pages/admin/stats.jsp");

            // Forward to the admin dashboard layout JSP
            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

        } catch (SQLException | ClassNotFoundException e) {
            // Print stack trace for debugging
            e.printStackTrace();

            // You may want to add error handling here, e.g. forward to error page or show message
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	}

}
