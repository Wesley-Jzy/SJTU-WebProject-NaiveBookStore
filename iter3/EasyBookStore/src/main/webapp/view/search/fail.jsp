<%--
  Created by IntelliJ IDEA.
  User: wesley
  Date: 17/5/24
  Time: 上午10:38
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>search</title>
</head>
<body>
<div align="center">
    <h1>抱歉，没有搜索到有关 <u><em>${query}</em></u> 的书籍</h1>
    <input value="返回之前页面" type="button" onclick="location.href='javascript:history.go(-1);'"/>
    <input value="返回首页" type="button" onclick="location.href='<%=ctx%>/index'" />
</div>
</body>｀
</html>
<%@ include file="../layouts/footer.jsp"%>
