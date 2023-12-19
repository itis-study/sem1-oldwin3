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

        h2 {
            color: #333;
        }

        nav {
            background-color: #333;
            overflow: hidden;
        }

        nav a {
            float: left;
            display: block;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        nav a:hover {
            background-color: #ddd;
            color: black;
        }

        nav a.active {
            background-color: #4CAF50;
            color: white;
        }

        nav a.nav-link {
            transition: background-color 0.3s, color 0.3s;
        }

        ul {
            list-style-type: none;
            padding: 0;
        }

        li {
            margin-bottom: 20px;
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        img {
            max-width: 100%;
            height: auto;
            border-radius: 8px;
            margin-top: 10px;
        }

        a {
            color: #428bca;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<c:import url="/WEB-INF/Panels/MainPanel.jsp"></c:import>
<nav id="topnav">
    <a class="nav-link" href="/RecipeWebsite/UpdateProfile">Обновить профиль</a>
    <a class="nav-link" href="/RecipeWebsite/CreateRecipe">Создать рецепт</a>
    <a class="nav-link" href="/RecipeWebsite/UpdateRecipes">Обновить Свои рецепт</a>
    <a class="nav-link" href="/RecipeWebsite/DeleteRecipe">Удалить Свой рецепт</a>
    <a class="nav-link" href="/RecipeWebsite/recipe?page=1&userName=${user.name}">Посмтореть все свои рецепты</a>
    <a class="nav-link" href="/RecipeWebsite/createCommunity">Создать группу</a>
    <a class="nav-link" href="/RecipeWebsite/updateCommunity">Обновить группу</a>
    <a class="nav-link" href="/RecipeWebsite/deleteCommunity">Удалить группу</a>
    <c:choose>
        <c:when test="${community == null}"></c:when>
        <c:otherwise><a class="nav-link" href="/RecipeWebsite/community?userName=${sessionScope.name}">
            Комьюнити</a></c:otherwise>
    </c:choose>
</nav>
<div style="text-align: center">
<h2>Добро пожаловать, ${user.name}!</h2>
Ваш текущий рейтинг: ${user.rating}
<br/>
<img src="data:image/jpeg;base64,${image}" alt=" Картинка" width=" 300"/>
<br/>
<h2>Информация о вас:</h2> ${user.information}
<br/>
</div>
<c:choose>
    <c:when test="${communities == null}"> Вы не состоите ни в каком коьюнити </c:when>
    <c:otherwise><h2>На данный момент вы состоите в:</h2>
        <ul>
            <c:forEach items="${communities}" var="communities">
                <li>
                    <a href="/RecipeWebsite/community?name=${communities.name}">${communities.name}</a>
                    <br />
                    <c:out value="${communities.description}"></c:out>
                    <br />
                    <img src="data:image/jpeg;base64,${communities.imageForSrc}" alt=" Картинка" width=" 300"/>
                    <br />
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<br/>
<c:choose>
    <c:when test="${users == null}"> <h3>На данный моменты вы не на кого не подписанны,
        рекомендуем перейти на страницу авторов и найти своих любимчиков!
        <a href="/RecipeWebsite/authors">Пора подписаться</a></h3>
    </c:when>
    <c:otherwise>
        Ваши подписки
        <ul>
            <c:forEach items="${users}" var="user">
                <li>
                    <a href="/RecipeWebsite/authors?name=${user.name}">${user.name}</a>
                    <br />
                    <c:out value="${user.rating}"></c:out>
                    <br />
                    Информация о авторе: ${user.information}
                    <br />
                    <img src="data:image/jpeg;base64,${user.imageForSrc}" alt=" Картинка" width=" 300"/>
                    <br />
                </li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
</body>
</html>
