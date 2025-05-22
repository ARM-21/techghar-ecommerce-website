package com.techghar.controller.carousel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.techghar.dao.CarouselDAO;
import com.techghar.model.CarouselItem;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class CarouselServlet
 * Handles requests to fetch and display carousel items on the main page.
 */
@WebServlet("/carousel")
public class GetCarouselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CarouselDAO carouselDAO; // Data Access Object for carousel-related operations

    /**
     * Default constructor.
     */
    public GetCarouselServlet() {
        // No specific initialization needed in the constructor
    }

    /**
     * Handles GET requests to retrieve carousel items from the database and forward them to the main page.
     *
     * @param request  the HttpServletRequest object
     * @param response the HttpServletResponse object
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Initialize the CarouselDAO to interact with the database
            carouselDAO = new CarouselDAO();

            // Debug print to confirm servlet execution
            System.out.println("Hello");

            // Retrieve a list of carousel items from the database
            ArrayList<CarouselItem> carouselItems = carouselDAO.getCarouselItems();

            // Debug print each carousel item
            for (CarouselItem item : carouselItems) {
                System.out.println(item);
            }

            // Set carousel items as a request attribute for access in the JSP page
            request.setAttribute("carouselItems", carouselItems);

            // Forward the request to the main JSP page to display the carousel
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/main.jsp");
            dispatcher.forward(request, response);
        }
        catch (ServletException | IOException | SQLException | ClassNotFoundException e) {
            // Handle any exceptions using a custom error handler utility
            ErrorHandlerUtilty.handleError(request, response, "Oops Error Occured! Try Again later ");
            e.printStackTrace();
        }
    }
}

