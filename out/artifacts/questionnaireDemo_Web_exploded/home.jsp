<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link href="./layui/css/layui.css" rel="stylesheet">
    <link href="./css/bootstrap.css" rel="stylesheet">
    <title>home</title>
    <style>
        a{
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo layui-hide-xs layui-bg-black">问卷</div>
        <!-- 头部区域（可配合layui 已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item layui-hide-xs"><a href="index.html">首页</a></li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-hide layui-show-md-inline-block">
                <a href="javascript:;">
                    <img src="#" class="#" alt="">
                </a>
            </li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="#">查看全部问卷</a>
                </li>
                <li class="layui-nav-item">
                    <a href="createNew.html">创建新的问卷</a>
                </li>
                <li class="layui-nav-item"><a href="index.html">退出登录</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <div style="padding: 15px;">
            <table class="table table-striped table-hover">
                <thead class="">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">问卷Id</th>
                    <th scope="col">问卷名称</th>
                    <th scope="col">填写人数</th>
                    <th scope="col">查看问卷</th>
                    <th scope="col">处理</th>
                </tr>
                </thead>
                <tbody id="tableBody">
                <jsp:useBean id="questionnaires" scope="request" type="java.util.List"/>
                <jsp:useBean id="results" scope="request" type="java.util.Map"/>
                <c:forEach items="${questionnaires}" var="questionnaire" varStatus="i">
                    <tr>
                        <td>${i.count}</td>
                        <td>${questionnaire.getFormId()}</td>
                        <td>${questionnaire.getFormName()}</td>
                        <td>${results.get(questionnaire.getFormId()).nums}</td>
                        <td><a href="http://localhost:8080/questionnaire/questionnaires/${questionnaire.getFormId()}">查看详情</a>&nbsp;</td>
                        <td><a href="http://localhost:8080/questionnaire/sendQues/${questionnaire.getFormId()}">派发</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        底部固定区域
    </div>
</div>
<script src="./layui/layui.js"></script>
<script src="./js/jquery.js"></script>
<script src="./js/bootstrap.min.js"></script>
<script src="./js/home.js"></script>
<script>
    //JS
    layui.use(['element', 'layer', 'util'], function(){
        let element = layui.element
            ,layer = layui.layer
            ,util = layui.util
            ,$ = layui.$;

        //头部事件
        util.event('lay-header-event', {
            //左侧菜单事件
            menuLeft: function(othis){
                layer.msg('展开左侧菜单的操作', {icon: 0});
            }
            ,menuRight: function(){
                layer.open({
                    type: 1
                    ,content: '<div style="padding: 15px;">处理右侧面板的操作</div>'
                    ,area: ['260px', '100%']
                    ,offset: 'rt' //右上角
                    ,anim: 5
                    ,shadeClose: true
                });
            }
        });
    });
</script>
</body>
</html>