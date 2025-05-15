<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Products Management</title>
<style>
/* admin-products.css */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

body {
	background-color: #f8f9fa;
	color: #333333;
}

.container {
	margin-left: 250px;
	margin-top: 10px;
	padding: 20px;
	transition: all 0.3s ease;
	max-width: 900px;
}

.add-btn {
	background-color: #6c63ff;
	color: white;
	padding: 10px 15px;
	border-radius: 5px;
	text-decoration: none;
	font-weight: 500;
	transition: background-color 0.3s;
}

.add-btn:hover {
	background-color: #5a52d5;
}

/* Search and Filter */
.search-filter {
	display: flex;
	margin-bottom: 20px;
	gap: 10px;
	background-color: white;
	padding: 15px;
	border-radius: 8px;
	box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

.search-filter input {
	flex: 1;
	padding: 10px 15px;
	border: 1px solid #ddd;
	border-radius: 5px;
	font-size: 14px;
}

.filter-btn {
	background-color: #6c63ff;
	color: white;
	border: none;
	padding: 10px 15px;
	border-radius: 5px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.filter-btn:hover {
	background-color: #5a52d5;
}

/* Product List */
.product-list {
	display: flex;
	flex-wrap: wrap;
	gap: 20px;
	margin-bottom: 30px;
	justify-content: center;
}

.product-card {
	background-color: white;
	border-radius: 8px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	position: relative;
	transition: transform 0.3s, box-shadow 0.3s;
	max-width: 300px;
}

.product-card:hover {
	transform: translateY(-5px);
	box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
}

.product-card .id {
	position: absolute;
	top: 10px;
	right: 10px;
	background-color: #f8f9fa;
	color: #666;
	padding: 3px 8px;
	border-radius: 12px;
	font-size: 12px;
}

.product-card .info {
	padding: 20px;
}

.product-card h3 {
	color: #333;
	margin-bottom: 10px;
	font-size: 18px;
}

.product-card p {
	color: #666;
	margin-bottom: 8px;
	font-size: 14px;
	line-height: 1.4;
}

.product-card .actions {
	display: flex;
	justify-content: flex-end;
	padding: 10px 20px;
	background-color: #f8f9fa;
	border-top: 1px solid #eee;
}

.product-card .actions a {
	margin-left: 15px;
	text-decoration: none;
	font-size: 18px;
}

.product-card .actions .edit {
	color: #6c63ff;
}

.product-card .actions .delete {
	color: #ff6b6b;
}

.info {
	max-width: 400px;
}

/* Pagination */
.pagination {
	display: flex;
	justify-content: center;
	margin-top: 30px;
}

.pagination a {
	color: #6c63ff;
	text-decoration: none;
	padding: 8px 12px;
	margin: 0 5px;
	border-radius: 4px;
	transition: background-color 0.3s;
}

.pagination a:hover {
	background-color: #f1f1f1;
}

.pagination a.active {
	background-color: #6c63ff;
	color: white;
}

/* Responsive adjustments */
@media ( max-width : 992px) {
	.container {
		margin-left: 0;
	}
	.sidebar {
		transform: translateX(-100%)
	}
}

.add-product-form {
	background-color: #fff;
	padding: 2rem;
	border-radius: 12px;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
	max-width: 800px;
	margin: 2rem auto;
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

.add-product-form h2 {
	margin-bottom: 1.5rem;
	font-size: 1.8rem;
	color: #333;
	text-align: center;
}

.add-product-form form {
	display: flex;
	flex-direction: column;
	gap: 1.2rem;
}

.form-group {
	display: flex;
	flex-direction: column;
}

.form-group label {
	font-weight: 600;
	margin-bottom: 0.5rem;
	color: #444;
}

.form-group input, .form-group textarea, .form-group select {
	padding: 0.7rem;
	border-radius: 8px;
	border: 1px solid #ccc;
	font-size: 1rem;
	transition: border-color 0.3s ease;
}

.form-group input:focus, .form-group textarea:focus, .form-group select:focus
	{
	border-color: #007bff;
	outline: none;
}

textarea {
	resize: vertical;
	min-height: 100px;
}

.save-btn {
	padding: 0.8rem 1.2rem;
	background-color: #28a745;
	color: white;
	border: none;
	border-radius: 8px;
	font-size: 1rem;
	font-weight: 600;
	cursor: pointer;
	transition: background-color 0.3s ease;
}

.save-btn:hover {
	background-color: #218838;
}

hr {
	margin-top: 2rem;
	border: 0;
	height: 1px;
	background: #eee;
}

@media ( max-width : 768px) {
	.search-filter {
		flex-direction: column;
	}
	.pagination a {
		padding: 6px 10px;
	}
	.prod-management-header {
		display: flex;
		flex-direction: column;
		justify-content: center;
		width: 90%;
		margin: auto;
	}
	.product-list {
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		gap: 20px;
		margin-bottom: 30px;
		align-items: center;
		align-content: center;
	}
}

@media ( max-width : 480px) {
	.product-list {
		display: flex;
		flex-direction: column;
		flex-wrap: wrap;
		gap: 20px;
		margin-bottom: 30px;
		align-items: center;
		align-content: center;
	}
	.product-card {
		width: 90%;
	}
}

.prod-management-header {
	display: flex;
	justify-content: space-between;
}

.product-image {
	width: 100%;
	height: 171px;
	overflow: hidden;
	border-bottom: 1px solid #eee;
}

.product-image img {
	width: 100%;
	height: 100%;
	object-fit: cover;
	display: block;
	transition: transform 0.3s ease;
}

.alert {
	padding: 15px;
	margin: 20px 0;
	border-radius: 8px;
	font-weight: bold;
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
</style>
</head>


<body>



	<div class="container">


		<%
		String message = request.getParameter("message");
		if (message != null && !message.isEmpty()) {
		%>
		<div style="color: green;"  class= "deleted-message"><%=message%></div>
		<%
		}
		%>

		<c:if test="${not empty message}">
			<div class="alert ${messageType}">${message}</div>
		</c:if>
		<c:if test="${not empty param.message}">
			<div class="alert alert-success">${param.message}</div>
		</c:if>


		<div class="prod-management-header">
			<h1>Product Management</h1>
			<a href="add-product" class="add-btn">+ Add New Product</a>
		</div>
		<c:if test="${addNewProduct == true}">
			<div class="add-product-form">
				<h2>Add New Product</h2>
				<form method="post" action="save-product"
					enctype="multipart/form-data">
					<div class="form-group">
						<label for="name">Product Name</label> <input type="text"
							name="name" required />
					</div>

					<div class="form-group">
						<label for="description">Description</label>
						<textarea name="description" required></textarea>
					</div>

					<div class="form-group">
						<label for="price">Price</label> <input type="number" step="0.01"
							name="price" required />
					</div>

					<div class="form-group">
						<label for="stock">Stock</label> <input type="number" name="stock"
							required />
					</div>

					<div class="form-group">
						<label for="brand">Brand</label> <select name="brandId" required>
							<option value="">-- Select Brand --</option>
							<c:forEach var="brand" items="${brands}">
								<option value="${brand.id}">${brand.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="category">Category</label> <select name="categoryId"
							required>
							<option value="">-- Select Category --</option>
							<c:forEach var="cat" items="${categories}">
								<option value="${cat.id}">${cat.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="form-group">
						<label for="rating">Rating</label> <input type="number"
							name="rating" step="0.1" min="0" max="5" required
							oninput="if(this.value > 5) this.value = 5;" />
					</div>

					<div class="form-group">
						<label for="imageUrl">Choose An Image</label> <input type="file"
							name="imageFile" required />
					</div>

					<button type="submit" class="save-btn">💾 Save Product</button>
				</form>
				<hr />
			</div>
		</c:if>

		<c:if test="${empty addNewProduct}">
			<div class="search-filter">
				<form method="get" action="admin-products">
					<input type="text" name="search" placeholder="Search products..." />
					<button type="submit" class="filter-btn">🔍</button>
				</form>
			</div>

		</c:if>
		<div class="product-list">
			<c:forEach var="product" items="${products}">
				<div class="product-card">
					<div class="id">#${product.id}</div>

					<!-- Product Image -->
					<div class="product-image">
						<img src="${product.imageURL}" alt="${product.name}" />
					</div>

					<div class="info">
						<h3>${product.name}</h3>
						<p>
							<strong>Description:</strong> ${product.description}
						</p>
						<p>
							<strong>Price:</strong> $${product.price}
						</p>
						<p>
							<strong>Stock:</strong> ${product.stock}
						</p>
						<p>
							<strong>Brand:</strong> ${product.brandName}
						</p>
						<p>
							<strong>Category:</strong> ${product.categoryName}
						</p>
						<p>
							<strong>Rating:</strong> ${product.rating} ⭐
						</p>
						<p>
							<strong>Created At:</strong> ${product.createdAt}
						</p>
					</div>

					<div class="actions">
						<a href="admin-update?id=${product.id}" class="edit">✏️</a>
						<form action="product-delete?id=${product.id}" method="post"
							class="delete">
							<button type="submit">Delete🗑️</button>
						</form>

					</div>
				</div>

			</c:forEach>
		</div>

		<div class="pagination">
			<a href="#">«</a> <a class="active" href="#">1</a> <a href="#">2</a>
			<a href="#">3</a> <a href="#">»</a>
		</div>
	</div>
	<script>

    setTimeout(() => {
        const alert = document.querySelector('.alert');
        if (alert) {
            alert.style.display = 'none';
            window.location.href = '/admin-products'
        }
    }, 4000);
    
    let deleteProduct = document.querySelector(".deleted-message")
    function deleteProductFunc(){
    	if(deleteProduct){
    		window.alert("deleted Successfully")
    		window.location.href = "/admin-products"
    		
    		
    			setTimeout(()=>{
    				
    				deleteProduct.style.display = "none";
    			},3000)
    			
    	}
    	
    	
    }
    deleteProductFunc()
    
    
    

</script>
</body>


</html>
