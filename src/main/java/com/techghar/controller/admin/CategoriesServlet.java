package com.techghar.controller.admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.techghar.dao.CategoryDAO;
import com.techghar.dao.ProductDAO;
import com.techghar.model.Category;
import com.techghar.utility.ErrorHandlerUtilty;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet("/view-categories")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private CategoryDAO dao;
    /**
     * @throws SQLException 
     * @throws ClassNotFoundException 
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() throws ClassNotFoundException, SQLException {
    	
   
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        List<Category> categories;
		try {
			dao = new CategoryDAO();
			categories = dao.getAllCategories();
	        request.setAttribute("categoryList", categories);
	        request.setAttribute("activePage", "view-categories");
	        request.setAttribute("pageContent", "viewCategories.jsp");
	        request.getRequestDispatcher( "WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Server is facing issue while fetching category");
		}

    }


}
