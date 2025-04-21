<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@6.4.2/css/all.min.css">
<style>
/* Add your CSS styles here */
.stats-container {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	justify-content: space-between;
	margin-bottom: 30px;
}

.stats-card {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	display: flex;
	align-items: center;
	flex: 1 1 250px;
	min-width: 250px;
	max-width: 100%;
}

.stats-icon {
	width: 60px;
	height: 60px;
	border-radius: 8px;
	background-color: #f0f0f5;
	display: flex;
	align-items: center;
	justify-content: center;
	margin-right: 15px;
}

.stats-icon i {
	font-size: 24px;
	color: #6c63ff;
}

.stats-info h3 {
	font-size: 24px;
	margin: 0 0 5px 0;
}

.stats-info p {
	margin: 0;
	color: #808080;
}

.table-container {
	background-color: white;
	border-radius: 8px;
	padding: 20px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
	margin-bottom: 30px;
}

.table-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 20px;
}

.table-title {
	font-size: 1.2rem;
	margin: 0;
}

.table-actions {
	display: flex;
	gap: 10px;
}

.data-table {
	width: 100%;
	border-collapse: collapse;
}

.data-table th, .data-table td {
	padding: 12px 15px;
	border-bottom: 1px solid #f0f0f0;
}

.data-table th {
	background-color: #f8f9fa;
	font-weight: 600;
	color: #808080;
}

.data-table tr:hover {
	background-color: #f8f9fa;
}

.status {
	padding: 5px 10px;
	border-radius: 20px;
	font-size: 0.85rem;
}

.status-active {
	background-color: #e6f7e6;
	color: #28a745;
}

.status-inactive {
	background-color: #fff5e6;
	color: #fd7e14;
}

.status-pending {
	background-color: #e6f2ff;
	color: #007bff;
}

.main-content {
	display: flex;
	width: 75%;
	flex-direction: column;
}
</style>
</head>
<body>
	<main class="main-content">
		<!-- Dashboard Stats -->
		<div class="stats-container">
			<div class="stats-card">
				<div class="stats-icon">
					<i class="fas fa-mobile-alt"></i>
				</div>
				<div class="stats-info">
					<h3>${totalProducts}</h3>
					<p>Total Products</p>
				</div>
			</div>
			<div class="stats-card">
				<div class="stats-icon">
					<i class="fas fa-users"></i>
				</div>
				<div class="stats-info">
					<h3>${totalCustomers}</h3>
					<p>Total Customers</p>
				</div>
			</div>
			<div class="stats-card">
				<div class="stats-icon">
					<i class="fas fa-shopping-cart"></i>
				</div>
				<div class="stats-info">
					<h3>${newOrders}</h3>
					<p>New Orders</p>
				</div>
			</div>
			<div class="stats-card">
				<div class="stats-icon">
					<i class="fas fa-dollar-sign"></i>
				</div>
				<div class="stats-info">
					<h3>${totalRevenue}</h3>
					<p>Total Revenue</p>
				</div>
			</div>
		</div>

		<!-- Recent Orders Table -->
		<div class="table-container">
			<div class="table-header">
				<h3 class="table-title">Recent Orders</h3>
				<div class="table-actions">
					<button class="btn btn-secondary">
						<i class="fas fa-filter"></i> Filter
					</button>
					<button class="btn btn-primary">
						<i class="fas fa-download"></i> Export
					</button>
				</div>
			</div>
			<div class="orders-container">
				<!-- 
    <c:forEach var="order" items="${recentOrders}">
   
				<div class="order-card">
					<div class="order-row">
						<strong>Order ID:</strong> ${order.id}
					</div>
					<div class="order-row">
						<strong>Customer:</strong> ${order.customerName}
					</div>
					<div class="order-row">
						<strong>Product:</strong> ${order.productName}
					</div>
					<div class="order-row">
						<strong>Date:</strong>
						<fmt:formatDate value="${order.date}" pattern="MMM d, yyyy" />
					</div>
					<div class="order-row">
						<strong>Amount:</strong> $${order.amount}
					</div>
					<div class="order-row">
						<strong>Status:</strong> <span
							class="status 
                <c:choose>
                    <c:when test="${order.status == 'Delivered'}">status-active</c:when>
                    <c:when test="${order.status == 'Cancelled'}">status-inactive</c:when>
                    <c:otherwise>status-pending</c:otherwise>
                </c:choose>
            ">${order.status}</span>
					</div>
					<div class="order-row">
						<a href="#" class="action-btn view-btn"><i class="fas fa-eye"></i>
							View</a>
					</div>
	
    </c:forEach>
 
     -->
			</div>
		</div>
	</main>
</body>
</html>