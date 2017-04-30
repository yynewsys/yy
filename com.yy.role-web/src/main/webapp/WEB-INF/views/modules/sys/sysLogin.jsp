<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>登录</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">
    <meta http-equiv="X-UA-Compatible" content="IE=8,IE=9,IE=10" />
    <link href="${ctxStatic}/css/login.css" rel="stylesheet">
    <script src="${ctxStatic}/jquery/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctxStatic}/pub/cookie.js" type="text/javascript"></script>
</head>
<body>
<form action="${ctx}/login" method="post" id="loginForm"
      onsubmit="return doLogin();" >
    <div class="login-main login-left">

    </div>
    <div class="login-main login-right">
        <div class="login">
            <input type="text" name="username" placeholder="请输入您的账号"/>
            <input class="password" type="password" name="password" placeholder="请输入您的六位数密码"/>
            <div  class="top" <c:if test="${message == null || message eq ''}">style="color:red;display: none;"</c:if><c:if test="${message != null && message!= ''}">style="clear:both;color: red;"</c:if>>${message}</div>
            <button class="submitBtn" type="submit"></button>
      </div>
    </div>
</form>
</body>
</html>