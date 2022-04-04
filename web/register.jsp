<%--
  Created by IntelliJ IDEA.
  User: zhihuan
  Date: 2022/4/4
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <link rel="stylesheet" href="css/register.css">
</head>
<body>
    <div class="registerBox">
        <h1>注册</h1>
        <div class="registerForm">
            <form action="doLogin.do" method="post">
                <label>
                    <input type="text" name="userName" placeholder="用户名">
                </label>
                <label>
                    <input type="password" name="pwd" placeholder="密码">
                </label>
            </form>
        </div>
    </div>
</body>
</html>
