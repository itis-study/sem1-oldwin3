<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f7f7;
            color: #333;
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

        h3 {
            color: #333;
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
<div style="text-align: center">
    <h2>Это, ${user.name}!</h2>
    Его текущий рейтинг: ${user.rating}
    <br/>
    <img src="data:image/jpeg;base64,${image}" alt=" Картинка" width=" 300"/>
    <br/>
    <h2>Он пишет о себе:</h2> ${user.information}
    <br/>
</div>
</div>
<c:choose>
    <c:when test="${sessionScope.name == user.name}">
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${subscribe == true}">
                Вы подписались на автора
                <form method="post">
                    <input type="hidden" name="action" value="unsubscribe">
                    <input type="submit" value="Отписаться от пользователя">
                </form>
            </c:when>
            <c:otherwise>
                Вы не подписанны на автора
                <form method="post">
                    <input type="hidden" name="action" value="subscribe">
                    <input type="submit" value="Подписаться на пользователя">
                </form>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>
<ul>
<h3>Все его рецепты</h3>
    <c:forEach items="${recipes}" var="recipe">
        <li>
            <a href="/RecipeWebsite/recipe?name=${recipe.name}">${recipe.name}</a>
            <br />
            Описание рецепта
            <br />
            Рейтинг
            <br />
            <c:out value="${recipe.description}"></c:out>
            <br />
            <c:out value="${recipe.rating}"></c:out>
            <br />
            <img src="data:image/jpeg;base64,${recipe.imageForSrc}" alt=" Картинка" width=" 300" />
            <br />
        </li>
    </c:forEach>
</ul>
<div>
    <c:if test="${currentPage > 1}">
        <a href="/RecipeWebsite/authors?name=${user.name}&page=${currentPage - 1}">Предыдущая</a>
    </c:if>
    <c:forEach var="page" begin="1" end="${totalPageCount}">
        <c:choose>
            <c:when test="${page == currentPage}">
                <span>${page}</span>
            </c:when>
            <c:otherwise>
                <a href="/RecipeWebsite/authors?name=${user.name}&page=${page}">${page}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage < totalPageCount}">
        <a href="/RecipeWebsite/authors?name=${user.name}&page=${currentPage + 1}">Следующая</a>
    </c:if>
</div>
</body>
</html>
