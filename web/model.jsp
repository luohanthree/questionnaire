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
<html lang="zh">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
  <title>${questionnaire.formName}</title>
</head>
<body>
<% request.setAttribute("questions",questionnaire.getQuestions());%>
<div class="container">
  <div class="mb-4">
    <form action="answer" method="post">
      <h4 style="text-align: center">${questionnaire.formName}</h4>
      <c:forEach items="${questions}" var="question" varStatus="i">
        <h5><label class="form-label" for="${question.question_name}">${i.count}.${question.question_name}</label></h5>
        <div class="row">
          <ul class="list-group">
            <c:forEach items="${question.getOptions()}" var="option" varStatus="t">
              <li class="list-group-item">
                <c:if test="${question.type eq 'text'}">
                  <textarea class="form-control" rows="3" name="${question.question_name}" id="${question.question_name}.${option}"></textarea>
                </c:if>
                <c:if test="${!(question.type eq 'text')}">
                  <label class="form-label" for="${question.question_name}.${option}">${t.count}.${option}</label>
                  <input type="${question.type}" value="${option}" name="${question.question_name}" id="${question.question_name}.${option}" />
                </c:if>
              </li>
            </c:forEach>
          </ul>
        </div>
      </c:forEach>
      <br>
      <footer class="footer">
        <label class="form-label" for="${questionnaire.formId}"></label>
        <input type="reset" class="btn btn-secondary" value="重置" />
        <input type="submit" class="btn btn-primary" name="formId" value="提交" id="${questionnaire.formId}">
      </footer>
    </form>
  </div>
</div>
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.min.js"></script>
</body>
</html>