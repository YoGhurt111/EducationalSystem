<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/26
  Time: 21:34
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
    <link rel="stylesheet" type="text/css" href="../statics/DataTables-1.10.13/Select-1.2.2/css/select.bootstrap.min.css">
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
            <li role="presentation"><a href="/student/">主页</a></li>
            <li role="presentation" class="active"><a href="">选课管理</a></li>
            <li role="presentation"><a href="/student/gradeTable">成绩查询</a></li>
            <li role="presentation"><a href="/student/courseTable">课表查看</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <div class="row">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <h3 class="panel-title">课单</h3>
            </div>
            <div class="panel-body">
                <table class="table table-striped table-bordered table-hover" id="selectTable">
                    <thead>
                    <tr>
                        <th>选课</th>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>教师号</th>
                        <th>教师姓名</th>
                        <th>上课时间</th>
                        <th>上课地点</th>
                        <th>学分</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
            </div>
        </div>
        <button class="btn btn-primary" onclick="scCilck()">确认选课</button>
    </div>
    <br>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">当前已选课程</h3>
            </div>
            <div class="panel-body">
                <table id="courseList" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th>退课</th>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>教师号</th>
                        <th>教师姓名</th>
                        <th>上课时间</th>
                        <th>上课地点</th>
                        <th>学分</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <button class="btn btn-success" onclick="dcClick()">确认退课</button>
            </div>
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
<script src="../statics/DataTables-1.10.13/Select-1.2.2/js/dataTables.select.min.js"></script>
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

    var selectTable = $('#selectTable').DataTable({
        ajax:{
            url:"/ajax/getCourse"
        },
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }
        },
        columns: [
            {
                data : null,
                defaultContent: "",
                className:'select-checkbox',
                orderable:false
            },
            {"data": "c_id"},
            {"data": "c_name"},
            {"data": "t_id"},
            {"data": "t_name"},
            {"data": "time"},
            {"data": "location"},
            {"data": "credit"},
        ],
        select: {
            style:    'single',
            selector: 'td:first-child'
        }
    });

    var cList = $('#courseList').DataTable({
        ajax: {
            url: "/student/get_courseList"
        },
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_ 条记录",
            "sZeroRecords": "抱歉， 没有找到",
            "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
            "sInfoEmpty": "没有数据",
            "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }
        },
        columns: [
            {
                data : null,
                defaultContent: "",
                className:'select-checkbox',
                orderable:false
            },
            {"data": "c_id"},
            {"data": "c_name"},
            {"data": "t_id"},
            {"data": "t_name"},
            {"data": "time"},
            {"data": "location"},
            {"data": "credit"}
        ],
        select: {
            style:    'single',
            selector: 'td:first-child'
        }
    });

    function scCilck() {
        var jsonData = selectTable.row({selected:true}).data();
        $.ajax({
            url: "/student/get_select_course",
            type: 'post',
            data: {'jsonData':JSON.stringify(jsonData)},
            dataType: 'json',
            success:function (data) {
                if(data.status == "success"){
                    location.reload(true);
                }
                else {
                    alert("时间冲突，选课失败");
                }
            }
        });
    };

    function dcClick() {
        var jsonData = cList.row({selected:true}).data();
        $.ajax({
            url: "/student/deleteCourse",
            type: 'post',
            data: {'jsonData':JSON.stringify(jsonData)},
            dataType: 'json',
            success:function (data) {
                if(data.status == "success"){
                    alert("退课成功！")
                    location.reload(true);
                }
                else {
                    alert("退课失败");
                }
            }
        });
    }
</script>
</body>
</html>
