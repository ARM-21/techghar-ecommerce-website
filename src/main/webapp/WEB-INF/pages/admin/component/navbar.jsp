<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Navbar with Sidebar</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">

<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/all.min.css">

<!-- Custom CSS -->
<link rel="stylesheet" href="/css/admin/navbar.css">
</head>

<body>
	<!-- ========== HEADER SECTION ========== -->
	<header class="header">
    <!-- Logo -->
    <div class="logo">
        <c:choose>
            <c:when test="${sessionScope.role == 'admin'}">
                <h1>TechGhar Admin</h1>
            </c:when>
            <c:when test="${sessionScope.role == 'staff'}">
                <h1>TechGhar Staff</h1>
            </c:when>
            <c:otherwise>
                <h1>TechGhar</h1>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- User information section -->
    <div class="user-info">
        <!-- User icon -->
        <div class="user-image">
            <i class="fas fa-user"></i>
        </div>

        <!-- Display username from session or fallback to 'Admin' -->
        <c:choose>
            <c:when test="${not empty sessionScope.username}">
                <div class="user-name">${sessionScope.username}</div>
            </c:when>
            <c:otherwise>
                <div class="user-name">Admin</div>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Mobile toggle button for sidebar -->
    <div class="mobile-toggle" id="sidebarToggle">
        <i class="fas fa-bars"></i>
    </div>
</header>


	<!-- ========== SIDEBAR MENU ========== -->
	<aside class="sidebar">
	<ul class="menu">
		<!-- Profile link (shared) -->
		<li><a href="/admin-profile?id=${sessionScope.id}"
			class="${activePage == 'admin-profile' ? 'active' : ''}"> <i
				class="fas fa-user-cog"></i> <span>Profile</span>
		</a></li>

		<!-- Statistics/Dashboard link (shared) -->
		<li><a href="/view-stat"
			class="${activePage == 'view-stat' || activePage == 'dashboard' ? 'active' : ''}">
				<i class="fas fa-tachometer-alt"></i> <span>Statistics</span>
		</a></li>

		<!-- Products (admin only) -->
		<c:if test="${sessionScope.role == 'admin'}">
			<li><a href="/admin-products"
				class="${activePage == 'admin-products' ? 'active' : ''}"> <i
					class="fas fa-mobile-alt"></i> <span>Products</span>
			</a></li>
		</c:if>

		<!-- Categories (admin only) -->
		<c:if test="${sessionScope.role == 'admin'}">
			<li><a href="/view-categories"
				class="${activePage == 'view-categories' ? 'active' : ''}"> <i
					class="fas fa-tags"></i> <span>Categories</span>
			</a></li>
		</c:if>

		<!-- Brands (admin only) -->
		<c:if test="${sessionScope.role == 'admin'}">
			<li><a href="/view-brands"
				class="${activePage == 'view-brands' ? 'active' : ''}"> <i
					class="fas fa-tags"></i> <span>Brands</span>
			</a></li>
		</c:if>

		<!-- Orders (shared) -->
		<li><a href="/admin-orders"
			class="${activePage == 'admin-orders' ? 'active' : ''}"> <i
				class="fas fa-shopping-cart"></i> <span>Orders</span>
		</a></li>

		<!-- Customers (admin only) -->
		<c:if test="${sessionScope.role == 'admin'}">
			<li><a href="/admin-customers"
				class="${activePage == 'admin-customers' ? 'active' : ''}"> <i
					class="fas fa-users"></i> <span>Customers</span>
			</a></li>
		</c:if>

		<!-- Staff (admin only) -->
		<c:if test="${sessionScope.role == 'admin'}">
			<li><a href="/admin-staff"
				class="${activePage == 'admin-staff' ? 'active' : ''}"> <i
					class="fas fa-user-tie"></i> <span>Staff</span>
			</a></li>
		</c:if>

		<!-- Logout -->
		<li>
			<form method="post" action="/logout">
				<i class="fas fa-sign-out-alt"></i>
				<button type="submit">Logout</button>
			</form>
		</li>
	</ul>
	</aside>

	<!-- ========== JAVASCRIPT ========== -->
	<script>
                // Toggle sidebar visibility on mobile
                const sidebarToggle = document.getElementById('sidebarToggle');
                const sidebar = document.querySelector('.sidebar');

                sidebarToggle.addEventListener('click', () => {
                    sidebar.classList.toggle('show');
                });

                // Highlight active menu item when clicked
                const links = document.querySelectorAll('.menu li a');

                links.forEach(link => {
                    link.addEventListener('click', () => {
                        // Remove active class from all links
                        links.forEach(l => l.classList.remove('active'));
                        // Add active class to clicked link
                        link.classList.add('active');
                    });
                });
            </script>
</body>

</html>