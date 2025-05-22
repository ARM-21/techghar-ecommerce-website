package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.dao.CartDAO;
import com.techghar.dao.checkoutDAO;
import com.techghar.model.CartItem;
import com.techghar.utility.SessionUtil;

@WebServlet(asyncSupported = true, urlPatterns = {"/cart-check-out"})
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   try {
			      
		    	int userId = (int) SessionUtil.getAttribute(request,"id");

		        CartDAO cartDAO = new CartDAO();
		        List<CartItem> cartItems = cartDAO.getCartItems(userId);
		        double cartTotal = cartDAO.getCartTotal(userId);
		     
		        
		        request.setAttribute("cartProducts", cartItems);
		        request.setAttribute("cartTotal", cartTotal);
		        
		       
		  
		        request.getRequestDispatcher("/WEB-INF/pages/cart-check-out.jsp").forward(request, response);
		        

		    } catch (SQLException e) {
		        throw new ServletException("Database error while loading cart"+ e.getMessage());
		    } catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		 
	}


	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
			 String fullName = request.getParameter("fullName");
			 String phone = request.getParameter("phone");
			 String street = request.getParameter("street");	
			 String city = request.getParameter("city");
			 String zip = request.getParameter("zip");
			 
		
			 
		
		 String address = street + ", " + city + " - " + zip;

		 
		 		HttpSession session = request.getSession();
		 		int userId = (int) SessionUtil.getAttribute(request,"id");

		 boolean success;
		try {
		    checkoutDAO checkoutDao = new checkoutDAO(); 
		    success = checkoutDao.checkout(userId,address, phone);      
		} catch (Exception e) {
		    e.printStackTrace();
		    success = false;
		}

		if (success) {
		    session.setAttribute("orderSuccess", "Your order has been placed successfully!");
		    session.setAttribute("cartCount", 0);
		    response.sendRedirect("orders");
		} else {
		    session.setAttribute("errorMessage", "Checkout failed. Your cart may be empty.");
		    response.sendRedirect("cart");
		}

	}
}