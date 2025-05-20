package com.techghar.controller.home;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import com.techghar.DAO.CarouselDAO;
import com.techghar.DAO.ProductDAO;
import com.techghar.model.CarouselItem;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;

@WebServlet(asyncSupported = true, urlPatterns = { "/home","/products" })
public class GetHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// carousel data

		try {
			CarouselDAO carouselDAO = new CarouselDAO();
			ArrayList<CarouselItem> carouselItems = carouselDAO.getCarouselItems();
			System.out.println("Database Connected Successfully");
			System.out.println(request.getRequestURI());
			if(request.getRequestURI().equals("/products")) {
				request.setAttribute("activePage", "products");	
			}
			
			else {
				request.setAttribute("carouselItems", carouselItems);
				request.setAttribute("activePage", "home");
			}
			
			// product data
			ProductDAO productDAO = new ProductDAO();
			ArrayList<Product> products = productDAO.getAllProducts();

			request.setAttribute("products", products);
			// main section
			request.setAttribute("pageContent", "./main.jsp");
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException | IOException | ServletException e) {
			e.printStackTrace();
			ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured Try Again Later");
		}
	}

}