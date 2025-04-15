<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tech Products</title>
    <style>
        <style>
    /* Base Styles */
    body {
        font-family: 'Arial', sans-serif;
        margin: 0;
        padding: 20px;
        background-color: #f5f5f5;
    }

    /* Filter Section */
    .filter-section {
        background-color: white;
        padding: 25px;
        border-radius: 8px;
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
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

    /* Checkbox Styles */
    label {
        display: block;
        margin: 8px 0;
        cursor: pointer;
        color: #34495e;
    }

    input[type="checkbox"] {
        margin-right: 10px;
        accent-color: #3498db;
    }

    /* Price Range */
    input[type="number"] {
        width: 100px;
        padding: 8px;
        margin: 5px 0;
        border: 1px solid #ddd;
        border-radius: 4px;
    }

    /* Rating Dropdown */
    select {
        width: 100%;
        padding: 8px;
        margin: 10px 0;
        border: 1px solid #ddd;
        border-radius: 4px;
        background-color: white;
    }

    /* Apply Filters Button */
    button[type="submit"] {
        background-color: #3498db;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
        margin-top: 15px;
        font-weight: bold;
    }

    button[type="submit"]:hover {
        background-color: #2980b9;
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
        box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        transition: transform 0.2s;
        position: relative;
    }

    .product-card:hover {
        transform: translateY(-5px);
    }

    .product-card h3 {
        color: #2c3e50;
        margin: 10px 0;
        font-size: 1.2em;
    }

    .product-card p {
        color: #7f8c8d;
        margin: 8px 0;
    }

    /* New Badge */
    .product-card span {
        position: absolute;
        top: 15px;
        right: 15px;
        background: #e74c3c;
        color: white;
        padding: 3px 8px;
        border-radius: 3px;
        font-size: 0.8em;
    }

    /* Rating Stars */
    .rating {
        color: #f1c40f;
        font-size: 1.2em;
        margin: 10px 0;
    }

    /* Price */
    .product-card p:last-of-type {
        color: #27ae60;
        font-size: 1.4em;
        font-weight: bold;
    }

    /* Add to Cart Button */
    .product-card button {
        background-color: #2ecc71;
        color: white;
        padding: 10px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        width: 100%;
        margin-top: 15px;
    }

    .product-card button:hover {
        background-color: #27ae60;
    }

    /* Products Found Counter */
    .product-section > p {
        color: #7f8c8d;
        margin-bottom: 20px;
        font-size: 0.9em;
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
    </style>
</head>
<body>
    <div class="filter-section">
        <form action="ProductServlet" method="get">
            <h3>Categories</h3>
            <c:forEach items="${categories}" var="category">
                <label>
                    <input type="checkbox" name="category" value="${category.id}" 
                           <c:if test="${paramValues.category.contains(category.id)}">checked</c:if>>
                    ${category.name}
                </label><br>
            </c:forEach>

            <h3>Brands</h3>
            <c:forEach items="${brands}" var="brand">
                <label>
                    <input type="checkbox" name="brand" value="${brand.id}" 
                           <c:if test="${paramValues.brand.contains(brand.id)}">checked</c:if>>
                    ${brand.name}
                </label><br>
            </c:forEach>

            <h3>Price Range</h3>
            <input type="number" name="minPrice" placeholder="Min" value="${param.minPrice}">
            <input type="number" name="maxPrice" placeholder="Max" value="${param.maxPrice}">

            <h3>Rating</h3>
            <select name="minRating">
                <option value="0" ${param.minRating == 0 ? 'selected' : ''}>Any Rating</option>
                <option value="3" ${param.minRating == 3 ? 'selected' : ''}>★★★☆☆ & Up</option>
                <option value="4" ${param.minRating == 4 ? 'selected' : ''}>★★★★☆ & Up</option>
            </select>

            <button type="submit">Apply Filters</button>
        </form>
    </div>

    <div class="product-section">
        <p>${products.size()} Products Found</p>
        
        <div class="product-container">
               <c:forEach var="product" items="${products}">
                <div class="product-card">
                    <div class="product-img-container">
                        <a href="product-detail.jsp?id=${product.id}">
                            <img src="" alt="${product.name}" class="product-img">
                        </a>
                    </div>
    
                        <div class="product-price">$${product.price}</div>
                        <div class="product-stock">Stock: ${product.stock}</div>
                        <button class="add-to-cart" data-id="${product.id}" data-name="${product.name}" data-price="${product.price}">Add to Cart</button>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>