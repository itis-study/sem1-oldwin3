<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="${pageContext.request.contextPath}/css/rating-result.css" rel="stylesheet" type="text/css">
    <style>
        .rating-area {
            overflow: hidden;
            width: 265px;
            margin: 0 auto;
        }
        .rating-area:not(:checked) > input {
            display: none;
        }
        .rating-area:not(:checked) > label {
            float: unset;
            width: 52px;
            padding: 0;
            cursor: pointer;
            font-size: 32px;
            line-height: 32px;
            color: lightgrey;
            text-shadow: 1px 1px #bbb;
        }
        .rating-area:not(:checked) > label:before {
            content: '★';
        }
        .rating-area > input:checked ~ label {
            color: gold;
            text-shadow: 1px 1px #c60;
        }
        .rating-area:not(:checked) > label:hover,
        .rating-area:not(:checked) > label:hover ~ label {
            color: gold;
        }
        .rating-area > input:checked + label:hover,
        .rating-area > input:checked + label:hover ~ label,
        .rating-area > input:checked ~ label:hover,
        .rating-area > input:checked ~ label:hover ~ label,
        .rating-area > label:hover ~ input:checked ~ label {
            color: gold;
            text-shadow: 1px 1px goldenrod;
        }
        .rate-area > label:active {
            position: relative;
        }
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
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

    </style>
</head>
<body>
<c:import url="../Panels/MainPanel.jsp"></c:import>
<div style="text-align: center;">
<c:if test="${editingRights == true}">
    <nav id="topnav">
        <a class="nav-link" href="/RecipeWebsite/UpdateRecipes?name=${recipe.name}&ownerName=${sessionScope.name}">Обновить рецепт</a>
        <a class="nav-link" href="/RecipeWebsite/DeleteRecipe?name=${recipe.name}&ownerName=${sessionScope.name}">Удалить рецепт</a>
    </nav>
    <br/>
</c:if>
<h3>Название Рецепта</h3>
<br/>
${recipe.name}
<br/>
<br/>
Описание Рецепта
<br/>
${recipe.description}
<br/>
<img src="data:image/jpeg;base64,${recipe.imageForSrc}" alt=" Картинка" width=" 300"/>
<br/>
<h3>Ингридиенты</h3>
${recipe.ingredients}
<h3>Сам рецепт</h3>
<br/>
${recipe.stages}
<br/>

<h3>Средний рейтинг:</h3>
<div class="rating-result">
    <c:set var="rating" value="${requestScope.rating}" />
    <c:forEach var="i" begin="1" end="5">
        <c:choose>
            <c:when test="${i <= rating}">
                <span class="active"></span>
            </c:when>
            <c:otherwise>
                <span></span>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</div>
<br/>
Рейтинг:
${requestScope.absRating}
<br/>
Колвичество Оценок:
${requestScope.countRating}
<br/>
<br/>
<h3>${requestScope.voteText}</h3>
<br/>

<form method="post">
    <div style="text-align: center" class="rating-area">
        <input type="radio" id="star-5" name="rating" value="5">
        <label for="star-5" title="Оценка «5»"></label>
        <input type="radio" id="star-4" name="rating" value="4">
        <label for="star-4" title="Оценка «4»"></label>
        <input type="radio" id="star-3" name="rating" value="3">
        <label for="star-3" title="Оценка «3»"></label>
        <input type="radio" id="star-2" name="rating" value="2">
        <label for="star-2" title="Оценка «2»"></label>
        <input type="radio" id="star-1" name="rating" value="1">
        <label for="star-1" title="Оценка «1»"></label>
    </div>
    <input type="submit" value="Отправить Отзыв">
</form>
<br/>
</div>
<c:import url="../Comment/CreateComment.jsp"></c:import>
<%--<c:import url="/comment"></c:import>--%>
<%--проблема была с get запросом и то что приходилось делать 2 запроса по отедльности при 1 запросе пользователя
один для comments другой для
recipe--%>
<div style="text-align: left">
<ul>
    <h3>Комментарии</h3>
    <c:forEach items="${comments}" var="comments">
        <li>
            <c:out value="${comments.creatorName}"></c:out>:
            <c:out value="${comments.text}"></c:out>
            <br />
        </li>
    </c:forEach>
</ul>
</div>
</body>
</html>
