<%--
  admin的管理主页
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <title>管理员主页</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-10 col-md-offset-1">
            <div class="panel panel-default">
                <div class="panel-heading">管理界面</div>

                <div class="panel-body">
                    <p>管理员登陆成功！</p>
                </div>
                <div align="center">
                    <a class="lsit-group-item" href="user">
                        <h3>用户管理</h3>
                    </a>

                    <a class="lsit-group-item" href="book">
                        <h3>书籍管理</h3>
                    </a>

                    <a class="lsit-group-item" href="order">
                        <h3>订单管理</h3>
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<%@ include file="../layouts/footer.jsp"%>
