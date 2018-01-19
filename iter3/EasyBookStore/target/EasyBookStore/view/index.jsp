<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="layouts/header.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>EasyBookStore</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">Welcome</div>

                    <div class="panel-body">
                        <h2>欢迎来到EasyBookStore</h2>
                        <h2>这是一个简单的在线书店</h2>
                        <h2>Enjoy it!</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

<%@ include file="layouts/footer.jsp"%>

<%--
<c:forEach items="${users}" var="user">
    <div>
        <tr>
            <td>id:${user.id}</td>
            <td>username:${user.username}</td>
            <td>password:${user.password}</td>
            <td>email:${user.email}</td>
        </tr>
    </div>
</c:forEach>
--%>


