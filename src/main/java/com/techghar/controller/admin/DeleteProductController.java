package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;

/**
 * Servlet implementation class DeleteProductController
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/product-delete" })
public class DeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			ProductDAO dao = new ProductDAO();
			String id = request.getParameter("id");
			
			if(id != null) {
				int idParam = Integer.parseInt(id);
				System.out.println(idParam);
				Boolean isDeleted = dao.deleteProductById(idParam);
				System.out.println("product is deleted "+ isDeleted);
				
				if(isDeleted) {

					response.sendRedirect("admin-products?message=Product+deleted");
				}
			}
			
			 response.setStatus(404);
			 return;
			
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			response.sendError(501, "server Side error occured");
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
//		dao.deleteProductById()
		
//		System.out.println("product Added" + productAddedSuccessfully);
//		if (productAddedSuccessfully) {
//			request.setAttribute("message", "✅ Product added successfully!");
//			request.setAttribute("messageType", "success");
//			
//		} else {
//			request.setAttribute("message", "❌ Failed to add product.");
//			request.setAttribute("messageType", "error");
//			return;
//		}
		
	}

}
