package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.dao.CarouselDAO;

/**
 * DeleteCarouselItemServlet is responsible for handling the deletion of
 * carousel items from the database. It processes delete requests and
 * removes carousel items based on their ID.
 */
@WebServlet("/delete-carousel-item")
public class DeleteCarouselServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Handles GET requests to process carousel item deletion.
     *
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Get carousel item ID from request parameter
            int id = Integer.parseInt(request.getParameter("id"));
            
            // Delete carousel item from database
            CarouselDAO carouselDAO = new CarouselDAO();
            boolean success = carouselDAO.deleteCarouselItem(id);
            
            if (success) {
                // Set success message and redirect to carousel management page
                request.getSession().setAttribute("message", "Carousel item deleted successfully!");
            } else {
                // Set error message if database operation failed
                request.getSession().setAttribute("errorMessage", "Failed to delete carousel item from database.");
            }
            
            // Redirect back to carousel management page
            response.sendRedirect(request.getContextPath() + "/admin-carousel");
            
        } catch (NumberFormatException e) {
            // Handle invalid ID parameter
            request.setAttribute("errorMessage", "Invalid carousel item ID.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        } catch (ClassNotFoundException | SQLException e) {
            // Log the exception
            e.printStackTrace();
            
            // Set error message and forward to error page
            request.setAttribute("errorMessage", "Failed to delete carousel item: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
