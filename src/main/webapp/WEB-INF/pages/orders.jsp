
<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shopping Cart - TechGhar</title>
    
   	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/header.css" />
   
    
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

.order-box {
  border: 1px solid #ccc;
  border-radius: var(--border-radius);
  padding: 1.5rem;
  margin-bottom: 2rem;
  background-color: #fff;
  box-shadow: var(--box-shadow);
}

.order-box h3,
.order-box p {
  margin-bottom: 0.75rem;
}

.order-box table {
  margin-top: 1.5rem;
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

.summary-table th,
.summary-table td {
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




/* Responsive Design */
@media (max-width: 768px) {
  .cart-section {
    padding: 1rem;
  }

  .cart-section > div {
    flex-direction: column;
  }
}
		
  
    </style>
</head>
<body>
    <%@ include file="/WEB-INF/pages/component/header.jsp" %>

  
<div class="cart-section" style="padding: 2rem;">
    <h2 style="text-align: center; font-size: 2rem; font-weight: bold; margin-bottom: 2rem; border-bottom: 2px solid #ccc;">
        Orders
    </h2>


    <c:choose>
        <c:when test="${not empty orders}">
            <c:forEach var="order" items="${orders}">
                <div class="order-box">
                
                    <h3>Order ID: ${order.id}</h3>
                    <p><strong>Order Date:</strong> ${order.deliveryDetails.deliveryDate}</p>
                
                    <p><strong>Total Amount:</strong> $${order.totalAmount}</p>
                    
                    
                    <p><strong>Delivery Address:</strong> ${order.deliveryDetails.address}</p>
					<p><strong>Phone:</strong> ${order.deliveryDetails.phone}</p>
					
					<p><strong>Status:</strong> ${order.deliveryDetails.deliveryStatus}</p>
					
                    

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
					        <c:forEach var="item" items="${order.orderItems}">
					            <tr>
					                <td>${item.product.name}</td>
					                <td>$${item.price}</td>
					                <td>${item.quantity}</td>
					                <td>$${item.subtotal}</td>
					            </tr>
					        </c:forEach>
					    </tbody>
					</table>

                </div>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <p style="text-align: center;">You have no orders yet. <a href="home">Start shopping</a>!</p>
        </c:otherwise>
    </c:choose>
</div>


 <%@ include file="/WEB-INF/pages/component/footer.jsp" %>
 
 
</body>


</html>