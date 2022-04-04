<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <link href="css/login.css" rel="stylesheet">
    </head>
        <div class="loginBox">
            <h1 id="loginTitle">登录
                <div class="loginForm">
                    <form action="doLogin.do" method="post">
                        <label>
                            <input type="text" name="userName" placeholder="用户名">
                        </label>
                        <label>
                            <input type="password" name="pwd" placeholder="密码">
                        </label>
                    </form>
                </div>
            </h1>
        </div>
    </body>
</html>