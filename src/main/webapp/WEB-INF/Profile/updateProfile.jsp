<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateProfile Page</title>
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
<form method="post" enctype="multipart/form-data" class="update-profile-form">
    <label for="name" class="form-label">Наименование:</label>
    <input type="text" id="name" name="name" class="form-input">
    <br>
    <label for="email" class="form-label">Почта:</label>
    <input type="text" id="email" name="email" class="form-input">
    <br>
    <label for="information" class="form-label">Информация о вас:</label>
    <input type="text" id="information" name="information" class="form-input">
    <br>
    <label for="password" class="form-label">Пароль:</label>
    <input type="text" id="password" name="password" class="form-input">
    <br>
    <label for="image" class="form-label">Изображение:</label>
    <input type="file" id="image" name="image" accept="image/*" class="form-input">
    <br>
    <input type="submit" value="Обновить Профиль" class="form-submit-btn">
</form>
<br>
<a style="text-align: center" href="/RecipeWebsite/deleteProfile" class="delete-account-link">Нажмите, чтобы удалить аккаунт</a>
</body>
</html>