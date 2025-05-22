package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.ReportDAO;
import com.techghar.model.BrandSalesReport;
import com.techghar.model.CategorySalesReport;
import com.techghar.model.CustomerPurchaseReport;

/**
 * AdminReportDashboardServlet is responsible for displaying the main report dashboard
 * in the admin panel. It retrieves and aggregates various sales analytics data
 * and forwards them to the report dashboard view.
 */
@WebServlet("/admin-reports")
public class AdminReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Handles GET requests to display the report dashboard.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Create DAO for reports
            ReportDAO reportDAO = new ReportDAO();
            
            // Calculate date range for monthly reports (last 30 days)
            Calendar calendar = Calendar.getInstance();
            Date endDate = calendar.getTime(); // today

            calendar.add(Calendar.DAY_OF_MONTH, -6);
            Date startDate = calendar.getTime(); // 6 days before today

            
            // Get category sales report
            List<CategorySalesReport> categorySalesReport = reportDAO.getCategorySalesReport(startDate, endDate);
            
            // Get brand sales report
            List<BrandSalesReport> brandSalesReport = reportDAO.getBrandSalesReport(startDate, endDate);
            
            // Get weekly customer purchase report
            List<CustomerPurchaseReport> customerPurchaseReport = reportDAO.getWeeklyCustomerPurchaseReport();
            
            // Set reports as request attributes
            request.setAttribute("categorySalesReport", categorySalesReport);
            request.setAttribute("brandSalesReport", brandSalesReport);
            request.setAttribute("customerPurchaseReport", customerPurchaseReport);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            
            // Forward to the report dashboard page
            request.setAttribute("activePage", "admin-reports");
            request.setAttribute("pageContent", "adminReport.jsp");
            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp")
            .forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            e.printStackTrace();
            
            // Set error message and forward to error page
            request.setAttribute("errorMessage", "Failed to load report data: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
