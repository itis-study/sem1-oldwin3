<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
    <style>
        /* Общие стили */
        .common-styles {
            font-family: 'Arial', sans-serif;
        }

        /* Стили для формы */
        .form-styles {
            margin: 12px auto;
        }

        /* Стили для кнопки submit */
        .submit-button {
            background-color: #d9534f;
            color: #f7f7f7;
            cursor: pointer;
        }

        .submit-button:hover {
            background-color: #c9302c;
        }
    </style>

</head>
<body>
<form action="LogoutServlet" method="post" class="common-styles form-styles">
    <input type="submit" value="Выйти" class="submit-button">
</form>
</body>
</html>