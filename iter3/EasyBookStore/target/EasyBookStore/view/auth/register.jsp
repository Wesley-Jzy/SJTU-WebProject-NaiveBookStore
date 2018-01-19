<%--
  简易注册页面
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<html>
<head>
    <title>注册</title>
</head>
<body>
<div class="container">
    <div class="form row">
        <form id="register-form" class="form-horizontal col-sm-offset-4 col-md-offset-4">
            <h3 class="form-title">注册</h3>
            <div class="col-sm-6 col-md-6">
                <div class="form-group">
                    <div>
                        <i class="fa fa-user fa-lg"></i>
                        <input class="form-control" type="text" placeholder="用户名" id="username" name="username"
                               data-rule-required="true" data-rule-maxlength="20"
                               data-msg-required="用户名不能为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control" type="password" placeholder="密码" id="password" name="password"
                               data-rule-required="true" data-rule-minlength="4" data-rule-maxlength="20"
                               data-msg-required="密码不能为空"/>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control" type="password" placeholder="重复密码" id="confirm-password"
                               name="confirm-password"
                               data-rule-required="true" data-rule-equalto="#password"
                               data-msg-required="请确认密码" data-msg-equalto="两次输入密码不同"/>
                    </div>
                </div>
                <div class="form-group">
                    <div>
                        <i class="fa fa-lock fa-lg"></i>
                        <input class="form-control" type="email" placeholder="email" id="email"
                               name="email"
                               data-rule-required="true" data-rule-email="true"
                               data-msg-required="请输入您的邮箱" data-msg-email="邮箱形式不合法"/>
                    </div>
                </div>
                <div class="form-group" align="center">
                    <hr />
                    <div>
                        <button id="register-btn" type="button" class="btn btn-primary">注册</button>
                    </div>
                    <hr />
                    <div>
                        <a href="login">已经有账号了，登录</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

<%@ include file="../layouts/footer.jsp"%>

<script type="text/javascript">
    $(function() {
        var form = $("#register-form");
        form.validate({
            submitHandler: function() {
                $("#confirm-password").attr("disabled", true);
                $.ajax({
                    url: 'register',
                    type: 'POST',
                    data: $("#register-form").serializeJSONObject(),
                    contentType: 'application/json',
                    dataType: 'json',
                    success: function (result) {
                        alert(result.msg);
                    }
                });
                $("#confirm-password").attr("disabled", false);
            }
        });
    });

    $.fn.serializeJSONObject = function()
    {
        var obj = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (obj[this.name]) {
                if (!obj[this.name].push) {
                    obj[this.name] = [obj[this.name]];
                }
                obj[this.name].push(this.value || '');
            } else {
                obj[this.name] = this.value || '';
            }
        });
        return JSON.stringify(obj);
    };

    $("#register-btn").click(function() {
        $("#register-form").submit();
    });
</script>

</body>
</html>
