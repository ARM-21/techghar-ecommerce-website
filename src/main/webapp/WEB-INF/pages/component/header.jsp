<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TechGhar - Navigation</title>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/css/header.css" />
<style>
:root {
    --primary-color: #4361ee;
    --secondary-color: #3f37c9;
    --accent-color: #4895ef;
    --text-color: #2b2d42;
    --light-gray: #f8f9fa;
    --white: #ffffff;
    --shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    --transition: all 0.3s ease;
}

/* Navigation Bar */
.navbar {
    background-color: var(--white);
    box-shadow: var(--shadow);
    position: sticky;
    top: 0;
    z-index: 1000;
    padding: 0.8rem 0;
}

.navbar-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 1.5rem;
}

.navbar-brand img {
    height: 40px;
    transition: var(--transition);
}

.navbar-brand:hover img {
    transform: scale(1.05);
}

.navbar-toggler {
    display: none;
    background: none;
    border: none;
    font-size: 1.5rem;
    color: var(--text-color);
    cursor: pointer;
}

.navbar-collapse {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
}

.navbar-nav {
    display: flex;
    list-style: none;
    margin: 0;
    padding: 0;
    gap: 1.5rem;
}

.nav-item {
    position: relative;
}

.nav-link {
    color: var(--text-color);
    text-decoration: none;
    font-weight: 500;
    padding: 0.5rem 0;
    transition: var(--transition);
    display: flex;
    align-items: center;
    gap: 0.3rem;
}

.nav-link:hover {
    color: var(--primary-color);
}

.nav-item.active .nav-link {
    color: var(--primary-color);
    font-weight: 600;
}

.nav-item.active::after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 3px;
    background-color: var(--primary-color);
    border-radius: 3px;
}

.nav-icons {
    display: flex;
    align-items: center;
    gap: 1.5rem;
}

.nav-icons a {
    color: var(--text-color);
    text-decoration: none;
    font-size: 1.2rem;
    transition: var(--transition);
    display: flex;
    align-items: center;
}

.nav-icons a:hover {
    color: var(--primary-color);
    transform: translateY(-2px);
}

/* Cart Icon */
.cart-icon-wrapper {
    position: relative;
    display: inline-block;
}

.cart-count {
    position: absolute;
    top: -8px;
    right: -12px;
    background-color: #ef233c;
    color: var(--white);
    font-size: 0.7rem;
    min-width: 18px;
    height: 18px;
    border-radius: 50%;
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: bold;
    line-height: 1;
}

/* User Dropdown */
.user-dropdown {
    position: relative;
}

.user-greeting {
    display: flex;
    align-items: center;
    gap: 0.5rem;
    cursor: pointer;
    padding: 0.5rem 0.8rem;
    border-radius: 4px;
    transition: var(--transition);
    color: var(--text-color);
}

.user-greeting:hover {
    background-color: var(--light-gray);
    color: var(--primary-color);
}

.user-dropdown-content {
    display: none;
    position: absolute;
    right: 0;
    top: 40%;
    background-color: var(--white);
    min-width: 180px;
    box-shadow: var(--shadow);
    border-radius: 8px;
    overflow: hidden;
    z-index: 100;
    margin-top: 0.5rem;
}

.user-dropdown:hover .user-dropdown-content {
    display: block;
}

.user-dropdown-item {
    padding: 0.8rem 1rem;
    display: block;
    color: var(--text-color);
    text-decoration: none;
    transition: var(--transition);
    font-size: 0.9rem;
    border-left: 3px solid transparent;
}

.user-dropdown-item:hover {
    background-color: var(--light-gray);
    border-left-color: var(--primary-color);
    padding-left: 1.2rem;
}

.user-dropdown-item button {
    background: none;
    border: none;
    color: var(--text-color);
    cursor: pointer;
    font-size: 0.9rem;
    width: 100%;
    text-align: left;
    padding: 0;
}

/* Auth Buttons */
.auth-buttons {
    display: flex;
    gap: 1rem;
}

.login-btn {
    padding: 0.5rem 1rem;
    border-radius: 4px;
    text-decoration: none;
    font-weight: 500;
    transition: var(--transition);
}

.login-btn:first-child {
    color: var(--text-color);
    border: 1px solid var(--text-color);
}

.login-btn:first-child:hover {
    background-color: var(--light-gray);
}

.login-btn:last-child {
    background-color: var(--primary-color);
    color: var(--white);
}

.login-btn:last-child:hover {
    background-color: var(--secondary-color);
}

/* Mobile Styles */
@media (max-width: 768px) {
    .navbar-container {
        padding: 0 1rem;
    }
    
    .navbar-toggler {
        display: block;
    }
    
    .navbar-collapse {
        position: fixed;
        top: 68px;
        left: 0;
        width: 100%;
        background-color: var(--white);
        flex-direction: column;
        align-items: flex-start;
        padding: 1rem;
        box-shadow: var(--shadow);
        max-height: 0;
        overflow: hidden;
        transition: max-height 0.3s ease;
    }
    
    .navbar-collapse.show {
        max-height: 500px;
    }
    
    .navbar-nav {
        flex-direction: column;
        width: 100%;
        gap: 0;
    }
    
    .nav-item {
        width: 100%;
        padding: 0.8rem 0;
    }
    
    .nav-item.active::after {
        display: none;
    }
    
    .nav-icons {
        width: 100%;
        flex-direction: column;
        align-items: flex-start;
        gap: 1rem;
        margin-top: 1rem;
        padding-top: 1rem;
        border-top: 1px solid var(--light-gray);
    }
    
    .user-dropdown-content {
        position: static;
        box-shadow: none;
        background-color: transparent;
        display: none;
        width: 100%;
    }
    
    .user-dropdown-item {
        padding: 0.8rem 0;
    }
    
    .auth-buttons {
        width: 100%;
        flex-direction: column;
        gap: 0.8rem;
    }
    
    .login-btn {
        width: 100%;
        text-align: center;
    }
}
</style>
</head>

<body>
    <!-- Navigation Bar -->
    <nav class="navbar">
        <div class="container navbar-container">
            <a class="navbar-brand" href="index.html">
                <img src="${pageContext.request.contextPath}/assets/images/logo.jpg" alt="TechGhar Logo">
            </a>
            
            <button class="navbar-toggler" id="navbarToggler">
                <span class="icon icon-bars"></span>
            </button>
            <div class="navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav">
                    <li class="nav-item ${activePage == 'home' ? 'active' : ''}">
                        <a class="nav-link" href="home">Home</a>
                    </li>
                    <li class="nav-item ${activePage == 'products' ? 'active' : ''}">
                        <a class="nav-link" href="products">Products</a>
                    </li>
                    <li class="nav-item ${activePage == 'about' ? 'active' : ''}">
                        <a class="nav-link" href="about">About</a>
                    </li>
                    <li class="nav-item ${activePage == 'contact' ? 'active' : ''}">
                        <a class="nav-link" href="contact">Contact</a>
                    </li>
                </ul>

                <div class="nav-icons">
                    <a href="search-catalog" title="Search">
                        <span class="icon icon-search"></span>
                    </a>
                    <c:if test="${not empty sessionScope.username }">
                        <a href="cart" title="Cart" class="cart-icon-wrapper">
                            <span class="icon icon-cart"></span>
                            <span class="cart-count">${cartCount}</span>
                        </a>
                    </c:if>

                    <c:choose>
                        <c:when test="${not empty sessionScope.username}">
                            <!-- Logged-in User Menu -->
                            <div class="user-dropdown">
                                <div class="user-greeting">
                                    <span class="icon icon-user"></span>
                                    <span>${username}</span>
                                </div>
                                <div class="user-dropdown-content">
                                    <a href="user-profile?id=${id}" class="user-dropdown-item">Profile</a>
                                    <a href="orders" class="user-dropdown-item">Orders</a>
                                    <form action="logout" method="POST" class="user-dropdown-item">
                                        <button type="submit">Logout</button>
                                    </form>
                                </div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <!-- Guest Menu -->
                            <div class="auth-buttons">
                                <a href="login" class="login-btn">Login</a>
                                <a href="register" class="login-btn">Sign Up</a>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>
    </nav>

    <script>
        // Mobile menu toggle
        document.getElementById('navbarToggler').addEventListener('click', function() {
            document.getElementById('navbarCollapse').classList.toggle('show');
        });

        // Close menu when clicking outside
        document.addEventListener('click', function(event) {
            const navbarCollapse = document.getElementById('navbarCollapse');
            const navbarToggler = document.getElementById('navbarToggler');
            
            if (window.innerWidth <= 768 && navbarCollapse.classList.contains('show') && 
                !navbarCollapse.contains(event.target) && 
                !navbarToggler.contains(event.target)) {
                navbarCollapse.classList.remove('show');
            }
        });

        // Mobile dropdown toggle
        document.querySelectorAll('.user-greeting').forEach(item => {
            item.addEventListener('click', function(event) {
                if (window.innerWidth <= 768) {
                    event.stopPropagation();
                    const dropdown = this.parentNode.querySelector('.user-dropdown-content');
                    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
                }
            });
        });
    </script>
</body>
</html>