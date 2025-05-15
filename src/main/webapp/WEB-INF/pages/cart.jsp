
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
:root {
	--primary-color: #0a2540;
	--secondary-color: #00d4ff;
	--accent-color: #635bff;
	--light-color: #f7fafc;
	--dark-color: #1a202c;
	--text-color: #4a5568;
	--border-radius: 8px;
	--box-shadow: 0 10px 15px -3px rgba(0, 0, 0, 0.1), 0 4px 6px -2px
		rgba(0, 0, 0, 0.05);
	--success-color: #48bb78;
}

body {
	font-family: 'Poppins', sans-serif;
	color: var(--text-color);
	background-color: var(--light-color);
	overflow-x: hidden;
}

/* Cart Section */
.cart-section {
	padding: 60px 0;
}

.section-title {
	text-align: center;
	margin-bottom: 40px;
	position: relative;
}

.section-title h2 {
	font-weight: 700;
	font-size: 2.2rem;
	display: inline-block;
	position: relative;
	z-index: 1;
	margin-bottom: 15px;
	color: var(--dark-color);
}

.section-title h2::after {
	content: '';
	position: absolute;
	width: 60px;
	height: 4px;
	background-color: var(--accent-color);
	bottom: -10px;
	left: 50%;
	transform: translateX(-50%);
	border-radius: 2px;
}

.cart-container {
	background-color: white;
	border-radius: var(--border-radius);
	box-shadow: var(--box-shadow);
	padding: 30px;
	margin-bottom: 30px;
}

.cart-header {
	border-bottom: 1px solid #e2e8f0;
	padding-bottom: 15px;
	margin-bottom: 20px;
	font-weight: 600;
	color: var(--dark-color);
}

.cart-item {
	display: flex;
	align-items: center;
	padding: 20px 0;
	border-bottom: 1px solid #e2e8f0;
}

.cart-item:last-child {
	border-bottom: none;
}

.item-image {
	width: 100px;
	height: 100px;
	object-fit: contain;
	background-color: var(--light-color);
	border-radius: var(--border-radius);
	padding: 10px;
}

.item-details {
	flex: 1;
	padding: 0 20px;
}

.item-name {
	font-weight: 600;
	color: var(--dark-color);
	margin-bottom: 5px;
	font-size: 1.1rem;
}

.item-meta {
	color: #718096;
	font-size: 0.9rem;
	margin-bottom: 10px;
}

.item-price {
	font-weight: 600;
	color: var(--dark-color);
}

.item-quantity {
	display: flex;
	align-items: center;
	margin-right: 20px;
}

.quantity-btn {
	width: 30px;
	height: 30px;
	display: flex;
	align-items: center;
	justify-content: center;
	background-color: var(--light-color);
	border: 1px solid #e2e8f0;
	cursor: pointer;
	font-size: 1rem;
	transition: all 0.3s ease;
	border-radius: 4px;
}

.quantity-btn:hover {
	background-color: var(--accent-color);
	color: white;
}

.quantity-input {
	width: 50px;
	height: 30px;
	text-align: center;
	border: 1px solid #e2e8f0;
	margin: 0 5px;
	border-radius: 4px;
}

.item-total {
	font-weight: 700;
	color: var(--dark-color);
	font-size: 1.1rem;
	min-width: 100px;
	text-align: right;
}

.item-remove {
	margin-left: 20px;
	color: #e53e3e;
	cursor: pointer;
	transition: all 0.3s ease;
}

.item-remove:hover {
	color: #c53030;
}

.cart-summary {
	background-color: white;
	border-radius: var(--border-radius);
	box-shadow: var(--box-shadow);
	padding: 30px;
}

.summary-title {
	font-weight: 700;
	color: var(--dark-color);
	margin-bottom: 20px;
	font-size: 1.3rem;
}

.summary-item {
	display: flex;
	justify-content: space-between;
	margin-bottom: 15px;
}

.summary-label {
	color: var(--text-color);
}

.summary-value {
	font-weight: 600;
	color: var(--dark-color);
}

.summary-total {
	border-top: 1px solid #e2e8f0;
	padding-top: 15px;
	margin-top: 15px;
	font-size: 1.2rem;
}

.summary-total .summary-label {
	font-weight: 600;
	color: var(--dark-color);
}

.summary-total .summary-value {
	font-weight: 700;
	color: var(--accent-color);
}

.promo-code {
	margin-top: 20px;
	margin-bottom: 20px;
}

.promo-input {
	display: flex;
}

.promo-input input {
	flex: 1;
	height: 45px;
	border-radius: var(--border-radius) 0 0 var(--border-radius);
	border: 1px solid #e2e8f0;
	padding: 10px 15px;
	font-size: 1rem;
}

.promo-input button {
	height: 45px;
	background-color: var(--accent-color);
	color: white;
	border: none;
	border-radius: 0 var(--border-radius) var(--border-radius) 0;
	padding: 0 20px;
	font-weight: 600;
	cursor: pointer;
	transition: all 0.3s ease;
}

.promo-input button:hover {
	background-color: #5851e6;
}

.checkout-button {
	display: block;
	width: 100%;
	height: 50px;
	background-color: var(--accent-color);
	color: white;
	border: none;
	border-radius: var(--border-radius);
	font-weight: 600;
	font-size: 1rem;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: 0 4px 6px rgba(99, 91, 255, 0.2);
	margin-top: 20px;
}

.checkout-button:hover {
	background-color: #5851e6;
	transform: translateY(-2px);
	box-shadow: 0 6px 8px rgba(99, 91, 255, 0.3);
}

.buy-now-button {
	display: block;
	width: 100%;
	height: 50px;
	background-color: var(--success-color);
	color: white;
	border: none;
	border-radius: var(--border-radius);
	font-weight: 600;
	font-size: 1rem;
	cursor: pointer;
	transition: all 0.3s ease;
	box-shadow: 0 4px 6px rgba(72, 187, 120, 0.2);
	margin-top: 15px;
}

.buy-now-button:hover {
	background-color: #38a169;
	transform: translateY(-2px);
	box-shadow: 0 6px 8px rgba(72, 187, 120, 0.3);
}

.continue-shopping {
	display: block;
	text-align: center;
	margin-top: 15px;
	color: var(--accent-color);
	text-decoration: none;
	font-weight: 500;
	transition: color 0.3s ease;
}

.continue-shopping:hover {
	color: #5851e6;
	text-decoration: underline;
}

.empty-cart {
	text-align: center;
	padding: 50px 0;
}

.empty-cart-icon {
	font-size: 5rem;
	color: #cbd5e0;
	margin-bottom: 20px;
}

.empty-cart h3 {
	font-weight: 600;
	color: var(--dark-color);
	margin-bottom: 15px;
}

.empty-cart p {
	color: var(--text-color);
	margin-bottom: 30px;
}

.shop-now-btn {
	display: inline-block;
	padding: 12px 30px;
	background-color: var(--accent-color);
	color: white;
	border-radius: var(--border-radius);
	text-decoration: none;
	font-weight: 600;
	transition: all 0.3s ease;
	box-shadow: 0 4px 6px rgba(99, 91, 255, 0.2);
}

.shop-now-btn:hover {
	background-color: #5851e6;
	transform: translateY(-2px);
	box-shadow: 0 6px 8px rgba(99, 91, 255, 0.3);
	color: white;
}

/* Footer */
footer {
	background-color: var(--primary-color);
	color: white;
	padding: 20px 0;
	margin-top: 60px;
}

.copyright {
	text-align: center;
	color: rgba(255, 255, 255, 0.7);
}

/* Responsive Adjustments */
@media ( max-width : 992px) {
	.cart-item {
		flex-wrap: wrap;
	}
	.item-quantity {
		margin-top: 15px;
		margin-right: 0;
	}
	.item-total {
		margin-top: 15px;
		margin-left: auto;
	}
	.item-remove {
		margin-top: 15px;
	}
}

@media ( max-width : 768px) {
	.cart-header .d-none {
		display: none !important;
	}
	.item-image {
		width: 80px;
		height: 80px;
	}
	.item-name {
		font-size: 1rem;
	}
}

@media ( max-width : 576px) {
	.cart-container {
		padding: 20px 15px;
	}
	.cart-summary {
		padding: 20px 15px;
	}
	.item-image {
		width: 60px;
		height: 60px;
	}
	.item-details {
		padding: 0 10px;
	}
}

/* Cart count */
.cart-count {
	position: absolute;
	top: -5px;
	right: -5px;
	background-color: var(--accent-color);
	color: white;
	width: 20px;
	height: 20px;
	border-radius: 50%;
	display: flex;
	align-items: center;
	justify-content: center;
	font-size: 0.7rem;
	font-weight: bold;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/pages/component/header.jsp"%>

	<!-- Cart Section -->
	<div class="cart-section" style="padding: 2rem;">
		<h2
			style="text-align: center; font-size: 2rem; font-weight: bold; margin-bottom: 1rem; border-bottom: 2px solid #ccc; display: inline-block;">
			Your Shopping Cart</h2>

		<div class="cart-table" style="overflow-x: auto; margin-top: 2rem;">
			<table style="width: 100%; border-collapse: collapse;">
				<thead>
					<tr style="background-color: #f9f9f9;">
						<th style="padding: 1rem; text-align: left;">Product</th>
						<th style="padding: 1rem; text-align: left;">Price</th>
						<th style="padding: 1rem; text-align: left;">Quantity</th>
						<th style="padding: 1rem; text-align: left;">Total</th>
						<th style="padding: 1rem; text-align: left;">Cancel</th>
					</tr>
				</thead>

				<tbody>
					<c:choose>
						<c:when test="${not empty cartProducts}">
							<c:forEach items="${cartProducts}" var="cartItem">
								<tr>
									<td style="padding: 1rem;"><strong>${cartItem.product.name}</strong>
									</td>
									<td style="padding: 1rem;">$${cartItem.product.price}</td>
									<td style="padding: 1rem;">
										<div style="display: flex; align-items: center; gap: 0.5rem;">
											<a href="Cartquantity?action=dec&id=${cartItem.product.id}"
												class="btn btn-sm btn-decre"
												${cartItem.quantity <= 1 ? 'style="pointer-events: none; opacity: 0.5;"' : ''}>
												<i class="fas fa-minus-square"></i>
											</a> <input type="text" value="${cartItem.quantity}" readonly
												style="width: 40px; text-align: center;" min="1" max="${cartItem.product.stock}"/>	 <a
												href="Cartquantity?action=inc&id=${cartItem.product.id}"
												class="btn btn-sm btn-incre"
												${cartItem.quantity >= cartItem.product.stock ? 'style="pointer-events: none; opacity: 0.5;"' : ''}>
												<i class="fas fa-plus-square"></i>
											</a>
										</div>

									</td>
									<td style="padding: 1rem;"><fmt:formatNumber
											value="${cartItem.product.price * cartItem.quantity}"
											type="currency" /></td>
									<td style="padding: 1rem;"><a
										href="RemoveFromCart?id=${cartItem.product.id}"
										class="btn btn-sm btn-danger">Remove</a></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="5" style="text-align: center; padding: 2rem;">
									Your cart is empty. <a href="home">Shop now</a>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</tbody>

			</table>
		</div>

		<!-- Order Summary -->
		<div class="order-summary"
			style="margin-top: 3rem; padding: 1.5rem; border: 1px solid #eee; border-radius: 0.5rem; max-width: 400px;">
			<h3
				style="font-size: 1.5rem; font-weight: bold; margin-bottom: 1rem;">Order
				Summary</h3>
			<div
				style="display: flex; justify-content: space-between; margin-bottom: 0.5rem;">
				<span>Sub Total</span> <span>$${cartTotal}</span>
			</div>
			<div
				style="display: flex; justify-content: space-between; margin-bottom: 0.5rem;">
				<span>Shipping</span> <span>$5.0</span>
			</div>
			<div
				style="display: flex; justify-content: space-between; font-weight: bold; color: #6A5ACD; font-size: 1.2rem; border-top: 1px solid #eee; padding-top: 0.5rem;">
				<span>Total</span> <span>$${cartTotal+5.0}</span>
			</div>
			<div style="text-align: right; margin-top: 1rem;">
				<a class="btn btn-primary" href="cart-check-out">Check Out</a>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/pages/component/footer.jsp"%>
	<script>
		function validateQuantity(input) {
			var maxStock = parseInt(input.max);
			var value = parseInt(input.value);
			console.log("thi is validating quantity")
			if (value > maxStock) {
				input.value = 0;
			}
		}
	</script>
</body>


</html>