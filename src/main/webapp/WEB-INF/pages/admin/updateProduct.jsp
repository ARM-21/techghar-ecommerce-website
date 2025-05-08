<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
.add-product-form {
    max-width: 600px;
    margin: 0 auto;
    background-color: #fff;
    border-radius: 10px;
    padding: 30px 40px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.add-product-form h2 {
    text-align: center;
    margin-bottom: 25px;
    color: #333;
}

.form-group {
    margin-bottom: 20px;
}

.form-group label {
    display: block;
    margin-bottom: 8px;
    font-weight: bold;
    color: #555;
}

.form-group input[type="text"],
.form-group input[type="number"],
.form-group input[type="file"],
.form-group textarea,
.form-group select {
    width: 100%;
    padding: 10px 12px;
    border: 1px solid #ccc;
    border-radius: 6px;
    box-sizing: border-box;
    font-size: 14px;
    background-color: #fdfdfd;
    transition: border-color 0.3s ease;
}

.form-group input:focus,
.form-group textarea:focus,
.form-group select:focus {
    border-color: #007bff;
    outline: none;
}

textarea {
    resize: vertical;
    min-height: 100px;
}

.save-btn {
    background-color: #007bff;
    color: white;
    padding: 12px 18px;
    font-size: 16px;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    display: block;
    width: 100%;
    transition: background-color 0.3s ease;
}

.save-btn:hover {
    background-color: #0056b3;
}

hr {
    margin-top: 30px;
}

</style>

</head>

<body>
			<div class="add-product-form">
			<div class="prod-management-header">
			<h1>Product Management</h1>
		</div>
				<h2>Edit Product</h2>
				<form method="post" action="admin-update-save" enctype="multipart/form-data">
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
							name="imageFile"  required />
					</div>

					<button type="submit" class="save-btn">💾 Update Product</button>
				</form>
				<hr />
			</div>


</body>
</html>