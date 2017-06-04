<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/21
  Time: 16:15
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
        <h5>身份：教师</h5>
        <h5 id="id">工号：</h5>
        <h5 id="department">院系：</h5>
        <hr>
    </div>
    <div class="row">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/teacher">主页</a></li>
            <li role="presentation"><a href="/teacher/courseList">所授课程</a></li>
            <li role="presentation"><a href="/teacher/updateGrade">成绩评定</a></li>
            <li role="presentation" class="active"><a href="">学生名单</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <br>
    <br>
    <div class="row">
        <table id="studentTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程编号</th>
                <th>课程名称</th>
                <th>学生编号</th>
                <th>学生姓名</th>
                <th>所属院系</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
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
        url: '/teacher/data',
        type: 'get',
        dataType: 'json',
        success: function (data) {
            $('#name').append(data.name);
            $("#jName").append(data.name);
            $("#jId").append(data.id);
            $('#id').append(data.id);
            $("#department").append(data.department);
            $("#d_location").append(data.d_location);
        },
        error: function () {
            location.assign("/");
        }
    });
</script>
<script>
    var studentTable = $("#studentTable").dataTable({
        ajax:{
            url:"/teacher/getStudentList",
            dataType:"json"
        },
        columns:[
            {"data":"no"},
            {"data":"c_id"},
            {"data":"c_name"},
            {"data":"s_id"},
            {"data":"s_name"},
            {"data":"department"}
        ]
    });
</script>
</body>
</html>

