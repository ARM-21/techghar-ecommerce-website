<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carousel Management - TechGhar Admin</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/admin.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin/carousel-admin.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
    <div class="admin-container">

        
        <div class="admin-content">
            <div class="admin-header">
                <h1>Carousel Management</h1>
                <a href="${pageContext.request.contextPath}/add-carousel-item" class="btn-add">
                    <i class="fas fa-plus"></i> Add New Carousel Item
                </a>
            </div>
            
            <!-- Success/Error Messages -->
            <c:if test="${not empty sessionScope.message}">
                <div class="alert alert-success">
                    ${sessionScope.message}
                    <% session.removeAttribute("message"); %>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.errorMessage}">
                <div class="alert alert-danger">
                    ${sessionScope.errorMessage}
                    <% session.removeAttribute("errorMessage"); %>
                </div>
            </c:if>
            
            <!-- Carousel Items Table -->
            <div class="table-responsive">
                <table class="admin-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Title</th>
                            <th>Description</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${carouselItems}">
                            <tr>
                                <td>${item.id}</td>
                                <td>${item.title}</td>
                                <td class="description-cell">
                                    <div class="truncate-text">${item.description}</div>
                                </td>
                                <td>
                                    <img src="${pageContext.request.contextPath}${item.imageUrl}" 
                                         alt="${item.title}" class="carousel-thumbnail">
                                </td>
                                <td class="actions-cell">
                                    <a href="#" onclick="confirmDelete(${item.id}, '${item.title}')" class="btn-delete" title="Delete">
                                        <i class="fas fa-trash"></i>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- No Items Message -->
            <c:if test="${empty carouselItems}">
                <div class="no-items-message">
                    <p>No carousel items found. Click "Add New Carousel Item" to create one.</p>
                </div>
            </c:if>
        </div>
    </div>
    
    <!-- Delete Confirmation Modal -->
    <div id="deleteModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Confirm Deletion</h2>
            <p>Are you sure you want to delete the carousel item "<span id="itemTitle"></span>"?</p>
            <p>This action cannot be undone.</p>
            <div class="modal-actions">
                <button id="cancelDelete" class="btn-secondary">Cancel</button>
                <a id="confirmDelete" href="#" class="btn-danger">Delete</a>
            </div>
        </div>
    </div>
    
    <script>
        // Delete confirmation modal functionality
        const modal = document.getElementById("deleteModal");
        const closeBtn = document.getElementsByClassName("close")[0];
        const cancelBtn = document.getElementById("cancelDelete");
        const confirmBtn = document.getElementById("confirmDelete");
        const itemTitleSpan = document.getElementById("itemTitle");
        
        function confirmDelete(id, title) {
            modal.style.display = "block";
            itemTitleSpan.textContent = title;
            confirmBtn.href = "${pageContext.request.contextPath}/delete-carousel-item?id=" + id;
        }
        
        closeBtn.onclick = function() {
            modal.style.display = "none";
        }
        
        cancelBtn.onclick = function() {
            modal.style.display = "none";
        }
        
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
    </script>
</body>
</html>
