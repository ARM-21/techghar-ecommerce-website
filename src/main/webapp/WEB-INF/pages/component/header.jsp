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
/* Add these styles to your header.css */
.user-dropdown {
	position: relative;
	display: inline-block;
}

.user-dropdown-content {
	display: none;
	position: absolute;
	right: 0;
	top: 100%;
	background-color: white;
	min-width: 160px;
	box-shadow: 0 8px 16px rgba(0, 0, 0, 0.1);
	z-index: 1;
	border-radius: 4px;
}

.user-dropdown:hover .user-dropdown-content {
	display: block;
}

.user-dropdown-item {
	padding: 12px 16px;
	display: block;
	color: var(--text-color);
	text-decoration: none;
	transition: background-color 0.2s;
}

.user-dropdown-item:hover {
	background-color: var(--light-gray);
}

.user-greeting {
	display: flex;
	align-items: center;
	gap: 8px;
	cursor: pointer;
	padding: 8px 12px;
	border-radius: 4px;
}

.user-greeting:hover {
	background-color: var(--light-gray);
}

.auth-buttons {
	display: flex;
	gap: 10px;
}

@media ( max-width : 768px) {
	.user-dropdown-content {
		position: static;
		box-shadow: none;
		background-color: transparent;
	}
	.user-dropdown-item {
		padding: 8px 0;
	}
	.user-greeting {
		padding-left: 0;
	}
	.auth-buttons {
		flex-direction: column;
		align-items: flex-start;
		gap: 10px;
		margin-top: 15px;
	}
}
</style>
</head>

<body>

	<!-- Navigation Bar -->
	<nav class="navbar">
		<div class="container navbar-container">
			<a class="navbar-brand" href="index.html"> <img
				src="${pageContext.request.contextPath}/images/logo.svg"
				alt="TechGhar Logo">
			</a>
			<button class="navbar-toggler" id="navbarToggler">
				<span class="icon icon-bars"></span>
			</button>
			<div class="navbar-collapse" id="navbarCollapse">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="home">Home</a></li>
					<li class="nav-item"><a class="nav-link" href="products">Products</a></li>
					<li class="nav-item"><a class="nav-link" href="about">About</a></li>
					<li class="nav-item"><a class="nav-link" href="contact">Contact</a></li>
				</ul>
				<div class="nav-icons">
					<a href="search-catalog" title="Search"> <span
						class="icon icon-search"></span></a>
					<c:if test="${not empty sessionScope.username }">
						<a href="view-cart" title="Cart"><span class="icon icon-cart"></span></a>
					</c:if>

					<c:choose>
						<c:when test="${not empty sessionScope.username}">
							<!-- Logged-in User Menu -->
							<div class="user-dropdown">
								<div class="user-greeting">
									<span class="icon icon-user"></span> <span>${username}</span>
								</div>
								<div class="user-dropdown-content">
									<a href="/user-profile?id=${id}" class="user-dropdown-item">Profile</a>
									<a href="orders" class="user-dropdown-item">Orders</a>
									<form action="/logout" method="POST" class="user-dropdown-item">
										<button type="submit">Logout</button>
									</form>
								</div>
							</div>
						</c:when>
						<c:otherwise>
							<!-- Guest Menu -->
							<div class="auth-buttons">
								<a href="/login" class="login-btn">Login</a> <a href="register"
									class="login-btn">Sign Up</a>
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



        // Mobile dropdown toggle
        document.querySelectorAll('.user-greeting').forEach(item => {
            item.addEventListener('click', function() {
                if (window.innerWidth <= 768) {
                    const dropdown = this.parentNode.querySelector('.user-dropdown-content');
                    dropdown.style.display = dropdown.style.display === 'block' ? 'none' : 'block';
                }
            });
        });
    </script>
</body>
</html>