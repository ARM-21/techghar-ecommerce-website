<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add New Staff</title>
<style>
.container {
	max-width: 700px;
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

form {
	display: flex;
	flex-direction: column;
	gap: 12px;
}

label {
	font-weight: bold;
	color: #555;
}

input, select {
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	font-size: 14px;
}

.submit-btn {
	background-color: #28a745;
	color: white;
	border: none;
	padding: 10px;
	font-size: 16px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.submit-btn:hover {
	background-color: #218838;
}
</style>
</head>
<body>
	<div class="container">
		<h2>Add New Staff</h2>
		<form action="admin-staff-save" method="post">
			<label for="firstName">First Name</label> <input type="text"
				id="firstName" name="firstName" required> <label
				for="lastName">Last Name</label> <input type="text" id="lastName"
				name="lastName" required> <label for="email">Email</label> <input
				type="email" id="email" name="email" required> <label
				for="password">Password</label> <input type="password" id="password"
				name="password" required> <label for="phone">Phone</label> <input
				type="number" id="phone" name="phone" required> <label
				for="address">Address</label> <input type="text" id="address"
				name="address" required> <label for="username">Username</label>
			<input type="text" id="username" name="username" required> <label
				for="dob">Date of Birth</label> <input type="date" id="dob"
				name="dob" required> <label for="gender">Gender</label> <select
				id="gender" name="gender" required>
				<option value="">Select Gender</option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>

			</select> <input type="hidden" name="role" value="STAFF" />

			<button type="submit" class="submit-btn">Add Staff</button>
		</form>
	</div>
	

</body>
</html>
