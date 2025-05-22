package com.techghar.controller.admin;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.techghar.dao.CarouselDAO;
import com.techghar.model.CarouselItem;
import com.techghar.utility.ImageUtility;

@WebServlet("/add-carousel-item")
@MultipartConfig
public class AddCarouselItemServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "admin-carousel");
        request.setAttribute("pageContent", "/WEB-INF/pages/admin/addCarouselItem.jsp" );
        // Forward to the carousel management page
        request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP POST request for adding a new carousel item.
     *
     * @param request  the HttpServletRequest containing form data
     * @param response the HttpServletResponse used to redirect or forward the response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve title and description from the form parameters
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // Retrieve the uploaded image file part from the request
        Part filePart = request.getPart("image");

        // Check if an image was uploaded; if not, set error message and forward back to form
        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Image is required.");
            request.getRequestDispatcher("/WEB-INF/views/admin/addCarouselItem.jsp").forward(request, response);
            return;
        }

        try {
            // Save the uploaded image and get its file path
            String filePath = ImageUtility.fileWriter(request, response, filePart);
            System.out.println(filePath);

            // Create a new CarouselItem object using the submitted data
            CarouselItem item = new CarouselItem(title, description, filePath);

            // Attempt to save the new carousel item using the DAO
            boolean success = new CarouselDAO().addCarouselItem(item);

            if (success) {
                // On success, store confirmation message in session and redirect to carousel page
                request.getSession().setAttribute("message", "Carousel item added successfully!");
                response.sendRedirect(request.getContextPath() + "/admin-carousel");
            } else {
                // On failure, show error message and forward back to form
                request.setAttribute("errorMessage", "Failed to add carousel item.");
                request.getRequestDispatcher("/WEB-INF/views/admin/addCarouselItem.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            // Handle potential database or class loading exceptions
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }

}
