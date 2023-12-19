<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
</head>
<body>
Комментарии
<ul>
  <c:forEach items="${comments}" var="comments">
    <li>
      <c:out value="${comments.text}"></c:out>
      <br />
    </li>
  </c:forEach>
</ul>

</body>
</html>
