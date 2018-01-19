<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored ="false" %>

<%@ include file="layouts/header.jsp" %>

<div class="container">
<div class="row">
    <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
            <div class="panel-heading">出错了～</div>
            <div class="panel-body">
                ${msg}
            </div>
        </div>
    </div>
</div>
</div>

<%@ include file="layouts/footer.jsp" %>
