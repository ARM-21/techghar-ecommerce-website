<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Tech Products</title>
<style>
/* Base Styles */

/* Filter Section */
.filter-section {
    background-color: white;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    margin-right: 30px;
    width: 250px;
    float: left;
    position: sticky;
    top: 20px;
}

.filter-section h3 {
    color: #2c3e50;
    margin-top: 0;
    margin-bottom: 15px;
    font-size: 1.1em;
    border-bottom: 2px solid #3498db;
    padding-bottom: 5px;
}

/* Product Section */
.product-section {
    margin-left: 300px;
    padding: 20px;
}

.product-container {
    display: grid;
    grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
    gap: 20px;
}

/* Product Card */
.product-card {
    background: white;
    border-radius: 8px;
    padding: 20px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.2s;
    position: relative;
    display: flex;
    flex-direction: column;
}

.product-card:hover {
    transform: translateY(-5px);
    box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.product-image {
    width: 100%;
    height: 200px;
    object-fit: contain;
    margin-bottom: 15px;
    background: #f8f9fa;
    border-radius: 4px;
}

.product-title {
    color: #2c3e50;
    margin: 0 0 10px 0;
    font-size: 1.2em;
    font-weight: 600;
}

.product-brand {
    color: #7f8c8d;
    font-size: 0.9em;
    margin-bottom: 5px;
}

.product-description {
    color: #34495e;
    font-size: 0.95em;
    margin: 10px 0;
    flex-grow: 1;
}

.product-meta {
    display: flex;
    justify-content: space-between;
    margin: 10px 0;
    font-size: 0.9em;
    color: #7f8c8d;
}

.product-rating {
    color: #f1c40f;
    font-weight: bold;
}

.product-stock {
    color: ${product.stock > 0 ? '#27ae60' : '#e74c3c'};
    font-weight: bold;
}

.product-price {
    color: #27ae60;
    font-size: 1.4em;
    font-weight: bold;
    margin: 10px 0;
}

.product-date {
    font-size: 0.8em;
    color: #95a5a6;
    margin-top: 5px;
}

.add-to-cart {
    background-color: #2ecc71;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
    margin-top: 15px;
    transition: background-color 0.2s;
}

.add-to-cart:hover {
    background-color: #27ae60;
}

.add-to-cart:disabled {
    background-color: #95a5a6;
    cursor: not-allowed;
}

/* Responsive Design */
@media (max-width: 768px) {
    .filter-section {
        width: 100%;
        float: none;
        margin-right: 0;
        margin-bottom: 30px;
        position: static;
    }
    .product-section {
        margin-left: 0;
    }
}
</style>
</head>
<body>
    <div class="filter-section">
        <form action="search-catalog" method="POST">
            <h3>Categories</h3>
            <c:forEach items="${categories}" var="category">
                <label> 
                    <input type="radio" name="category" required value="${category.id}"
                        <c:if test="${param.category == category.id}">checked</c:if>>
                    ${category.name}
                </label>
                <br>
            </c:forEach>

            <h3>Brands</h3>
            <c:forEach items="${brands}" var="brand">
                <label> 
                    <input type="radio" name="brand" required value="${brand.id}"
                        <c:if test="${param.brand == brand.id}">checked</c:if>>
                    ${brand.name}
                </label>
                <br>
            </c:forEach>

            <h3>Price Range</h3>
            <input type="number" name="minPrice" placeholder="Min" value="${param.minPrice}"> 
            <input type="number" name="maxPrice" placeholder="Max" value="${param.maxPrice}">

            <button type="submit">Apply Filters</button>
        </form>
    </div>

    <div class="product-section">
        <p>${products.size()} Products Found</p>

<div class="product-container">
    <c:forEach var="product" items="${products}">
        <div class="product-card">
            <img src="${product.imageURL}" alt="${product.name}" class="product-image">

            <h3 class="product-title">${product.name}</h3>
            <div class="product-brand">${product.brandName}</div>

            <div class="product-description">
                ${product.description}
            </div>

            <div class="product-meta">
                <span class="product-stock">
                    <c:choose>
                        <c:when test="${product.stock > 0}">
                            In Stock (${product.stock})
                        </c:when>
                        <c:otherwise>
                            Out of Stock
                        </c:otherwise>
                    </c:choose>
                </span>
            </div>

            <div class="product-price">$${String.format("%.2f", product.price)}</div>
            <div class="product-date">Added: ${product.createdAt}</div>

            <form action="${pageContext.request.contextPath}/add-to-cart" method="post">
                <input type="hidden" name="productId" value="${product.id}" />
                <button type="submit"
                        class="add-to-cart"
                        ${product.stock <= 0 ? 'disabled' : ''}>
                    ${product.stock > 0 ? 'Add to Cart' : 'Out of Stock'}
                </button>
            </form>
        </div>
    </c:forEach>
</div>
        </div>
    </div>
</body>
</html>