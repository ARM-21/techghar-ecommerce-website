package com.techghar.utility;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorHandlerUtilty {

	
	public static void handleError(HttpServletRequest request, HttpServletResponse response, String message){
	    // Set the error message as a request attribute
	    request.setAttribute("error", message);
	    
	    // Optional: Set page content to error page
	    request.setAttribute("pageContent", "/WEB-INF/pages/error.jsp");
	    
	    // Forward the request to the main page (home.jsp or error.jsp)
	    try {
			request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}
	public static void handleErrorAdmin(HttpServletRequest request, HttpServletResponse response, String message){
	    // Set the error message as a request attribute
	    request.setAttribute("error", message);
	    
	    // Optional: Set page content to error page
	    request.setAttribute("pageContent", "/WEB-INF/pages/error.jsp");
	    
	    // Forward the request to the main page (home.jsp or error.jsp)
	    try {
			request.getRequestDispatcher("/WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		} 
	}
}
