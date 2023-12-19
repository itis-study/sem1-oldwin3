<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Создать свой рецепт</title>
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
    <label for="name" class="form-label">Введите название рецепта:</label>
    <input type="text" id="name" name="name" class="form-input" required>
    <br>
    <label for="description" class="form-label">Введите краткое описание блюда:</label>
    <input type="text" id="description" name="description" class="form-input" required>
    <br>
    <label for="ingredients" class="form-label">Укажите ингредиенты:</label>
    <div id="ingredientsContainer">
        <input type="text" id="ingredient1" name="ingredients" class="form-input" required>
    </div>
    <button type="button" onclick="addIngredient()" class="form-submit-btn">Добавить ингредиент</button>
    <br>
    <br>
    <label for="stages" class="form-label">Введите описание рецепта:</label>
    <input type="text" id="stages" name="stages" class="form-input" required>
    <br>
    <label for="image" class="form-label">Добавьте фото рецепта:</label>
    <input type="file" id="image" name="image" accept="image/*" class="form-input" required>
    <br>
    <input type="submit" value="Сохранить" class="form-submit-btn">
</form>
<script>
    function addIngredient() {
        var container = document.getElementById('ingredientsContainer');
        var input = document.createElement('input');
        input.type = 'text';
        input.name = 'ingredients';
        input.required = true;
        input.className='form-input'
        container.appendChild(document.createElement('br'));
        container.appendChild(input);
    }
</script>

</body>
</html>
