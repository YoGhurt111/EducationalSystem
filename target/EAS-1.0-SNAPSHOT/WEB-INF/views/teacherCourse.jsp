<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/21
  Time: 20:13
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
            <li role="presentation"><a href="/teacher/">主页</a></li>
            <li role="presentation" class="active"><a href="">所授课程</a></li>
            <li role="presentation"><a href="/teacher/updateGrade">成绩评定</a></li>
            <li role="presentation"><a href="/teacher/studentList">学生名单</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <br>
    <div class="row">
        <table id="courseList" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>序号</th>
                <th>课程编号</th>
                <th>课程名称</th>
                <th>上课时间</th>
                <th>上课地点</th>
                <th>学分</th>
                <th>选课人数</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>
    <br>
    <hr>
    <button onclick="showCourseTable()">显示排课表</button>
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <table id="courseTable" class="table table-bordered table-hover table-responsive" hidden="hidden">
                <thead>
                <tr>
                    <th class="text-center">上课时间</th>
                    <th class="text-center">一</th>
                    <th class="text-center">二</th>
                    <th class="text-center">三</th>
                    <th class="text-center">四</th>
                    <th class="text-center">五</th>
                </tr>
                </thead>
                <tbody>
                <tr class="text-center">
                    <td>8:00<br>1</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>8:55<br>2</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>10:00<br>3</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>10:55<br>4</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>12:10<br>5</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>13:05<br>6</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>14:10<br>7</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>15:05<br>8</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>16:00<br>9</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>16:55<br>10</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>18:00<br>11</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>18:55<br>12</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr class="text-center">
                    <td>19:50<br>13</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
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
<script src="../statics/DataTables-1.10.13/media/js/jquery.dataTables.js">
</script>
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

    var cList = $('#courseList').dataTable({
        ajax:{
            url:"/teacher/getCourseList"
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
            {"data": "no"},
            {"data": "c_id"},
            {"data": "c_name"},
            {"data": "time"},
            {"data": "location"},
            {"data": "credit"},
            {"data": "number"}
        ]
    });

    function showCourseTable() {
        $("#courseTable").removeAttr('hidden');
        var list = cList.api();
        var row = cList.fnGetNodes().length;
        var courseTable = $('#courseTable');
        for (var i = 1; i <= 13; i++) {
            for (var j = 1; j <= 5; j++) {
                courseTable.find('tr').eq(i).find('td').eq(j).html('');
            }
        }
        for (var i=0; i<row; i++){
            var day = list.cell(i,3).data().trim().substring(1,2);
            var start = list.cell(i,3).data().trim().substring(2,3);
            var end = list.cell(i,3).data().trim().substring(4,5);
            switch(day){
                case "一":
                    day = 1;
                    break;
                case "二":
                    day = 2;
                    break;
                case "三":
                    day = 3;
                    break;
                case "四":
                    day = 4;
                    break;
                case "五":
                    day = 5;
                    break;
            };
            for (var j = start; j <= end; j++) {
                courseTable.find('tr').eq(j).find("td").eq(day).html(list.cell(i,0).data());
            }
        }
    }


</script>
</body>
</html>
