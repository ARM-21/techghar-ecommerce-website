<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Navbar with Sidebar</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/all.min.css"> 
    <link rel="stylesheet" href="/css/admin/navbar.css" >
</head>
<body>
  <!-- Header -->
  <header class="header">
    <div class="logo">
        
        <h1>TechGhar Admin</h1>
    </div>
    <div class="user-info">
        <div class="user-image">
            <i class="fas fa-user"></i>
        </div>
		<c:choose>
		<c:when test="${not empty sessionScope}">
		 <div class="user-name">${sessionScope.username}</div>
		</c:when>
		<c:otherwise>
		 <div class="user-name">Admin</div>
		</c:otherwise>
		</c:choose>
		
       
        
    </div>
    <div class="mobile-toggle" id="sidebarToggle">
        <i class="fas fa-bars"></i>
    </div>
</header>

<!-- Sidebar -->
<aside class="sidebar">
    <ul class="menu">
        <li>
            <a href="#" class="active" data-tab="dashboard">
                <i class="fas fa-tachometer-alt"></i>
                <span>Dashboard</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="products">
                <i class="fas fa-mobile-alt"></i>
                <span>Products</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="categories">
                <i class="fas fa-tags"></i>
                <span>Categories</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="orders">
                <i class="fas fa-shopping-cart"></i>
                <span>Orders</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="users">
                <i class="fas fa-users"></i>
                <span>Users</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="staff">
                <i class="fas fa-user-tie"></i>
                <span>Staff</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="reports">
                <i class="fas fa-chart-bar"></i>
                <span>Reports</span>
            </a>
        </li>
        <li>
            <a href="#" data-tab="settings">
                <i class="fas fa-cog"></i>
                <span>Settings</span>
            </a>
        </li>
        <li>
         
      		<form method="post"  action="/logout">
                <i class="fas fa-sign-out-alt"></i>
                <button type="submit">Logout</button>
                </form>
        
        </li>
    </ul>
</aside>
<script>
    // Sidebar toggle functionality
    const sidebarToggle = document.getElementById('sidebarToggle');
    const sidebar = document.querySelector('.sidebar');

    sidebarToggle.addEventListener('click', () => {
        sidebar.classList.toggle('show');
    });

    
</script>
</body>
</html>