package com.techghar.controller.cart;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import com.techghar.dao.OrderDAO;
import com.techghar.model.DeliveryModel;
import com.techghar.model.OrderItem;
import com.techghar.model.OrderModel;
import com.techghar.utility.SessionUtil;


@WebServlet(asyncSupported = true, urlPatterns = {"/orders"})
public class OrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = (int) SessionUtil.getAttribute(request,"id");
		
		  try {
		        OrderDAO orderDAO = new OrderDAO();  
		        
		        List<OrderModel> orders = orderDAO.getOrdersByUserId(userId);
		        
		        for (OrderModel order : orders) {
		            List<OrderItem> items = orderDAO.getOrderItems(order.getId());
		            order.setOrderItems(items);

		            OrderModel delivery = orderDAO.getOrderWithDeliveryById(order.getId());
		            if (delivery != null) {
		                order.setDeliveryModel(delivery.getDeliveryModel());
		            }
		        }
		        
		        
		        
		        
		        request.setAttribute("orders", orders);
		        request.getRequestDispatcher("/WEB-INF/pages/orders.jsp").forward(request, response);
		         
		    } catch (SQLException | ClassNotFoundException e) {
		        e.printStackTrace();
		        response.sendRedirect("error.jsp");
		    }
		
		
	}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}