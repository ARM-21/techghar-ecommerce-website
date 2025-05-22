
<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Shopping Cart - TechGhar</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<!-- Google Fonts -->
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap"
	rel="stylesheet">
<!-- Custom CSS -->

<style>
/* Root styling */
:root {
	--primary-color: #4CAF50;
	--secondary-color: #f5f5f5;
	--font-color: #333;
	--box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
	--border-radius: 8px;
	--transition: all 0.3s ease;
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: var(--secondary-color);
	color: var(--font-color);
	margin: 0;
	padding: 0;
}

.cart-section {
	padding: 2rem;
	max-width: 1200px;
	margin: 0 auto;
}

.cart-section h2 {
	text-align: center;
	font-size: 2rem;
	font-weight: bold;
	margin-bottom: 2rem;
	border-bottom: 2px solid #ccc;
	padding-bottom: 0.5rem;
}

.cart-section form {
	display: flex;
	flex-direction: column;
}

.form-section {
	margin-bottom: 1.2rem;
}

.form-section label {
	display: block;
	margin-bottom: 0.5rem;
	font-weight: 600;
}

.form-section input[type="text"], .form-section input[type="tel"] {
	width: 100%;
	padding: 0.7rem;
	border: 1px solid #ccc;
	border-radius: var(--border-radius);
	transition: var(--transition);
	font-size: 1rem;
}

.form-section input[type="text"]:focus, .form-section input[type="tel"]:focus
	{
	border-color: var(--primary-color);
	outline: none;
	box-shadow: 0 0 0 2px rgba(76, 175, 80, 0.2);
}

.form-section input[type="checkbox"] {
	margin-right: 0.5rem;
}

.summary-table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 1.5rem;
	box-shadow: var(--box-shadow);
	background-color: #fff;
	border-radius: var(--border-radius);
	overflow: hidden;
}

.summary-table th, .summary-table td {
	padding: 1rem;
	text-align: left;
	border-bottom: 1px solid #e0e0e0;
}

.summary-table th {
	background-color: #f9f9f9;
	font-weight: bold;
}

.summary-table tfoot td {
	font-weight: bold;
	background-color: #f1f1f1;
}

.summary-table tr:last-child td {
	border-bottom: none;
}

.checkout-button {
	background-color: var(--primary-color);
	color: white;
	border: none;
	padding: 0.9rem 1.5rem;
	border-radius: var(--border-radius);
	cursor: pointer;
	width: 100%;
	font-size: 1rem;
	margin-top: 1.5rem;
	transition: var(--transition);
}

.checkout-button:hover {
	background-color: #43a047;
}

.continue-shopping {
	display: block;
	margin-top: 1rem;
	text-align: center;
	color: #666;
	text-decoration: none;
	font-size: 0.95rem;
}

.continue-shopping:hover {
	text-decoration: underline;
}

/* Responsive Design */
@media ( max-width : 768px) {
	.cart-section {
		padding: 1rem;
	}
	.cart-section>div {
		flex-direction: column;
	}
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/pages/component/header.jsp"%>

	<!-- Inside your body -->
	<div class="cart-section" style="padding: 2rem;">
		<h2
			style="text-align: center; font-size: 2rem; font-weight: bold; margin-bottom: 2rem; border-bottom: 2px solid #ccc;">
			Checkout</h2>

		<div
			style="display: flex; flex-wrap: wrap; gap: 2rem; justify-content: center;">
			<!-- Delivery Form Section -->
			<div
				style="flex: 1 1 500px; background-color: white; padding: 2rem; border-radius: 8px; box-shadow: var(--box-shadow);">
				<h3
					style="font-size: 1.4rem; font-weight: bold; margin-bottom: 1.5rem;">Delivery
					Address</h3>
				<form action="cart-check-out" method="post">
					<div class="form-section">
						<label for="full-name">Full Name:</label> <input type="text"
							id="full-name" name="fullName" placeholder="Hancy Para" required>
					</div>
					<div class="form-section">
						<label for="phone">Phone Number:</label> 
						<input type="tel" id="phone" name="phone" pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}" required>
					</div>
					<div class="form-section">
						<label for="street">Street Address:</label> <input type="text"
							id="street" name="street" placeholder="123 Main St" required>
					</div>
					<div class="form-section">
						<label for="city">City:</label> <input type="text" id="city"
							name="city" placeholder="rampur York" required>
					</div>
					<div class="form-section">
						<label for="zip">ZIP Code:</label> <input type="text" id="zip"
							name="zip" placeholder="33021" required>
					</div>
					<div class="form-section">
						<c:choose>
							<c:when test="${not empty cartProducts}">

								<input type="submit" value="Place Order" class="checkout-button" />
							</c:when>
							<c:otherwise>
								<p>
									<a href="home" class="continue-shopping">Shop now</a>
								</p>
							</c:otherwise>
						</c:choose>


					</div>
				</form>
			</div>

			<!-- Order Summary Section -->
			<div class="order-summary">
				<h3 class="summary-title">Order Summary</h3>

				<c:choose>
					<c:when test="${not empty cartProducts}">
						<table class="summary-table">
							<thead>
								<tr>
									<th>Product</th>
									<th>Price</th>
									<th>Quantity</th>
									<th>Subtotal</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${cartProducts}">
									<tr>
										<td>${item.product.name}</td>
										<td>$${item.product.price}</td>
										<td>${item.quantity}</td>
										<td>$${item.product.price * item.quantity}</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="3" align="right"><strong>Total:</strong></td>
									<td><strong>$${cartTotal}</strong></td>
								</tr>
							</tfoot>
						</table>


					</c:when>
					<c:otherwise>
						<p>Your cart is empty.</p>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</div>

	<%@ include file="/WEB-INF/pages/component/footer.jsp"%>


	<%
	String orderSuccess = (String) session.getAttribute("orderSuccess");
	if (orderSuccess != null) {
		session.removeAttribute("orderSuccess"); 
	%>
	<script>
            alert("<%=orderSuccess%>
		");
	</script>
	<%
	}
	%>
</body>


</html>