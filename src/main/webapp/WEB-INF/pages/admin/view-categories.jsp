<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Categories</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 700px;
            margin: 60px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        form {
            margin-bottom: 30px;
            display: flex;
            gap: 10px;
            justify-content: center;
        }

        input[type="text"] {
            padding: 8px;
            flex: 1;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button {
            padding: 8px 14px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .add-btn {
            background-color: #28a745;
            color: white;
        }

        .edit-btn {
            background-color: #007bff;
            color: white;
        }

        .delete-btn {
            background-color: #dc3545;
            color: white;
        }

        ul.category-list {
            list-style: none;
            padding: 0;
        }

        ul.category-list li {
            padding: 10px 15px;
            margin-bottom: 10px;
            background-color: #f0f2f5;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        .category-actions {
            display: flex;
            gap: 8px;
        }

        .no-categories {
            text-align: center;
            color: #777;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Manage Categories</h2>

        <!-- Add Category Form -->
        <form action="add-category" method="post">
            <input type="text" name="categoryName" placeholder="Enter new category" required />
            <button type="submit" class="add-btn">Add</button>
        </form>

        <!-- List Categories -->
        <c:choose>
            <c:when test="${not empty categoryList}">
                <ul class="category-list">
                    <c:forEach var="cat" items="${categoryList}">
                        <li>
                            <form action="update-category" method="post" style="display:inline;">
                                <input type="hidden" name="categoryId" value="${cat.id}" />
                                <input type="text" name="updatedName" value="${cat.name}" required />
                                <div class="category-actions">
                                    <button type="submit" class="edit-btn">Update</button>
                                </div>
                            </form>
                            <form action="delete-category" method="post" style="display:inline;">
                                <input type="hidden" name="categoryId" value="${cat.id}" />
                                <button type="submit" class="delete-btn">Delete</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p class="no-categories">No categories available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
