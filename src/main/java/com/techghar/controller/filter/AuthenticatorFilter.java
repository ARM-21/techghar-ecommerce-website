package com.techghar.controller.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.techghar.utility.SessionUtil;
import com.techghar.utility.CookieUtility;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticatorFilter implements Filter {
	private static final long serialVersionUID = 1L;
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";
    
    
    @Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

    private static final String[] PUBLIC_PAGES = {
        "/", "/home", "/about", "/contact", "/products", "/product-details","search-catalog", LOGIN, REGISTER
    };

    private static final String[] USER_PAGES = {
        "/user-profile", "/checkout", "/cartlist", "/orderlist", "/update-profile"
    };

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        boolean isLoggedIn = SessionUtil.getAttribute(request, "username") != null;
        String role = CookieUtility.getCookie(request, "role") != null
                ? CookieUtility.getCookie(request, "role").getValue()
                : null;
       if(isLoggedIn && role == null) {
    	   SessionUtil.invalidateSession(request);
    	   response.sendRedirect(contextPath + "/login");
           return;
       }
    
        // Redirect logged-in users away from login/register
        if (isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER))) {
            response.sendRedirect(contextPath + "/home");
            return;
        }
        
        for (String page : PUBLIC_PAGES) {
            if (uri.endsWith(page)) {
                chain.doFilter(request, res);
                return;
            }
        }
        
        // Allow user-specific pages only if logged in and role is "user"
        for (String page : USER_PAGES) {
            if (uri.endsWith(page)) {
            	System.out.println("user logged in");
                if (isLoggedIn && "user".equals(role)) {
                	System.out.println("user logged in");
                    chain.doFilter(request, res);
                    return;
          
                } else {
                    response.sendRedirect(contextPath + LOGIN);
                }
                return;
            }
        }
        
        if (isLoggedIn) {
            chain.doFilter(request, res);
            return;
        } else {
            response.sendRedirect(contextPath + LOGIN);
        }

        // Allow all public pages
       
        // Default: allow access if logged in
      
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
