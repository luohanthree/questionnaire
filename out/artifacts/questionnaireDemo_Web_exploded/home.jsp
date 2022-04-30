<!DOCTYPE html>
<html lang="zh ">
<head>
  <meta charset="utf-8">
  <title>Collapsing sidebar drawer menu</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <style>
      body {
          padding-top: 51px;
      }
      .text-center {
          padding-top: 20px;
      }
      .col-xs-12 {
          background-color: #fff;
      }
      #sidebar {
          height: 100%;
          padding-right: 0;
          padding-top: 20px;
      }
      #sidebar .nav {
          width: 95%;
      }
      #sidebar li {
          border:0 #f2f2f2 solid;
          border-bottom-width:1px;
      }

      /* collapsed sidebar styles */
      @media screen and (max-width: 767px) {
          .row-offcanvas {
              position: relative;
              -webkit-transition: all 0.25s ease-out;
              -moz-transition: all 0.25s ease-out;
              transition: all 0.25s ease-out;
          }
          .row-offcanvas-right
          .sidebar-offcanvas {
              right: -41.6%;
          }

          .row-offcanvas-left
          .sidebar-offcanvas {
              left: -41.6%;
          }
          .row-offcanvas-right.active {
              right: 41.6%;
          }
          .row-offcanvas-left.active {
              left: 41.6%;
          }
          .sidebar-offcanvas {
              position: absolute;
              top: 0;
              width: 41.6%;
          }
          #sidebar {
              background-color:#3b3b3b;
              padding-top:0;
          }
          #sidebar .nav>li {
              color: #ddd;
              background: linear-gradient(#3E3E3E, #383838);
              border-top: 1px solid #484848;
              border-bottom: 1px solid #2E2E2E;
          }
          #sidebar .nav>li:first-child {
              border-top:0;
          }
          #sidebar .nav>li>a {
              color: #ddd;
          }
          #sidebar .nav>li>a>img {
              max-width: 14px;
          }
          #sidebar .nav>li>a:hover, #sidebar .nav>li>a:focus {
              text-decoration: none;
              background: linear-gradient(#373737, #323232);
              color: #fff;
          }
          #sidebar .nav .caret {
              border-top-color: #fff;
              border-bottom-color: #fff;
          }
          #sidebar .nav a:hover .caret{
              border-top-color: #fff;
              border-bottom-color: #fff;
          }
      }



  </style>
</head>
<body>
<div class="page-container">
  <!-- top navbar -->
  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="offcanvas" data-target=".sidebar-nav">
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">问卷调查</a>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row row-offcanvas row-offcanvas-left">

      <!-- sidebar -->
      <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
        <ul class="nav">
          <li class="active"><a href="#">全部表单</a></li>
          <li><a href="#">创建新的表单</a></li>
        </ul>
      </div>

      <!-- main area -->
      <div class="col-xs-12 col-sm-9">
        <table class="table table-hover" >
          <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">表达名字</th>
            <th scope="col">填写人数</th>
            <th scope="col">查看</th>
          </tr>
          </thead>
          <tbody>
            <c:forEach items="${requestScope.forms}" var="form" varStatus="status">
                <tr>
                  <td>${status.count}</td>
                  <td>${form.formName}</td>
                  <td>${form.numbers}</td>
                  <td><a style="text-decoration: none">查看</a></td>
                </tr>
            </c:forEach>
          </tbody>
        </table>
      </div><!-- /.col-xs-12 main -->
    </div><!--/.row-->
  </div><!--/.container-->
</div><!--/.page-container-->
<!-- script references -->
<script src="js/jquery-3.6.0.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>