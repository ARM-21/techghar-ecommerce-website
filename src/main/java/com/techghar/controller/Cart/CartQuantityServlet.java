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
import com.techghar.utility.SessionUtil;

@WebServlet("/Cartquantity")
public class CartQuantityServlet extends HttpServlet {

    private CartDAO cartDAO;

    @Override
    public void init() throws ServletException {
        try {
            cartDAO = new CartDAO();
        } catch (Exception e) {
            throw new ServletException("Error initializing DAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        int userId = (int) SessionUtil.getAttribute(request,"id");

        String action = request.getParameter("action");
        int productId = Integer.parseInt(request.getParameter("id"));

        try {
            boolean result = false;

            if ("inc".equals(action)) {
                result = cartDAO.incrementQuantity(userId, productId);
            } else if ("dec".equals(action)) {
                result = cartDAO.decrementQuantity(userId, productId);
            }

            if (!result) {
                session.setAttribute("errorMessage", "Failed to update quantity.");
            }

            response.sendRedirect("cart"); 

        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "Error updating quantity: " + e.getMessage());
            response.sendRedirect("error.jsp");
        }
    }
}
