<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Manage Brands</title>
    <style>
        .container {
            width: 60%;
            margin: auto;
            padding-top: 30px;
        }
        .brand-list {
            list-style-type: none;
            padding: 0;
        }
        .brand-list li {
            margin: 10px 0;
        }
        input[type="text"] {
            padding: 6px;
            margin-right: 10px;
            width: 200px;
        }
        .add-btn, .edit-btn, .delete-btn {
            padding: 6px 12px;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }
        .add-btn { background-color: #28a745; color: white; }
        .edit-btn { background-color: #007bff; color: white; }
        .delete-btn { background-color: #dc3545; color: white; }
        .brand-actions {
            display: inline-block;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Manage Brands</h2>

        <!-- Add Brand Form -->
        <form action="add-brand" method="post">
            <input type="text" name="brandName" placeholder="Enter new brand" required />
            <button type="submit" class="add-btn">Add</button>
        </form>

        <!-- List Brands -->
        <c:choose>
            <c:when test="${not empty brandList}">
                <ul class="brand-list">
                    <c:forEach var="brand" items="${brandList}">
                        <li>
                            <form action="update-brand" method="post" style="display:inline;">
                                <input type="hidden" name="brandId" value="${brand.id}" />
                                <input type="text" name="updatedName" value="${brand.name}" required />
                                <div class="brand-actions">
                                    <button type="submit" class="edit-btn">Update</button>
                                </div>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p class="no-brands">No brands available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
