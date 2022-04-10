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
    <form action="" method="post" class="basic-grey">  <%--注意form title中的class就是下文提到的几种样式的名称，引入的时候要自己替换过来 --%>
        <h1>注册
            <span>填写以下内容完成注册</span>
        </h1>
        <label>
            <span>用户名 :</span>
            <input id="name" type="text" name="name" placeholder="Your Full Name" />
        </label>
        <label>
            <span>你的邮箱 :</span>
            <input id="email" type="email" name="email" placeholder="Valid Email Address" />
        </label>
        <label>
            <span>密码 :</span>
            <input id="pwd" type="password" name="pwd" placeholder="Secure password" />
        </label>
        <label>
            <span>&#10;</span>
            <input type="button" class="button" value="Send" />
        </label>
    </form>
</body>
</html>
