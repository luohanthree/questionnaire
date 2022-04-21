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
  <title>我的</title>
  <link href="bootstrap-5.1.3-dist/css/bootstrap.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <style>
    body {
        background: #6c757d;
    }
  </style>
</head>
<body>
<div class="container-fluid">
  <nav class="nav">
    <div class="row">
      <div class="col-lg-3">
        <a class="navbar-brand" style="color: white"><h3>问卷</h3></a>
      </div>
      <br>
      <ul class="nav" style="float: right; margin: 0; top: 10px">
        <li><a href="index.html" style="color: white">首页</a></li>
        <li><p><%=session.getAttribute("userName")%></p></li>
      </ul>
    </div>
  </nav>
</div>
<div class="row">
  <div class="content">
    <form action="createForm" method="post">
      <p class="start"></p>
    </form>
  </div>
  <div class="addQues">
    <button class="btn btn-primary" id="r adio">单选题</button>
    <button class="btn btn-primary btn-sm" id="checkbox">多选题</button>
    <button class="btn btn-primary btn-sm" id="text">自定义</button>
  </div>
</div>
<script>
  function insertHtml() {
     let newRadio = document.createElement('input')
  }
</script>
</body>
</html>
