package com.techghar.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techghar.DAO.UserDAO;
import com.techghar.utility.ErrorHandlerUtiltiy;

/**
 * Servlet implementation class UserController
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/user-profile","/update-profile"})
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		// TODO Auto-generated method stub
		
		
		
		try {
			
			System.out.println(request.getRequestURI());
			if(request.getRequestURI().equals("/update-profile")) {
				UserDAO dao = new UserDAO();
				request.setAttribute("userDetails",dao.getUserById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageContent", "/WEB-INF/pages/updateProfile.jsp");
			}
			else {
				UserDAO dao = new UserDAO();
				request.setAttribute("userDetails",dao.getUserById(Integer.parseInt(request.getParameter("id"))));
				request.setAttribute("pageContent", "/WEB-INF/pages/profile.jsp");
			}
			
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		} catch (IOException | ServletException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			ErrorHandlerUtiltiy.handleError(request, response,"Oops! Error Occured While fetching user details");
			e.printStackTrace();
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
