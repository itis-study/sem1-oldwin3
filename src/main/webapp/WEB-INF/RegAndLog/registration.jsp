<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="Cp1251"%>
<html>
<head>
    <title>–егистраиц€</title>
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

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const emailInput = document.getElementById('email');
        const form = document.querySelector('form');

        form.addEventListener('submit', function(event) {
            const emailValue = emailInput.value;
            const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

            if (!emailPattern.test(emailValue)) {
                alert('ѕожалуйста, введите корректный email.');
                event.preventDefault();
            }
        });
    });
</script>

<c:import url="../Panels/MainPanel.jsp"></c:import>
<form method="post">
    <label for="name">¬ведите ваше им€:</label>
    <input type="text" id="name" name="name" required>
    <label for="password">¬ведите ваш пароль:</label>
    <input type="password" id="password" name="password" required>
    <label for="password">¬ведите вашу почту:</label>
    <input type="email" id="email" name="email" required>
    <br>
    <br>
    <input type="submit" value="—охранить">
</form>
</body>
</html>
