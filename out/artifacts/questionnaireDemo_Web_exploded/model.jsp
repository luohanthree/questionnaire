<%@ page import="entity.questionaire.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.questionaire.Option" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhihuan
  Date: 2022/5/8
  Time: 21:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="questionnaire" type="entity.questionaire.Questionnaire" scope="request"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>${questionnaire.formName}</title>
  <link href="./css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<% request.setAttribute("questions",questionnaire.getQuestions());%>
<form action="dataAnA" method="post">
<%--  <jsp:useBean id="questions" type="entity.questionaire.Question" scope="request"/>--%>
<%--  <jsp:useBean id="questions" type="java.util.List" />--%>
  <c:forEach items="${questions}" var="question" varStatus="i">
    <label class="form-label" for="${question.question_name}">${i.count}.${question.question_name}</label>
    <ul class="list-group">
    <c:forEach items="${question.getOptions()}" var="option" varStatus="t">
      <li class="list-group-item">
        <label class="form-label" for="${question.question_name}.${option}">${t.count}.${option}</label>
        <input type="${question.type}" value="${option}" id="${question.question_name}.${option}">
      </li>
    </c:forEach>
    </ul>
  </c:forEach>
  <label>
    <input type="radio" name="formId" value="${questionnaire.formId}" checked disabled>
  </label>
  <label class="form-label" for="${questionnaire.formId}"></label>
  <input type="button" class="btn btn-primary" value="提交" id="${questionnaire.formId}">
</form>
</body>
</html>