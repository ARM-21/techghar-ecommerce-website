<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Staff</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #eef2f5;
	margin: 0;
	padding: 0;
}

.container {
	max-width: 900px;
	margin: 50px auto;
	padding: 20px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #333;
	margin-bottom: 20px;
}

.staff-actions {
	display: flex;
	justify-content: flex-end;
	margin-bottom: 15px;
}

.add-btn {
	background-color: #28a745;
	color: white;
	border: none;
	padding: 8px 16px;
	border-radius: 4px;
	text-decoration: none;
	font-size: 14px;
}

.alert {
	padding: 10px 15px;
	border-radius: 4px;
	margin-bottom: 20px;
	text-align: center;
	font-size: 14px;
	font-weight: bold;
}

.alert-success {
	background-color: #d4edda;
	color: #155724;
	border: 1px solid #c3e6cb;
}

.alert-error {
	background-color: #f8d7da;
	color: #721c24;
	border: 1px solid #f5c6cb;
}

.staff-list {
	display: flex;
	flex-direction: column;
	gap: 15px;
}

.staff-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 12px 16px;
	background-color: #f8f9fa;
	border-left: 5px solid #007bff;
	border-radius: 6px;
}

.staff-info {
	display: flex;
	flex-direction: column;
}

.staff-info span {
	font-size: 15px;
	color: #333;
}

.staff-buttons {
	display: flex;
	gap: 10px;
}

.staff-buttons a {
	text-decoration: none;
	padding: 6px 10px;
	border-radius: 4px;
	font-size: 13px;
	color: white;
}

.edit-btn {
	background-color: #007bff;
}

.delete-btn {
	background-color: #dc3545;
}

.no-staff {
	text-align: center;
	font-style: italic;
	color: #666;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Manage Staff</h2>

		<!-- Display success or error message -->
		<c:if test="${not empty message}">
			<div class="alert alert-success" id="alertBox">${message}</div>
		</c:if>
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-error" id="alertBox">${errorMessage}</div>
		</c:if>

		<!-- Button to add new staff -->
		<div class="staff-actions">
			<a href="admin-staff-add" class="add-btn">Add New Staff</a>
		</div>

		<!-- Staff list or fallback message -->
		<c:choose>
			<c:when test="${not empty staffList}">
				<div class="staff-list">
					<c:forEach var="staff" items="${staffList}">
						<div class="staff-item">
							<div class="staff-info">
								<span><strong>Name:</strong> ${staff.firstName}
									${staff.lastName}</span> <span><strong>Email:</strong>
									${staff.email}</span> <span><strong>Phone:</strong>
									${staff.phone}</span>
							</div>
							<div class="staff-buttons">
								<a href="update-staff-profile?id=${staff.id}" class="edit-btn">Edit</a>

								<form action="admin-staff-delete" method="post"
									style="display: inline;">
									<input type="hidden" name="id" value="${staff.id}" />
									<button type="submit" class="delete-btn"
										onclick="return confirm('Are you sure you want to delete this staff?');">Delete</button>
								</form>	
							</div>

						</div>
					</c:forEach>
				</div>
			</c:when>
			<c:otherwise>
				<p class="no-staff">No staff members found.</p>
			</c:otherwise>
		</c:choose>
	</div>

	<c:if test="${not empty js}">
		<script>
			${js}
		</script>
	</c:if>

	<!-- JavaScript to auto-dismiss the alert after 2 seconds -->

	<script>
		setTimeout(function() {
			const alertBox = document.getElementById("alertBox");
			if (alertBox) {
				alertBox.style.display = "none";
				$
				{
					js
				}
			}
		}, 2000);
	</script>
</body>
</html>
