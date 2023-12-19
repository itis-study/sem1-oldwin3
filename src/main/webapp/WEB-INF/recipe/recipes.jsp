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
<c:choose>
    <c:when test="${userName != null}">
        <nav id="topnav">
            <a class="nav-link" href="/RecipeWebsite/recipe?page=${currentPage}&userName=${userName}&bestrecipe=true">
                Отсортировать по рейтингу</a>
            <a class="nav-link" href="/RecipeWebsite/recipe?page=${currentPage}&userName=${userName}&bestrecipe=false">
                Отсортировать по произвольному пордядку</a>
        </nav>
    </c:when>
    <c:otherwise>
        <nav id="topnav">
            <a class="nav-link" href="/RecipeWebsite/recipe?page=${currentPage}&bestrecipe=true">
                Отсортировать по рейтингу</a>
            <a class="nav-link" href="/RecipeWebsite/recipe?page=${currentPage}&bestrecipe=false">
                Отсортировать по произвольному пордядку</a>
        </nav>
    </c:otherwise>
</c:choose>
<c:if test="${param.userName == null}">
    <c:if test="${currentPage == 1}">
        <c:import url="/WEB-INF/recipe/topRecipes.jsp" />
    </c:if>
    <h2>Остальные рецепты</h2>
</c:if>
<ul>
    <c:forEach items="${recipes}" var="recipe">
        <li>
            <a href="/RecipeWebsite/recipe?name=${recipe.name}">${recipe.name}</a>
            <br />
            Описание рецепта
            <br />
            <c:out value="${recipe.description}"></c:out>
            <br />
            Рейтинг
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
        <a href="/RecipeWebsite/recipe?page=${currentPage - 1}&bestrecipe=${bestrecipe}">Предыдущая</a>
    </c:if>
    <c:forEach var="page" begin="1" end="${totalPageCount}">
        <c:choose>
            <c:when test="${page == currentPage}">
                <span>${page}</span>
            </c:when>
            <c:otherwise>
                <a href="/RecipeWebsite/recipe?page=${page}&bestrecipe=${bestrecipe}">${page}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>
    <c:if test="${currentPage < totalPageCount}">
        <a href="/RecipeWebsite/recipe?page=${currentPage + 1}&bestrecipe=${bestrecipe}">Следующая</a>
    </c:if>
</div>
</body>
</html>