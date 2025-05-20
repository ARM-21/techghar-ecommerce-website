<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Categories</title>
    <style>
        /* Basic page styling */
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #f1f3f6;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 750px;
            margin: 60px auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
        }

        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }

        /* Form for adding new categories */
        form.add-form {
            display: flex;
            gap: 10px;
            justify-content: center;
            margin-bottom: 30px;
        }

        input[type="text"] {
            flex: 1;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 6px;
            font-size: 15px;
        }

        button {
            padding: 10px 16px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 500;
            font-size: 14px;
        }

        .add-btn {
            background-color: #28a745;
            color: #fff;
        }

        .edit-btn {
            background-color: #007bff;
            color: #fff;
        }

        .delete-btn {
            background-color: #dc3545;
            color: #fff;
        }

        /* Category list styling */
        ul.category-list {
            list-style: none;
            padding: 0;
        }

        ul.category-list li {
            background-color: #f8f9fa;
            margin-bottom: 12px;
            padding: 12px 15px;
            border-radius: 6px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            gap: 10px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.05);
        }

        .category-form {
            display: flex;
            flex: 1;
            gap: 10px;
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
            margin-top: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Manage Categories</h2>

        <!-- Form to add a new category -->
        <form action="add-category" method="post" class="add-form">
            <input type="text" name="categoryName" placeholder="Enter new category" required />
            <button type="submit" class="add-btn">Add</button>
        </form>

        <!-- Display existing categories -->
        <c:choose>
            <c:when test="${not empty categoryList}">
                <ul class="category-list">
                    <!-- Loop through each category -->
                    <c:forEach var="cat" items="${categoryList}">
                        <li>
                            <!-- Inline form for each category update -->
                            <form action="update-category" method="post" class="category-form">
                                <input type="hidden" name="categoryId" value="${cat.id}" />
                                <input type="text" name="updatedName" value="${cat.name}" required />
                                <div class="category-actions">
                                    <button type="submit" class="edit-btn">Update</button>
                                </div>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <!-- Message when no categories are available -->
                <p class="no-categories">No categories available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
