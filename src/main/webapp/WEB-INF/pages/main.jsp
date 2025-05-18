<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
<style type="text/css">

/* Product Section CSS */
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
}

body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	color: var(--text-color);
	background-color: var(--light-color);
	margin: 0;
	padding: 0;
}

/* Container */
.container {
	width: 100%;
	max-width: 1200px;
	margin: 0 auto;
	padding: 0 15px;
}

/* Section Title */
.section-title {
	text-align: center;
	margin-bottom: 40px;
	position: relative;
	font-weight: 700;
	font-size: 2.2rem;
	display: inline-block;
	position: relative;
	z-index: 1;
	color: var(--dark-color);
	width: 100%;
}

.section-title::after {
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

/* Products Section */
.products-section {
	padding: 40px 0;
}

/* Products Header */
.products-header {
	display: flex;
	justify-content: space-between;
	align-items: center;
	margin-bottom: 30px;
	flex-wrap: wrap;
	gap: 15px;
}

.products-found {
	font-weight: 500;
	color: var(--dark-color);
}

.sorting-container {
	display: flex;
	align-items: center;
}

.sorting-label {
	margin-right: 10px;
	font-weight: 500;
}

.sorting-select {
	height: 40px;
	border-radius: var(--border-radius);
	border: 1px solid #e2e8f0;
	padding: 0 15px;
	font-size: 0.95rem;
	color: var(--dark-color);
	background-color: white;
}

/* Product Grid */
.product-grid {
	display: flex;
	flex-wrap:wrap;
	gap: 25px;
	justify-content:center;
}

/* Product Cards */
.product-card {
	background-color: #fff;
	border-radius: var(--border-radius);
	overflow: hidden;
	box-shadow: var(--box-shadow);
	transition: all 0.3s ease;
	position: relative;
	height: 100%;
	display: flex;
	flex-direction: column;
	padding: 10px;
}

.product-card:hover {
	transform: translateY(-10px);
	box-shadow: 0 20px 25px -5px rgba(0, 0, 0, 0.1), 0 10px 10px -5px
		rgba(0, 0, 0, 0.04);
}

.product-badge {
	position: absolute;
	top: 15px;
	left: 15px;
	background-color: var(--accent-color);
	color: white;
	padding: 5px 15px;
	border-radius: 20px;
	font-weight: 600;
	font-size: 0.8rem;
	z-index: 1;
}

.product-img-container {
	height: 220px;
	background-color: var(--light-color);
	display: flex;
	align-items: center;
	justify-content: center;
	padding: 20px;
	position: relative;
	overflow: hidden;
}

.product-img {
	max-height: 180px;
	max-width: 100%;
	object-fit: contain;
	transition: transform 0.5s ease;
}

.product-card:hover .product-img {
	transform: scale(1.05);
}

.product-content {
	padding: 20px;
	display: flex;
	flex-direction: column;
	flex-grow: 1;
}

.product-category {
	font-size: 0.8rem;
	color: var(--accent-color);
	text-transform: uppercase;
	letter-spacing: 1px;
	margin-bottom: 8px;
	font-weight: 500;
}

.product-title {
	font-weight: 600;
	font-size: 1.1rem;
	margin-bottom: 10px;
	color: var(--dark-color);
	height: 50px;
	overflow: hidden;
	display: -webkit-box;
	-webkit-line-clamp: 2;
	-webkit-box-orient: vertical;
}

.product-price {
	font-size: 1.3rem;
	font-weight: 700;
	color: var(--dark-color);
	margin-bottom: 10px;
}

.product-stock {
	font-size: 0.9rem;
	margin-bottom: 15px;
	color: var(--text-color);
}

.product-rating {
	color: #ffc107;
	margin-bottom: 15px;
	font-size: 0.9rem;
}

.rating-value {
	margin-left: 5px;
	color: var(--text-color);
}

.add-to-cart {
	width: 100%;
	padding: 10px 0;
	background-color: var(--accent-color);
	color: white;
	border: none;
	border-radius: var(--border-radius);
	font-weight: 500;
	transition: all 0.3s ease;
	cursor: pointer;
	margin-top: auto;
}

.add-to-cart:hover {
	background-color: #5851e6;
}

/* Star rating fallback for Font Awesome */
/* If you don't want to use Font Awesome, replace these with your own icons */
.fa-star::before {
	content: "★";
}

.fa-star-half-alt::before {
	content: "★";
	position: relative;
	overflow: hidden;
	display: inline-block;
	width: 0.5em;
}

.far.fa-star::before {
	content: "☆";
}

.product-card {
	background-color: #fff;
	border-radius: 12px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.08);
	overflow: hidden;
	height:400px;
	width:300px;
	transition: transform 0.2s ease-in-out;
}

.product-card:hover {
	transform: translateY(-5px);
}

.product-img-container {
	width: 100%;
	height: 200px;
	overflow: hidden;
	background-color: #f1f1f1;
	display: flex;
	justify-content: center;
	align-items: center;
}

.product-img {
	max-width: 100%;
	max-height: 100px;
	object-fit: co;
}

.product-details {
	padding: 1rem;
}

.product-name {
	font-size: 1.1rem;
	font-weight: bold;
	margin-bottom: 0.5rem;
	color: #333;
}

.product-price {
	color: #e91e63;
	font-size: 1rem;
	margin-bottom: 0.3rem;
}

.product-stock, .product-brand, .product-category {
	font-size: 0.9rem;
	color: #666;
	margin-bottom: 0.2rem;
}

.product-rating {
	margin: 0.5rem 0;
}

.star {
	font-size: 1rem;
	color: #ccc;
}

.star.filled {
	color: #ffc107;
}

.add-to-cart {
	background-color: #6200ea;
	color: #fff;
	border: none;
	padding: 0.6rem 1.2rem;
	font-size: 0.95rem;
	width: 90%;
	margin: 0.8rem auto 1rem;
	display: block;
	border-radius: 8px;
	cursor: pointer;
	transition: background-color 0.2s ease-in-out;
}

.add-to-cart:hover {
	background-color: #3700b3;
}
/* Responsive Styles */
@media ( max-width : 992px) {
	.product-grid {
		display:flex;
	}
}

@media ( max-width : 768px) {
	.products-header {
		flex-direction: column;
		align-items: flex-start;
	}
	.section-title {
		font-size: 1.8rem;
	}
}

@media ( max-width : 576px) {
	.product-grid {
		grid-template-columns: repeat(auto-fill, minmax(100%, 1fr));
	}
	.product-img-container {
		height: 180px;
	}
	.container {
		width: 100%;
	}
	.section-title {
		font-size: 1.5rem;
	}
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/pages/component/carousel.jsp" />
	<!-- Product Section -->
	<section class="products-section">
	<div class="container">
		<h2 class="section-title">Products</h2>

		<div class="products-header">
			<div class="products-found">
				<span>${products.size()} Products Found</span>
			</div>
			<div class="sorting-container">
				<span class="sorting-label">Sort by:</span> <select
					class="sorting-select">
					<option value="price-low">Price: Low to High</option>
					<option value="price-high">Price: High to Low</option>
					<option value="newest">Newest First</option>
				</select>
			</div>
		</div>

		<div class="product-grid">
			<c:forEach var="product" items="${products}">


				<div class="product-card">
					<div class="product-img-container">
						<a href="product-details?id=${product.id}"> <img
							src="${product.imageURL}" alt="${product.name}"
							class="product-img" />
						</a>
					</div>

					<div class="product-details">
						<a href="product-details?id=${product.id}">
							<h3 class="product-name">${product.name}</h3>
							<div class="product-price">Price: $${product.price}</div>
							<div class="product-brand">Brand: ${product.brandName}</div>
							<div class="product-category">Category: ${product.categoryName}</div>
						</a>
					</div>
					<a href="product-details?id=${product.id}">
					<button class="add-to-cart">view Product</button>
				</a>
				</div>
				
		

		</c:forEach>

</div>

	</div>
	</section>
	<script>
</script>

</body>
</html>