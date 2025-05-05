package com.techghar.controller.Cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.DAO.CartDAO;
import com.techghar.model.CartItem;



@WebServlet(asyncSupported = true, urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    try {
	      
	      
	      
	        int userId = 6;

	       
	        CartDAO cartDAO = new CartDAO();
	        List<CartItem> cartItems = cartDAO.getCartItems(userId);
	        double cartTotal = cartDAO.getCartTotal(userId);

	        
	        request.setAttribute("cartProducts", cartItems);
	        request.setAttribute("cartTotal", cartTotal);
	        
	       
	        request.getRequestDispatcher("/WEB-INF/pages/cart.jsp").forward(request, response);
	        
	        

	    } catch (SQLException e) {
	        throw new ServletException("Database error while loading cart", e);
	    } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
