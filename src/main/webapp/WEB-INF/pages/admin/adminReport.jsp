<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Report Dashboard</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 40px;
}

h2 {
	margin-top: 40px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

th, td {
	padding: 8px 12px;
	border: 1px solid #ccc;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}
</style>
</head>
<body>

	<h1>Admin Report Dashboard</h1>
	<p>
		Reporting Period:
		<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd" />
		to
		<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd" />
	</p>

	<!-- Category Sales Report -->
	<h2>Category Sales Report</h2>
	<c:choose>
		<c:when test="${not empty categorySalesReport}">
			<table>
				<thead>
					<tr>
						<th>Category</th>
						<th>Product Sold</th>
						<th>Total Revenue</th>
							<th>Percentage of Sales</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${categorySalesReport}">
						<tr>
							<td>${report.categoryName}</td>
							<td> ${report.productsSold}</td>
							<td>$${report.totalRevenue}</td>
							<td>${report.percentageOfSales}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>No category sales data available.</p>
		</c:otherwise>
	</c:choose>

	<!-- Brand Sales Report -->
	<h2>Brand Sales Report</h2>
	<c:choose>
		<c:when test="${not empty brandSalesReport}">
			<table>
				<thead>
					<tr>
						<th>BrandName</th>
						<th>Products Sold</th>
						<th>Total Percentage Sales</th>
						<th>Total Revenue</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${brandSalesReport}">
						<tr>
							<td>${report.brandName}</td>
							<td> ${report.productsSold}</td>
							<td>${report.percentageOfSales}</td>
							<td>$${report.totalRevenue}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>No brand sales data available.</p>
		</c:otherwise>
	</c:choose>

	<!-- Weekly Customer Purchase Report -->
	<h2>Weekly Customer Purchases</h2>
	<c:choose>
		<c:when test="${not empty customerPurchaseReport}">
			<table>
				<thead>
					<tr>
						<th>NUmber of Customer </th>
						<th>Orders</th>
						<th>Total Revenue</th>
						<th>Total Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="report" items="${customerPurchaseReport}">
						<tr>
							<td>${report.numberOfCustomers}</td>
							<td>${report.numberOfOrders}</td>
							<td>${report.totalRevenue}</td>
							<td>Rs. ${report.date}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:when>
		<c:otherwise>
			<p>No customer purchases this week.</p>
		</c:otherwise>
	</c:choose>

</body>
</html>
