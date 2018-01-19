<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ page isELIgnored ="false" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<% String ctx = request.getContextPath(); %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>EasyBookStore</title>

    <!-- Styles -->
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/resources/js/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="<%=ctx%>/resources/js/themes/icon.css">
    <link href="<%=ctx%>/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=ctx%>/resources/css/style.css" rel="stylesheet">
    <script src="<%=ctx%>/resources/js/jquery.min.js"></script>
</head>
<body id="app-layout">
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <!-- Collapsed Hamburger -->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#app-navbar-collapse">
                    <span class="sr-only">Toggle Navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                 <!-- Branding Image -->
                 <a class="navbar-brand" href="<%=ctx%>/index">
                     EasyBookstore
                 </a>
            </div>

            <div class="collapse navbar-collapse" id="app-navbar-collapse">
                <!-- Left Side Of Navbar -->
                <form class="navbar-form navbar-left" role="search" action="<%=ctx%>/index" method="GET">
                    <div class="input-group">
                        <input name="q" type="text" class="form-control" placeholder="Search">
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
                        </span>
                    </div>
                </form>

                <!-- Right Side Of Navbar -->
                <ul class="nav navbar-nav navbar-right">
                    <sec:authorize access="isAnonymous()">
                        <li><a href="<%=ctx%>/auth/register">注册</a></li>
                        <li><a href="<%=ctx%>/auth/login">登录</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_USER')">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle avatar-container" data-toggle="dropdown" role="button" aria-expanded="false">
                                <sec:authentication property="principal.username" var="username"/>
                                ${username}
                            </a>

                            <ul class="dropdown-menu" role="menu">
                                <li><a href="<%=ctx%>/test">用户主页</a></li>
                                <li><a href="<%=ctx%>/logout">登出</a></li>

                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <li role="separator" class="divider"></li>
                                    <li><a href="<%=ctx%>/admin/home">后台管理</a></li>
                                </sec:authorize>
                            </ul>
                        </li>
                    </sec:authorize>
                </ul>
            </div>
        </div>
    </nav>
