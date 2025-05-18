<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Orders</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 20px;
}

h2 {
	color: #333;
}

.order-container {
	border: 1px solid #ccc;
	padding: 15px;
	margin-bottom: 30px;
	border-radius: 5px;
	background-color: #f9f9f9;
}

.order-container p {
	margin: 5px 0;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 10px;
}

table, th, td {
	border: 1px solid #bbb;
}

th {
	background-color: #eee;
	padding: 8px;
	text-align: left;
}

td {
	padding: 8px;
}
</style>
</head>
<body>
	<div class="order-container">

		<h2>Orders</h2>

		<c:forEach var="order" items="${orders}">
			<div
				style="border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;">
				<p>
					<strong>Order ID:</strong> ${order.orderId}
				</p>
				<p>
					<strong>User ID:</strong> ${order.userId}
				</p>
				<p>
					<strong>Total Price:</strong> $${order.totalPrice}
				</p>
				<p>
					<strong>Order Date:</strong> ${order.orderDate}
				</p>

				<c:if test="${order.delivery != null}">
					<p>
						<strong>Delivery Address:</strong> ${order.delivery.address}
					</p>
					<p>
						<strong>Delivery Phone:</strong> ${order.delivery.phone}
					</p>
					<p>
						<strong>Status:</strong> ${order.delivery.deliveryStatus}
					</p>
					<p>
					<form action="UpdateDeliveryStatusServlet" method="post">
						<input type="hidden" name="orderId" value="${order.orderId}" /> <label
							for="statusSelect">Update Status:</label> <select
							name="deliveryStatus" id="statusSelect">
							<option value="Pending">Pending</option>
							<option value="Shipped">Shipped</option>
							<option value="Out for Delivery">Out for Delivery</option>
							<option value="Delivered">Delivered</option>
							<option value="Cancelled">Cancelled</option>
							<option value="Returned">Returned</option>
						</select>
						<button type="submit">Update</button>
					</form>
					</p>

					<p>
						<strong>Delivery Date:</strong> ${order.delivery.deliveryDate}
					</p>
				</c:if>

				<h4>Items:</h4>
				<table border="1" cellpadding="5">
					<thead>
						<tr>
							<th>Product Name</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${order.items}">
							<tr>
								<td>${item.productName}</td>
								<td>${item.quantity}</td>
								<td>$${item.price}</td>
								<td>$${item.subtotal}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>

</body>
</html>