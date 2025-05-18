<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Manage Customers</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 10px;
	border: 1px solid #ddd;
}

th {
	background-color: #f2f2f2;
}

.table-wrapper {
	overflow-x: auto;
	width: 100%;
}


.customer-table {
  min-width: 1000px; /* Force horizontal scroll on smaller screens */
  border-collapse: collapse;
}

.customer-table td, .customer-table th {
  padding: 10px;
  text-align: left;
}

</style>
</head>
<body>
	<div class="container">
		<h2>Customers</h2>

		<c:choose>
			<c:when test="${not empty customers}">
				<div class="table-wrapper">
					<table class="Customer-table">
						<tr>
							<th>#</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th>Email</th>
							<th>Phone</th>
							<th>Address</th>
							<th>Username</th>
							<th>DOB</th>
							<th>Gender</th>
							<th>Created At</th>
						</tr>
						<c:forEach var="user" items="${customers}" varStatus="loop">
							<tr>
								<td>${loop.index + 1}</td>
								<td>${user.firstName}</td>
								<td>${user.lastName}</td>
								<td>${user.email}</td>
								<td>${user.phone}</td>
								<td>${user.address}</td>
								<td>${user.username}</td>
								<td>${user.dob}</td>
								<td>${user.gender}</td>
								<td>${user.createdAt}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</c:when>
			<c:otherwise>
				<p>No customer records found.</p>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
