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
<body>
<c:import url="../Panels/MainPanel.jsp"></c:import>
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
</body>
</html>
