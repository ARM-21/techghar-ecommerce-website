<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error</title>
  <style>
  
  <style>
.error-message {
    color: white;
    background-color: red;
    padding: 10px;
    border-radius: 5px;
    margin-bottom: 20px;
    font-weight: bold;
    text-align: center;
}

.error-container h1 {
    color: red;
    text-align: center;
}

.error-container p {
    font-size: 16px;
    text-align: center;
}

.error-container a {
    display: block;
    text-align: center;
    color: blue;
    text-decoration: underline;
}

</style>
</head>

<body>
    <div class="error-container">
        <h1>Oops! Something went wrong.</h1>
        <p>${error}</p>
        <a href="/home">Go Back to Home</a>
    </div>
</body>
</html>
