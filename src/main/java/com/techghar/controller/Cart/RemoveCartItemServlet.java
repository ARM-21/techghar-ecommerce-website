package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.techghar.DAO.CartDAO;
import com.techghar.utility.SessionUtil;


@WebServlet(asyncSupported = true, urlPatterns = {"/RemoveFromCart"})
public class RemoveCartItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        try {
            cartDAO = new CartDAO();
        } catch (Exception e) {
            throw new ServletException("Failed to initialize CartDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) SessionUtil.getAttribute(request,"id");
        System.out.println(userId);

        try {
            int productId = Integer.parseInt(request.getParameter("id"));

            boolean removed = cartDAO.removeCartItem(userId, productId);

            if (removed) {
                int cartCount = cartDAO.getCartItemCount(userId);
                session.setAttribute("cartCount", cartCount);
            } else {
                session.setAttribute("errorMessage", "Failed to remove item.");
            }

            response.sendRedirect("cart");

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
}
	
