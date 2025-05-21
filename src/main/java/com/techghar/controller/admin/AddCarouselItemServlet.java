package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.techghar.DAO.CarouselDAO;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Part filePart = request.getPart("image");

        if (filePart == null || filePart.getSize() == 0) {
            request.setAttribute("errorMessage", "Image is required.");
            request.getRequestDispatcher("/WEB-INF/views/admin/add-carousel-item.jsp").forward(request, response);
            return;
        }

        try {
            String filePath = ImageUtility.fileWriter(request, response, filePart);
            CarouselItem item = new CarouselItem(title, description, "/" + filePath);

            boolean success = new CarouselDAO().addCarouselItem(item);

            if (success) {
                request.getSession().setAttribute("message", "Carousel item added successfully!");
                response.sendRedirect(request.getContextPath() + "/admin-carousel");
            } else {
                request.setAttribute("errorMessage", "Failed to add carousel item.");
                request.getRequestDispatcher("/WEB-INF/views/admin/add-carousel-item.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Database error: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
