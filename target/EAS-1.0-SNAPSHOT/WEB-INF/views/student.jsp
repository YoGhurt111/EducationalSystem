<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/4/7
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="stylesheet" href="../statics/bootstrap-3.3.7-dist/css/bootstrap.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="../statics/DataTables-1.10.13/media/css/jquery.dataTables.css">
    <title>上海大学教务管理系统</title>

</head>
<body>
<div class="container">
    <div class="row">
            <h3 id="name"></h3>
            <h5>身份：学生</h5>
            <h5 id="id">学号：</h5>
            <h5 id="department">院系：</h5>
        <hr>
    </div>
    <div class="row">
        <ul class="nav nav-tabs">
            <li role="presentation" class="active"><a href="">主页</a></li>
            <li role="presentation"><a href="">选课管理</a></li>
            <li role="presentation"><a href="/student/gradeTable">成绩查询</a></li>
            <li role="presentation"><a href="/student/courseTable">课表查看</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <div id="summary" class="row">
        <div class="jumbotron">
            <h2 id="jName">姓名：</h2>
            <h2 id="jId">学号：</h2>
            <h2 id="jGPA">上学期绩点：</h2>
            <h2 id="term">当前学期：</h2>
        </div>
    </div>
    <div class="row" style="text-align: center; bottom: 10px; position: fixed">
        <i>
            版权所有@<a href="http://www.shu.edu.cn/IndexPage.html">上海大学 </a>
            <a href="http://www.miibeian.gov.cn/state/outPortal/loginPortal.action">沪ICP备09014157 </a>
            地址：<a href="http://map.shu.edu.cn/">上海市宝山区上大路99号（周边交通）</a>
            邮编：200444 <a href="http://www.shu.edu.cn/Default.aspx?tabid=8558">电话查询 </a>
            技术支持：<a href="http://www.its.shu.edu.cn/">上海大学信息化工作办公室 </a>
            <a href="/">联系我们</a>
        </i>
    </div>
</div>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="../statics/JQuery/jquery-3.2.1.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script src="../statics/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- DataTables -->
<script src="../statics/DataTables-1.10.13/media/js/jquery.dataTables.js"></script>
<script>
    $.ajax({
        url: '/student/data',
        type: 'post',
        data: {},
        dataType: 'json',
        success: function (data) {
            $('#name').append(data.name);
            $("#jName").append(data.name);
            $("#jId").append(data.id);
            $('#id').append(data.id);
            $("#department").append(data.department);
        },
        error: function () {
            location.assign("/");
        }
    });

    $.ajax({
        url:'/ajax/term',
        type:'get',
        dataType:'json',
        success:function (data) {
            switch (data.term){
                case "spring":
                    $('#term').append("春季学期");
                    break;
                case "summer":
                    $('#term').append("夏季学期");
                    break;
                case "autumn":
                    $('#term').append("秋季学期");
                    break;
                case "winter":
                    $('#term').append("冬季学期");
                    break;
                case "vacation":
                    $('#term').append("放假中");
                    break;
            }
        },
        error:function () {
            alert('读取学期数据失败');
        }
    });
</script>
</body>
</html>
