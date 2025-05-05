<%@page import="com.techghar.model.Product"%>
<%@page import="com.techghar.DAO.ProductDAO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>


<!DOCTYPE html>
<html>
<head>
    <title><c:out value="${product.name}" /> - Product Details</title>
    <link rel="stylesheet" href="css/product-details.css">
</head>
<body>

    <div class="product-detail-container">
        <div class="product-image">
            <img src="<c:out value="${product.imageUrl}" />" alt="<c:out value="${product.name}" />">
        </div>
        <div class="product-info">
            <h1><c:out value="${product.name}" /></h1>
            <p class="price">$<c:out value="${product.price}" /></p>
            <p>
                <strong>Stock:</strong> <c:out value="${product.stock}" />
            </p>
            <p>
                <strong>Brand:</strong> <c:out value="${product.brandName}" />
            </p>
            <p>
                <strong>Category:</strong> <c:out value="${product.categoryName}" />
            </p>
            <p>
                <strong>Description:</strong> <c:out value="${product.description}" />
            </p>
            <strong>Rating:</strong>
            <c:forEach var="i" begin="0" end="4">
                <span class="star ${i < product.rating ? 'filled' : ''}">&#9733;</span>
            </c:forEach>

            <c:if test="${product.stock > 0}">
                <form action="/add-to-cart" method="post">
                    <input type="hidden" name="productId" value="${product.id}" />
                    <label for="quantity" class="form-label">Quantity:</label>
                   <input type="number" name="quantity" id="quantity" class="form-control w-25" 
                           min="1" max="${product.stock}" value="1" required 
                           onchange="validateQuantity(this)" />
                    <button type="submit" class="add-to-cart">Add to Cart</button>
                </form>
            </c:if>

            <c:if test="${product.stock == 0}">
                <p class="text-danger fw-bold">Out of Stock</p>
                <button class="btn btn-secondary mt-2" disabled>Add to Cart</button>
            </c:if>
        </div>
    </div>

<script>
function validateQuantity(input) {
    var maxStock = parseInt(input.max);
    var value = parseInt(input.value);
    if (value > maxStock) {
        input.value = maxStock;
    }
}
</script>
</body>
</html>
