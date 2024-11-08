<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="/css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="user/login" id="form">
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${error}${login_msg}${register_msg}</div>
        <p>用户名:<input id="username" name="username" value="${cookie.username.value}" type="text"></p>
        <p>密码:<input id="password" name="password" value="${cookie.password.value}" type="password"></p>
        <p>记住我:<input id="remember" name="remember" value="1" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="登陆">
            <input type="reset" class="button" value="取消">&nbsp;&nbsp;&nbsp;
            <a href="../../register.jsp">没有账号？</a>
        </div>
    </form>
</div>

</body>
</html>
