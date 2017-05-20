<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/4/7
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="stylesheet" href="../statics/bootstrap-3.3.7-dist/css/bootstrap.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="../statics/DataTables-1.10.13/media/css/jquery.dataTables.css">
    <title>上海大学教务管理系统:学生主页</title>

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
            <li role="presentation"><a href="/student">主页</a></li>
            <li role="presentation"><a href="">选课管理</a></li>
            <li role="presentation" class="active"><a href="">成绩查询</a></li>
            <li role="presentation"><a href="/student/courseTable">课表查看</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <br>
    <br>
    <div class="row">
        <table id="gradeTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程编号</th>
                <th>课程名称</th>
                <th>教师号</th>
                <th>教师姓名</th>
                <th>学分</th>
                <th>最终成绩</th>
                <th>绩点</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <h4 id="total_credit">总学分：</h4>
        <h4 id="a_gpa">平均绩点：</h4>
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
</script>
<script>
    var gradeTable = $('#gradeTable').DataTable({
        ajax:{
            url:"/student/get_grade",
            dataType:"json"
        },
        columns: [
            {"data": "no"},
            {"data": "c_id"},
            {"data": "c_name"},
            {"data": "t_id"},
            {"data": "t_name"},
            {"data": "credit"},
            {"data": "grade"},
            {"data": "gpa"}
        ]
    });
    $.ajax({
        url:"/student/get_total_credit",
        dataType:"json",
        type:"get",
        success:function (data) {
            $("#total_credit").append(data.t_credit);
            $("#a_gpa").append(data.gpa);
        },
        error:function () {
            alert("读取总学分和平均绩点错误");
        }
    });
</script>
</body>
</html>
