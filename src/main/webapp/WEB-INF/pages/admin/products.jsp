<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Products Management</title>
<style>
/* Reset default browser styles */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Main page styling */
body {
    background-color: #f8f9fa;
    color: #333333;
    line-height: 1.6;
}

/* Container for all content */
.container {
    padding: 20px;
    max-width: 1200px;
    margin: 0 auto;
}

/* Header section styles */
.prod-management-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 30px;
    padding-bottom: 15px;
    border-bottom: 1px solid #e0e0e0;
}

.prod-management-header h1 {
    font-size: 28px;
    color: #2c3e50;
}

/* Button styles */
.add-btn {
    background-color: #6c63ff;
    color: white;
    padding: 10px 20px;
    border-radius: 5px;
    text-decoration: none;
    font-weight: 500;
    transition: background-color 0.3s;
    display: inline-flex;
    align-items: center;
    gap: 8px;
}

.add-btn:hover {
    background-color: #5a52d5;
}

/* Search bar styling */
.search-filter {
    display: flex;
    margin-bottom: 25px;
    background-color: white;
    padding: 15px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.05);
}

.search-filter input {
    flex: 1;
    padding: 10px 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
}

.search-filter button {
    background-color: #6c63ff;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    margin-left: 10px;
    transition: background-color 0.3s;
}

.search-filter button:hover {
    background-color: #5a52d5;
}

/* Product table styling */
.product-table {
    width: 100%;
    border-collapse: collapse;
    background-color: white;
    box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    border-radius: 8px;
    overflow: hidden;
}

.product-table th, 
.product-table td {
    padding: 12px 15px;
    text-align: left;
    border-bottom: 1px solid #eee;
}

.product-table th {
    background-color: #6c63ff;
    color: white;
    font-weight: 600;
}

.product-table tr:hover {
    background-color: #f9f9f9;
}

/* Product image in table */
.table-img {
    width: 60px;
    height: 60px;
    object-fit: cover;
    border-radius: 4px;
}

/* Action buttons in table */
.product-table .edit,
.product-table .delete {
    text-decoration: none;
    font-size: 18px;
    margin-right: 10px;
}

.product-table .edit {
    color: #6c63ff;
}

.product-table .delete {
    color: #ff6b6b;
}

/* Form for adding new products */
.add-product-form {
    background-color: white;
    padding: 25px;
    border-radius: 8px;
    box-shadow: 0 2px 10px rgba(0,0,0,0.05);
    margin: 30px auto;
    max-width: 700px;
}

.add-product-form h2 {
    margin-bottom: 20px;
    color: #2c3e50;
    text-align: center;
}

.form-group {
    margin-bottom: 15px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    font-weight: 500;
    color: #555;
}

.form-group input,
.form-group textarea,
.form-group select {
    width: 100%;
    padding: 10px 15px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 16px;
}

.form-group textarea {
    min-height: 100px;
    resize: vertical;
}

.save-btn {
    background-color: #28a745;
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
    width: 100%;
    margin-top: 10px;
    transition: background-color 0.3s;
}

.save-btn:hover {
    background-color: #218838;
}

/* Message alerts */
.alert {
    padding: 15px;
    margin: 20px 0;
    border-radius: 5px;
    font-weight: 500;
    text-align: center;
}

.alert.success {
    background-color: #d4edda;
    color: #155724;
    border: 1px solid #c3e6cb;
}

.alert.error {
    background-color: #f8d7da;
    color: #721c24;
    border: 1px solid #f5c6cb;
}

/* Responsive design for mobile */
@media (max-width: 768px) {
    .prod-management-header {
        flex-direction: column;
        align-items: flex-start;
        gap: 15px;
    }
    
    .search-filter {
        flex-direction: column;
    }
    
    .search-filter button {
        margin-left: 0;
        margin-top: 10px;
    }
    
    .product-table {
        display: block;
        overflow-x: auto;
    }
}
</style>
</head>

<body>
    <div class="container">
        <!-- Display success/error messages -->
        <%
        String message = request.getParameter("message");
        if (message != null && !message.isEmpty()) {
        %>
        <div class="alert success"><%=message%></div>
        <%
        }
        %>
        
        <c:if test="${not empty message}">
            <div class="alert ${messageType}">${message}</div>
        </c:if>
        
        <c:if test="${not empty param.message}">
            <div class="alert success">${param.message}</div>
        </c:if>

        <!-- Page header with title and add button -->
        <div class="prod-management-header">
            <h1>Product Management</h1>
            <a href="add-product" class="add-btn">+ Add New Product</a>
        </div>
        
        <!-- Show add product form if addNewProduct is true -->
        <c:if test="${addNewProduct == true}">
            <div class="add-product-form">
                <h2>Add New Product</h2>
                <form method="post" action="save-product" enctype="multipart/form-data">
                    <!-- Product name field -->
                    <div class="form-group">
                        <label for="name">Product Name</label>
                        <input type="text" name="name" required />
                    </div>
                    
                    <!-- Product description field -->
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea name="description" required></textarea>
                    </div>
                    
                    <!-- Price field -->
                    <div class="form-group">
                        <label for="price">Price</label>
                        <input type="number" step="0.01" name="price" required />
                    </div>
                    
                    <!-- Stock quantity field -->
                    <div class="form-group">
                        <label for="stock">Stock</label>
                        <input type="number" name="stock" required />
                    </div>
                    
                    <!-- Brand selection dropdown -->
                    <div class="form-group">
                        <label for="brand">Brand</label>
                        <select name="brandId" required>
                            <option value="">-- Select Brand --</option>
                            <c:forEach var="brand" items="${brands}">
                                <option value="${brand.id}">${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <!-- Category selection dropdown -->
                    <div class="form-group">
                        <label for="category">Category</label>
                        <select name="categoryId" required>
                            <option value="">-- Select Category --</option>
                            <c:forEach var="cat" items="${categories}">
                                <option value="${cat.id}">${cat.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    
                    <!-- Image upload field -->
                    <div class="form-group">
                        <label for="imageUrl">Product Image</label>
                        <input type="file" name="imageFile" required />
                    </div>
                    
                    <!-- Submit button -->
                    <button type="submit" class="save-btn">💾 Save Product</button>
                </form>
            </div>
        </c:if>
        
        <!-- Show product list if not in add mode -->
        <c:if test="${empty addNewProduct}">
            <!-- Search form -->
            <div class="search-filter">
                <form method="get" action="admin-products">
                    <input type="text" name="search" placeholder="Search products..." />
                    <button type="submit">🔍 Search</button>
                </form>
            </div>
            
            <!-- Products table -->
            <table class="product-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Image</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Stock</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>#${product.id}</td>
                            <td><img src="${product.imageURL}" alt="${product.name}" class="table-img"/></td>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>$${product.price}</td>
                            <td>${product.stock}</td>
                            <td>
                                <a href="admin-update?id=${product.id}" class="edit">✏️</a>
                                <a href="product-delete?id=${product.id}" class="delete" 
                                   onclick="return confirm('Are you sure you want to delete this product?');">🗑️</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
    
    <script>
    // Hide success/error messages after 4 seconds
    setTimeout(() => {
        const alerts = document.querySelectorAll('.alert');
        alerts.forEach(alert => {
            alert.style.display = 'none';
        });
    }, 4000);
    </script>
</body>
</html>