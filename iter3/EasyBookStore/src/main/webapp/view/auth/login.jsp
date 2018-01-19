<%--
  简易登录页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>登录</title>
</head>
<body>
<div class="container">
    <div class="form row">
        <form id="login-form" class="form-horizontal col-sm-offset-4 col-md-offset-4" method="post" action="login.do">
            <h3 class="form-title">登录</h3>
            <div class="col-sm-6 col-md-6">
                <div class="form-group">
                    <div>
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control" type="text" placeholder="用户名" id="username" name="username"
                               onfocus="clearMsg()"
                               data-rule-required="true" data-rule-maxlength="20"
                               data-msg-required="用户名不能为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control" type="password" placeholder="密码" id="password" name="password"
                               onfocus="clearMsg()"
                               data-rule-required="true" data-rule-minlength="4" data-rule-maxlength="20"
                               data-msg-required="密码不能为空"/>
                    </div>
                    <div align="left">
                        <label id="login-msg" for="password" class="control-label">${msg}</label>
                    </div>
                </div>
                <div class="form-group" align="center">
                    <hr />
                    <div>
                        <button type="submit" class="btn btn-primary">登录</button>
                    </div>
                    <hr />
                    <div>
                        <a href="register">没有账号，马上注册</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="../layouts/footer.jsp"%>

<script type="text/javascript">
    $(function() {
        var form = $("#login-form");
        form.validate();
    });

    function clearMsg() {
        $("#login-msg").html("");
    }
</script>

</body>
</html>
