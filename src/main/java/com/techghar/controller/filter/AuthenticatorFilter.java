package com.techghar.controller.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;

import com.techghar.utility.SessionUtil;
import com.techghar.utility.CookieUtility;

/**
 * AuthenticatorFilter is a servlet filter that controls access to different parts
 * of the web application based on user login status and role.
 * It restricts access to pages depending on whether the user is logged in and their role
 * (admin, staff, user). It also allows access to public resources like CSS, JS, images.
 */
@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticatorFilter implements Filter {
    // URL endpoints for login and registration pages
    private static final String LOGIN = "/login";
    private static final String REGISTER = "/register";

    // File extensions for static resources that should always be allowed
    private static final String[] ALLOWED_EXTENSIONS = {
        ".css", ".js", ".png", ".jpg", ".jpeg", ".svg", ".ico"
    };

    // Publicly accessible pages, no login required
    private static final String[] PUBLIC_PAGES = {
        "/", "/home", "/about", "/contact", "/products", "/product-details", "search-catalog", LOGIN, REGISTER, "/uploadPhoto"
    };

    // Pages accessible to logged-in users with role "user"
    private static final String[] USER_PAGES = {
        "/user-profile", "/checkout", "/cart", "/orderlist", "/update-profile", "/update-profile-post",
        "/add-to-cart","/RemoveFromCart", "/Cartquantity","/cart-check-out","/orders","/about","/contact"
    };

    // Pages accessible to logged-in users with role "admin" (also staff related pages)
    private static final String[] ADMIN_PAGES = {
        "/dashboard", "/admin-orders", "/admin-customers", "/admin-products", "/admin-update", "/admin-update-save",
        "/delete-product", "/add-product", "/manage-orders", "/save-product", "/admin-profile", "/edit-product",
        "/update-admin-profile", "/update-ad-profile-post", "/view-stat", "/view-categories", "/product-delete",
        "/admin-reports", "/update-category", "/add-category", "/view-brands", "/update-brand", "/add-brand",
        "/admin-carousel","/delete-carousel-item","/add-carousel-item",
        // Staff-related endpoints
        "/admin-staff", "/add-new-staff", "/admin-staff-add","/admin-staff-save","/admin-staff-delete",
        "/update-staff-profile", "/update-staff-profile-post"
    };

    @Override
    public void init(FilterConfig filterConfig) {}

    /**
     * Core filtering logic for authentication and authorization.
     * It checks the request URI and user's login status and role to decide
     * whether to allow the request, redirect to login, or redirect to role-specific pages.
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        // Allow static resources like CSS, JS, images without any authentication
        for (String ext : ALLOWED_EXTENSIONS) {
            if (uri.endsWith(ext)) {
                chain.doFilter(req, res);
                return;
            }
        }

        // Check if user is logged in by checking session attribute "username"
        boolean isLoggedIn = SessionUtil.getAttribute(request, "username") != null;
        // Get user role from cookie if present
        String role = CookieUtility.getCookie(request, "role") != null
                ? CookieUtility.getCookie(request, "role").getValue()
                : null;

        // Allow logout request without restrictions
        if (uri.endsWith("/logout")) {
            chain.doFilter(request, response);
            return;
        }

        // If user is logged in but role cookie is missing, invalidate session and redirect to login
        if (isLoggedIn && role == null) {
            SessionUtil.invalidateSession(request);
            response.sendRedirect(contextPath + LOGIN);
            return;
        }

        // Prevent logged-in users from accessing login or register pages again
        if (isLoggedIn && (uri.endsWith(LOGIN) || uri.endsWith(REGISTER))) {
            if ("admin".equals(role) || "staff".equals(role)) {
                // Redirect admin and staff to dashboard
                response.sendRedirect(contextPath + "/dashboard");
            } else if ("user".equals(role)) {
                // Redirect normal users to home page
                response.sendRedirect(contextPath + "/home");
            } else {
                // If role is unknown, redirect to login
                response.sendRedirect(contextPath + LOGIN);
            }
            return;
        }

        // Allow access to public pages without login
        for (String page : PUBLIC_PAGES) {
            if (uri.endsWith(page)) {
                chain.doFilter(req, res);
                return;
            }
        }

        // Restrict access to user-only pages
        for (String page : USER_PAGES) {
            if (uri.endsWith(page)) {
                if (isLoggedIn && "user".equals(role)) {
                    chain.doFilter(req, res);
                } else {
                    // Redirect unauthorized users to login page
                    response.sendRedirect(contextPath + LOGIN);
                }
                return;
            }
        }

        // Restrict access to admin-only pages
        for (String page : ADMIN_PAGES) {
            if (uri.endsWith(page)) {
                if (isLoggedIn && "admin".equals(role)) {
                    chain.doFilter(req, res);
                } else {
                    // Redirect unauthorized users to login page
                    response.sendRedirect(contextPath + LOGIN);
                }
                return;
            }
        }

        // If none of the above conditions matched, redirect to login page by default
        response.sendRedirect(contextPath + LOGIN);
    }

    @Override
    public void destroy() {
        // No resource cleanup necessary
    }
}
