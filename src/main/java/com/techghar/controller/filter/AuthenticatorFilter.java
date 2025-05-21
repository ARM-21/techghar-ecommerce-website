package com.techghar.controller.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.techghar.utility.SessionUtil;
import com.techghar.utility.CookieUtility;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticatorFilter implements Filter {
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";

    private static final String[] ALLOWED_EXTENSIONS = {
        ".css", ".js", ".png", ".jpg", ".jpeg", ".svg", ".ico"
    };

    private static final String[] PUBLIC_PAGES = {
        "/", "/home", "/about", "/contact", "/products", "/product-details", "search-catalog", LOGIN, REGISTER, "/uploadPhoto"
    };

    private static final String[] USER_PAGES = {
    		 "/user-profile", "/checkout", "/cart", "/orderlist", "/update-profile", "/update-profile-post","/add-to-cart","/RemoveFromCart", "/Cartquantity","/cart-check-out","/orders","/about","/contact"
    };

    private static final String[] ADMIN_PAGES = {
        "/dashboard", "/admin-orders", "/admin-customers", "/admin-products", "/admin-update", "/admin-update-save",
        "/delete-product", "/add-product", "/manage-orders", "/save-product", "/admin-profile", "/edit-product",
        "/update-admin-profile", "/update-ad-profile-post", "/view-stat", "/view-categories", "/product-delete",
        "/update-category", "/add-category", "/view-brands", "/update-brand", "/add-brand",
        // Staff-related endpoints
        "/admin-staff", "/add-new-staff", "/admin-staff-edit", "/admin-staff-delete", "/update-staff-profile-post"
    };

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        for (String ext : ALLOWED_EXTENSIONS) {
            if (uri.endsWith(ext)) {
                chain.doFilter(req, res);
                return;
            }
        }

        boolean isLoggedIn = SessionUtil.getAttribute(request, "username") != null;
        String role = CookieUtility.getCookie(request, "role") != null
                ? CookieUtility.getCookie(request, "role").getValue()
                : null;

        if (uri.endsWith("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        if (isLoggedIn && role == null) {
            SessionUtil.invalidateSession(request);
            response.sendRedirect(contextPath + LOGIN);
            return;
        }

        // Redirect away from login/register if already logged in
        if (isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER))) {
            if ("admin".equals(role) || "staff".equals(role)) {
                response.sendRedirect(contextPath + "/dashboard");
            } else if ("user".equals(role)) {
                response.sendRedirect(contextPath + "/home");
            } else {
                response.sendRedirect(contextPath + LOGIN);
            }
            return;
        }

        // Allow public pages
        for (String page : PUBLIC_PAGES) {
            if (uri.endsWith(page)) {
                chain.doFilter(req, res);
                return;
            }
        }

        // User pages
        for (String page : USER_PAGES) {
            if (uri.endsWith(page)) {
                if (isLoggedIn && "user".equals(role)) {
                    chain.doFilter(req, res);
                } else {
                    response.sendRedirect(contextPath + LOGIN);
                }
                return;
            }
        }

        // Admin pages
        for (String page : ADMIN_PAGES) {
            if (uri.endsWith(page)) {
                if (isLoggedIn && "admin".equals(role)) {
                    chain.doFilter(req, res);
                } else {
                    response.sendRedirect(contextPath + LOGIN);
                }
                return;
            }
        }

        // If nothing matched, redirect to login
        response.sendRedirect(contextPath + LOGIN);
    }

    @Override
    public void destroy() {

    }
}
