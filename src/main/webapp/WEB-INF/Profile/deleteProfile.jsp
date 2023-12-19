<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
            color: #333;
        }

        .update-profile-form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .form-label {
            display: block;
            margin-bottom: 8px;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-submit-btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .form-submit-btn:hover {
            background-color: #45a049;
        }

        .delete-account-link {
            display: block;
            margin-top: 10px;
            color: #428bca;
            text-decoration: none;
        }

        .delete-account-link:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:import url="/WEB-INF/Panels/MainPanel.jsp"></c:import>
<div style="text-align: center"> <h1>Вы уверены что хотите удалить аккаунт</h1>
<form method="post">
    <input type="submit" value="Да">
</form>
<a href="/RecipeWebsite">Нет</a>
</div>
</body>
</html>
