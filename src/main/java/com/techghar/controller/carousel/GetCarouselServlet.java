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

import com.techghar.DAO.CarouselDAO;
import com.techghar.model.CarouselItem;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class CarouselServlet
 */
@WebServlet("/carousel")
public class GetCarouselServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private CarouselDAO carouselDAO ; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCarouselServlet() {
    	  }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
    	
    	try {
    		carouselDAO = new CarouselDAO();
    		System.out.println("Hello");
    		 ArrayList<CarouselItem> carouselItems = carouselDAO.getCarouselItems();
    		 for(CarouselItem item: carouselItems) {
    			 System.out.println(item);
    		 }
    	        request.setAttribute("carouselItems", carouselItems);
    	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/pages/main.jsp");
    	        dispatcher.forward(request, response);
    	}
       catch(ServletException | IOException | SQLException | ClassNotFoundException e) {
    	   ErrorHandlerUtilty.handleError(request, response, "Oops Error Occured! Try Again later ");
    	   e.printStackTrace();
       }
    }


}
