package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.CarouselDAO;
import com.techghar.model.CarouselItem;

/**
 * AdminCarouselServlet is responsible for displaying all carousel items in the
 * admin dashboard. It retrieves carousel items from the database and forwards
 * them to the admin carousel management page.
 */
@WebServlet("/admin-carousel")
public class AdminCarouselServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Handles GET requests to display the carousel management page.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Create DAO and get all carousel items
            CarouselDAO carouselDAO = new CarouselDAO();
            ArrayList<CarouselItem> carouselItems = carouselDAO.getCarouselItems();
            
            // Set carousel items as request attribute
            request.setAttribute("carouselItems", carouselItems);
            request.setAttribute("activePage", "admin-carousel");
            request.setAttribute("pageContent", "carouselManagement.jsp" );
            // Forward to the carousel management page
            request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp")
                  .forward(request, response);
            
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            e.printStackTrace();
            
            // Set error message and forward to error page
            request.setAttribute("errorMessage", "Failed to load carousel items: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
