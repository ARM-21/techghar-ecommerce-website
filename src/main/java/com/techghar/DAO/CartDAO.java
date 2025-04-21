//package com.techghar.DAO;
//
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.techghar.model.CartItem;
//
///**
// * Servlet implementation class CartDAO
// */
//@WebServlet(asyncSupported = true, urlPatterns = {"/add-to-cart","view-cart"})
//public class CartDAO extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public CartDAO() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//
//        if ("/view-cart".equals(action)) {
//            viewCart(request, response);  // Show cart
//        } else {
//            response.getWriter().append("Invalid action");
//        }
//    }
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getServletPath();
//
//        if ("/add-to-cart".equals(action)) {
////            addToCart(request, response);  // Add product to cart
//        } else {
//            response.getWriter().append("Invalid action");
//        }
//    }
//    
//    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        CartItem cart = (CartItem) session.getAttribute("cart");
//
////        if (cart != null && !cart.getItems().isEmpty()) {
////            
////            request.setAttribute("cart", cart);
////            request.getRequestDispatcher("/viewCart.jsp").forward(request, response);
////        } else {
////            response.getWriter().append("Your cart is empty.");
////        }
////    }
//}
//
//}
