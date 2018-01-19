<%--
  简易注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>register</title>
</head>
<body>
    <div>
        <h1>注册</h1>
        <form method="post" action="register">
            <input type="text" required="required" placeholder="用户名" name="username">
            <input type="password" required="required" placeholder="密码" name="password">
            <button type="submit">注册</button>

            <div class="bottom-link">
                <a href="login">已经有账号了，登录</a>
            </div>
        </form>
    </div>
</body>
</html>

<%@ include file="../layouts/footer.jsp"%>
