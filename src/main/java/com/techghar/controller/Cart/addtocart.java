package com.techghar.controller.Cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.DAO.CartDAO;
import com.techghar.DAO.ProductDAO;
import com.techghar.model.CartItem;
import com.techghar.model.Product;
import com.techghar.utility.SessionUtil;


@WebServlet(asyncSupported = true, urlPatterns = {"/add-to-cart"})

public class addtocart  extends HttpServlet {
    
    private CartDAO cartDAO;
    
    @Override
    public void init() throws ServletException {
        try {
            cartDAO = new CartDAO();
        } catch (ClassNotFoundException | SQLException e) {
            throw new ServletException("Error initializing CartDAO", e);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        int userId = (int) SessionUtil.getAttribute(request,"id");
        
    
        try {
         
            int productId = Integer.parseInt(request.getParameter("productId"));
//            int quantity = Integer.parseInt(request.getParameter("quantity"));
            ProductDAO prodDao = new ProductDAO();
            Product product = prodDao.getProductById(productId);
            boolean success = cartDAO.addToCart(userId, productId, 1);
            
            if (success) {
                int cartCount = cartDAO.getCartItemCount(userId);
                session.setAttribute("cartCount", cartCount);
                session.setAttribute("maxStock", product.getStock());
            } else {
                
                session.setAttribute("errorMessage", "Failed to add product to cart.");
            }
            
            
          
            response.sendRedirect("cart");
            
        } catch (SQLException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Database error: " + e.getMessage());
            response.sendRedirect("error.jsp");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Invalid input data");
            response.sendRedirect("error.jsp");
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
    }
}