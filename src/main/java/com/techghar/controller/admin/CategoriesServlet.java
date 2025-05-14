package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.techghar.DAO.ProductDAO;
import com.techghar.model.Category;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet("/view-categories-admin")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private ProductDAO dao;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() throws ClassNotFoundException, SQLException {
    	dao = new ProductDAO();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        List<Category> categories;
		try {
			categories = dao.getAllCategories();
	        request.setAttribute("categoryList", categories);
	        request.setAttribute("activePage", "view-categories-admin");
	        request.setAttribute("pageContent", "view-categories.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
			categories = dao.getAllCategories();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String category = (String) request.getAttribute("categoryName");
		
		
		
		
		doGet(request, response);
	}

}
