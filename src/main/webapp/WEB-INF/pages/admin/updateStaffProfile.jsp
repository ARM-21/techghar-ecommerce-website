<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<title>Edit Profile</title>
<style>
/* Add message styles */
.alert {
	padding: 15px;
	margin-bottom: 20px;
	border-radius: 6px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

.alert-error {
	background-color: #fee2e2;
	color: #dc2626;
	border: 1px solid #fca5a5;
}

.alert-success {
	background-color: #dcfce7;
	color: #16a34a;
	border: 1px solid #86efac;
}

.alert-close {
	cursor: pointer;
	font-weight: bold;
	margin-left: 15px;
}

.edit-container {
	max-width: 700px;
	margin: auto;
	background-color: #ffffff;
	padding: 30px;
	border-radius: 12px;
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

h2 {
	text-align: center;
	color: #2c3e50;
	margin-bottom: 25px;
}

.form-group {
	margin-bottom: 18px;
}

label {
	display: block;
	margin-bottom: 6px;
	font-weight: bold;
	color: #34495e;
}

input[type="text"], input[type="email"], input[type="password"], input[type="date"],
	select, textarea {
	width: 100%;
	padding: 10px;
	border-radius: 6px;
	border: 1px solid #ccc;
	font-size: 15px;
}

.edit-container button {
	width: 100%;
	padding: 12px;
	background-color: #2980b9;
	color: white;
	font-size: 16px;
	border: none;
	border-radius: 6px;
	cursor: pointer;
}

.edit-container button:hover {
	background-color: #1f5f8b;
}
</style>
</head>
<body>

	<div class="edit-container">
		<h2>Edit Your Profile</h2>

		<!-- Error Message -->
		<c:if test="${not empty error}">
			<div class="alert alert-error">
				<span>${error}</span> <span class="alert-close"
					onclick="this.parentElement.style.display='none'">×</span>
			</div>
		</c:if>

		<!-- Success Message -->
		<c:if test="${not empty success}">
			<div class="alert alert-success">
				<span>${success}</span> <a
					href="/admin-staff">
					click here </a> <span class="alert-close"
					onclick="this.parentElement.style.display='none'">×</span>
			</div>
		</c:if>
		<form
			action="update-staff-profile-post"
			method="post">
			<input type="hidden" name="id" value="${userDetails.id}">

			<div class="form-group">
				<label for="firstName">First Name</label> <input type="text"
					name="firstName" id="firstName" value="${userDetails.firstName}">
			</div>

			<div class="form-group">
				<label for="lastName">Last Name</label> <input type="text"
					name="lastName" id="lastName" value="${userDetails.lastName}">
			</div>

			<div class="form-group">
				<label for="username">Username</label> <input type="text"
					name="username" id="username" value="${userDetails.username}">
			</div>

			<div class="form-group">
				<label for="email">Email</label> <input type="email" name="email"
					id="email" value="${userDetails.email}">
			</div>

			<div class="form-group">
				<label for="phone">Phone</label> <input type="number" name="phone"
					id="phone" value="${userDetails.phone}">
			</div>

			<div class="form-group">
				<label for="dob">Date of Birth</label> <input type="date" name="dob"
					id="dob" required value="${userDetails.dob}">
			</div>

			<div class="form-group">
				<label for="gender">Gender</label> <select name="gender" id="gender">
					<option value="Male"
						${userDetails.gender == 'Male' ? 'selected' : ''}>Male</option>
					<option value="Female"
						${userDetails.gender == 'Female' ? 'selected' : ''}>Female</option>
				</select>
			</div>

			<div class="form-group">
				<label for="address">Address</label>
				<textarea name="address" id="address" draggable="false" rows="3">${userDetails.address} </textarea>
			</div>

			<div class="form-group">
				<label for="password">Password (Leave blank to keep current)</label>
				<input type="password" name="password" id="password">
			</div>

			<button type="submit">Update Profile</button>
		</form>
	</div>
	<script>
		function navigateToProfile() {
			let sucess = document.querySelector(".alert-success a");
			console.log(sucess)
			if (sucess) {
				setTimeout(()=>{
					window.location.href = '/admin-staff'
					console.log("navigation success")
				},1000)
				
			}
		}
		navigateToProfile()
	</script>
</body>
</html>
