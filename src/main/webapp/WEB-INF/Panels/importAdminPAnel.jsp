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
<body>
<nav id="topnav">
  <a class="nav-link" href="/RecipeWebsite/createNews">Создать новость</a>
  <a class="nav-link" href="/RecipeWebsite/updateNews">Изменить новость</a>
  <a class="nav-link" href="/RecipeWebsite/deleteNews">Удалить новость</a>
  <a class="nav-link" href="/RecipeWebsite/deleteComments">Удалить комменты</a>
</nav>
</body>
</html>
