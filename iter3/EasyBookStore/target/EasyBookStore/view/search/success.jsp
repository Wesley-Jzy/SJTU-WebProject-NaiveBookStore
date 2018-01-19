<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wesley
  Date: 17/5/24
  Time: 上午10:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>search</title>
</head>
<body>
<h1 align="center">搜索结果如下</h1>
    <c:forEach var="book" items="${bookList}">
        <div>
            <h3>ISBN:${book.ISBN}</h3>
            <h3>书名:${book.name}</h3>
            <h3>作者:${book.author}</h3>
            <h3>出版社:${book.publishingHouse}</h3>
            <h3>分类:${book.category}</h3>
            <h3>价格:${book.price}</h3>
            <hr>
            <p>#########################################################</p>
            <hr>
        </div>
    </c:forEach>
</body>｀
</html>
<%@ include file="../layouts/footer.jsp"%>
