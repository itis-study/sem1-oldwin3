<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Рецепты.ру</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
        }

        #topnav {
            background-color: #333;
            overflow: hidden;
        }

        #topnav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        #topnav a:hover {
            background-color: #ddd;
            color: black;
        }

        #topnav a.active {
            background-color: #4CAF50;
            color: white;
        }

        #topnav a.nav-link {
            transition: background-color 0.3s, color 0.3s;
        }
    </style>
</head>
<html>
<body>
<c:choose>
    <c:when test="${sessionScope.name == null}">
        <nav id="topnav">
            <a class="nav-link" href="/RecipeWebsite">Главная</a>
            <a class="nav-link" href="/RecipeWebsite/News">Новости</a>
            <a class="nav-link" href="/RecipeWebsite/registration">Зарегистреироваться</a>
            <a class="nav-link" href="/RecipeWebsite/login">Войти в систему</a>
        </nav>
    </c:when>
    <c:when test="${sessionScope.role == \"ADMIN\"}">
        <nav id="topnav">
            <a class="nav-link" href="/RecipeWebsite">Главная</a>
            <a class="nav-link" href="/RecipeWebsite/News">Новости</a>
            <a class="nav-link" href="/RecipeWebsite/recipe?page=1">Рецепты</a>
            <a class="nav-link" href="/RecipeWebsite/authors">Авторы</a>
            <a class="nav-link" href="/RecipeWebsite/community">Сообщества</a>
            <a class="nav-link" href="/RecipeWebsite/adminPanel">Админ Панель</a>
            <a class="nav-link" href="/RecipeWebsite/Profile">Вы вошли под именем: ${sessionScope.name}</a>
            <c:import url="/WEB-INF/RegAndLog/Logout.jsp"></c:import>
        </nav>
    </c:when>
    <c:otherwise>
        <nav id="topnav">
            <a class="nav-link" href="/RecipeWebsite">Главная</a>
            <a class="nav-link" href="/RecipeWebsite/News">Новости</a>
            <a class="nav-link" href="/RecipeWebsite/recipe?page=1">Рецепты</a>
            <a class="nav-link" href="/RecipeWebsite/authors">Авторы</a>
            <a class="nav-link" href="/RecipeWebsite/community">Сообщества</a>
            <a class="nav-link" href="/RecipeWebsite/Profile">Вы вошли под именем: ${sessionScope.name}</a>
            <a class="nav-link"><c:import url="/WEB-INF/RegAndLog/Logout.jsp"></c:import></a>
        </nav>
    </c:otherwise>
</c:choose>
</body>
</html>
