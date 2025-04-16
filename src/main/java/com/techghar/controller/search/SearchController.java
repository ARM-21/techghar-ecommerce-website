package com.techghar.controller.search;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.ProductDAO;
import com.techghar.utility.ErrorHandlerUtilty;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search-catalog")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		ProductDAO dao;
		try {
			dao = new ProductDAO();
	
		request.setAttribute("brands", dao.getAllBrands());
		request.setAttribute("categories", dao.getAllCategories());
		
		request.setAttribute("products", dao.getAllProducts());
		
		request.setAttribute("pageContent", "/WEB-INF/pages/search.jsp");
		request.getRequestDispatcher("WEB-INF/pages/home.jsp").forward(request, response);
		}catch(SQLException | ServletException | IOException | ClassNotFoundException e) {
			ErrorHandlerUtilty.handleError(request, response, "Oops! Error Occured ! Try Again Later");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
