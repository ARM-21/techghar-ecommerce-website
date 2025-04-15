<%@page import="com.techghar.model.Product"%>
<%@page import="com.techghar.DAO.ProductDAO"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int productId = Integer.parseInt(request.getParameter("id"));
    ProductDAO dao = new ProductDAO();
    Product product = dao.getProductById(productId); 
%>

<!DOCTYPE html>
<html>
<head>
    <title><%= product.getName() %> - Product Details</title>
    <link rel="stylesheet" href="css/product-details.css">
</head>
<body>

<div class="product-detail-container">
    <div class="product-image">
        <img src="<%= product.getImageUrl() %>" alt="<%= product.getName() %>">
    </div>
    <div class="product-info">
        <h1><%= product.getName() %></h1>
        <p class="price">$<%= product.getPrice() %></p>
        <p><strong>Stock:</strong> <%= product.getStock() %></p>
        <p><strong>Brand:</strong> <%= product.getBrand() %></p>
        <p><strong>Category:</strong> <%= product.getCategory() %></p>
        <p><strong>Description:</strong> <%= product.getDescription() %></p>
        <p><strong>Rating:</strong>
            <% int rating = product.getRating(); %>
            <% for (int i = 0; i < 5; i++) { %>
                <span class="star <%= i < rating ? "filled" : "" %>">&#9733;</span>
            <% } %>
        </p>
        <button class="add-to-cart" data-id="<%= product.getId() %>">Add to Cart</button>
    </div>
</div>

</body>
</html>
