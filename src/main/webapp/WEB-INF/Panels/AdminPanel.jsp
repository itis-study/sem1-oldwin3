<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
<c:import url="MainPanel.jsp"></c:import>
<nav id="topnav">
    <a class="nav-link" href="/RecipeWebsite/createNews">Создать новость</a>
    <a class="nav-link" href="/RecipeWebsite/updateNews">Изменить новость</a>
    <a class="nav-link" href="/RecipeWebsite/deleteNews">Удалить новость</a>
    <a class="nav-link" href="/RecipeWebsite/deleteComments">Удалить комменты</a>
</nav>
</body>
</html>
