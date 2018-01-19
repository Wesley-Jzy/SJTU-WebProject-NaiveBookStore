<%--
  简易登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>login</title>
</head>
<body>
    <div>
        <h1>登录</h1>
        <form method="post" action="login.do">
            <input type="text" required="required" placeholder="用户名" name="username">
            <input type="password" required="required" placeholder="密码" name="password">
            <button type="submit">登录</button>

            <div class="bottom-link">
                <a href="register">注册</a>
            </div>
        </form>
    </div>
</body>
</html>

<%@ include file="../layouts/footer.jsp"%>
