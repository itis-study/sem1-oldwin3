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
            max-width: 150px;
            margin: 0 auto;

            list-style-type: none;
            padding: 0;
        }

        li {
            max-width: 150px;
            margin: 0 auto;

            margin-bottom: 20px;
            background-color: #fff;
            padding: 15px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h3 {
            color: #333;
        }

        form {
            display: block;
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
</head>
<body>
<c:import url="../Panels/MainPanel.jsp"></c:import>
<div style="text-align: center">
<h3>Название</h3>
<br/>
${community.name}
<br/>
<h3>Описание</h3>
<br/>
<c:out value="${community.description}"></c:out>
<br/>
<img src="data:image/jpeg;base64,${image}" alt=" Картинка" width=" 300"/>
<br/>

<c:choose>
    <c:when test="${isCreator == true}">
    </c:when>
    <c:otherwise>
        <c:choose>
            <c:when test="${join == true}">
                <br>
                Вы состоите в этой группе
                <br>
                <br>
                <form method="post">
                    <input type="hidden" name="action" value="leave">
                    <input type="submit" value="Я хочу покинуть это комьюнити">
                </form>
            </c:when>
            <c:otherwise>
                <br>
                Вы не состоите в этой группе
                <br>
                <br>
                <form method="post">
                    <input type="hidden" name="action" value="join">
                    <input type="submit" value="Я хочу присодеиниться к этому комьюинит">
                </form>
            </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>

<h3>Участники комьюнити:</h3>
<ul>
    <c:forEach items="${users}" var="user">
        <li>
            <a href="/RecipeWebsite/authors?name=${user.name}&page=1">${user.name}</a>
        </li>
    </c:forEach>
</ul>
</div>
</body>
</html>