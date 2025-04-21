<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Available Categories</title>
  <!--   <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"> --> 
    
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

        ul.category-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        ul.category-list li {
            padding: 15px 20px;
            margin-bottom: 10px;
            background-color: #f0f2f5;
            border-radius: 5px;
            font-size: 16px;
            color: #444;
            transition: background-color 0.3s ease;
        }

        ul.category-list li:hover {
            background-color: #e0e4ea;
        }

        .no-categories {
            text-align: center;
            color: #777;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Available Categories</h2>
        <c:choose>
            <c:when test="${not empty categoryList}">
                <ul class="list-group">
                    <c:forEach var="cat" items="${categoryList}">
                        <li class="list-group-item">${cat.name}</li>
                    </c:forEach>
                </ul>
            </c:when>
            <c:otherwise>
                <p>No categories available.</p>
            </c:otherwise>
        </c:choose>
    </div>
</body>
</html>
