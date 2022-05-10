<%@ page import="entity.Results" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="entity.questionaire.Question" %>
<%@ page import="entity.questionaire.Questionnaire" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhihuan
  Date: 2022/5/5
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>数据分析</title>
  <link href="../css/bootstrap.min.css" rel="stylesheet" />
</head>
<body>
<%
  List<Results> resultsList = (List<Results>) request.getAttribute("results");
  Map<String,List<String>> QandA = new HashMap<>();
  for (Results results : resultsList) {
      QandA.put(results.getQuestion_name(), results.getAnswers());
  }
  Questionnaire questionnaire = (Questionnaire) request.getAttribute("questionnaire");
  List<Question> questions = questionnaire.getQuestions();

%>
<div class="container">
  <div class="mb-4">
    <jsp:useBean id="resluts" scope="request" type="java.util.List"/>
    <c:forEach items="${resluts}" varStatus="i">

    </c:forEach>
  </div>
</div>
</body>
</html>
