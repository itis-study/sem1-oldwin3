<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Top 3 Recipes</title>
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
<div align="center">
<h2>Топ 3 Рецептов</h2>
<ul>
<c:forEach var="bestRecipe" items="${bestRecipe}">
  <li>
    <h3><a href="/RecipeWebsite/recipe?name=${bestRecipe.name}">${bestRecipe.name}</a></h3>
    <p>Описание: ${bestRecipe.description}</p>
    <p>Рейтинг: ${bestRecipe.rating}</p>
    <p>Количесвто Голосов: ${bestRecipe.ratingCount}</p>
    <img src="data:image/jpeg;base64,${bestRecipe.imageForSrc}" alt=" Картинка" width=" 300" />
  </li>
</c:forEach>
</div>
</ul>
</body>
</html>

