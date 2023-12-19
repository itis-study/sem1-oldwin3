<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Cp1251"%>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1251">
<html>
<head>
    <title>Вход</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }

        form {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        #email.invalid {
            border: 1px solid red;
        }

        #email.invalid::placeholder {
            color: red;
        }
    </style>
</head>
<body>
<c:import url="../Panels/MainPanel.jsp"></c:import>
<form method="post" accept-charset="UTF-8">
    <label for="name">Введите ваше имя:</label>
    <input type="text" id="name" name="name" required>
    <label for="password">Введите ваш пароль:</label>
    <input type="password" id="password" name="password" required>
    <br>
    <br>
    <input type="submit" value="Сохранить">
</form>
</body>
</html>
