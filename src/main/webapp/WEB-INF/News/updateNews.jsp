<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
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
<c:import url="../Panels/MainPanel.jsp"></c:import>
<c:import url="../Panels/importAdminPAnel.jsp"></c:import>
<form method="post" enctype="multipart/form-data" class="update-profile-form">
    <label for="news" class="form-label">Выберите новость:</label>
    <select id="news" name="news" class="form-input">
        <c:forEach items="${news}" var="news">
            <option value="${news.name}">${news.name}</option>
        </c:forEach>
    </select>
    <br>
    <label for="newName" class="form-label">Наиминование:</label>
    <input type="text" id="newName" name="newName" class="form-input">
    <br>
    <label for="text" class="form-label">Описание:</label>
    <input type="text" id="text" name="text" class="form-input">
    <br>
    <label for="image" class="form-label">Image:</label>
    <input type="file" id="image" name="image" accept="image/*" class="form-input">
    <br>
    <input type="submit" value="Обновить Рецепт" class="form-submit-btn">
</form>
</body>
</html>
