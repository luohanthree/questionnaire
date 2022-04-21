<%--
  Created by IntelliJ IDEA.
  User: zhihuan
  Date: 2022/4/20
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>
    <%String name = (String) session.getAttribute("userName");%>
    <%=name%>
  </title>
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
  <div class="container">
    <h1 class="header"><%=name%></h1>
  </div>
<div class="page-sidebar"></div>
</body>
</html>
