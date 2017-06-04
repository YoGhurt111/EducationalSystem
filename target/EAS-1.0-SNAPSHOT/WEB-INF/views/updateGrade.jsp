<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/22
  Time: 15:26
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
    <link rel="stylesheet" type="text/css" href="../statics/DataTables-1.10.13/media/css/editor.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="../statics/bootstrap-3.3.7-dist/css/fileinput.min.css">
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
            <li role="presentation"><a href="/teacher/courseList">所授课程</a></li>
            <li role="presentation" class="active"><a href="">成绩评定</a></li>
            <li role="presentation"><a href="/teacher/studentList">学生名单</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <br>
    <div class="row">
        <table id="gradeTable" class="table table-striped table-bordered">
            <thead>
            <tr>
                <th>课程编号</th>
                <th>课程名称</th>
                <th>学分</th>
                <th>学生编号</th>
                <th>学生姓名</th>
                <th>平时成绩</th>
                <th>期末成绩</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
        <a href="/teacher/downloadSTC">下载登分表</a>
        <br>
        <div class="row col-md-6">
            <label for="file">上传成绩表</label>
            <form enctype="multipart/form-data">
                <input id="file" type="file" name="uploadfile"
                       multiple data-min-file-count="1">
            </form>
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
<script src="../statics/bootstrap-3.3.7-dist/js/fileinput.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script src="../statics/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<!-- DataTables -->
<script src="../statics/DataTables-1.10.13/media/js/jquery.dataTables.js"></script>
<script src="../statics/DataTables-1.10.13/media/js/dataTables.editor.min.js"></script>

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
    var gradeTable = $("#gradeTable").dataTable({
        ajax:{
            url:"/teacher/getGradeTable"
        },
        columns:[
            { data: "c_id" },
            { data: "c_name" },
            { data: "credit" },
            { data: "s_id" },
            { data: "s_name" },
            { data: "u_grade"},
            { data: "f_grade"}
            ]
    });

    $("#file").fileinput({
        uploadUrl:"/teacher/uploadSTC",
        allowedFileExtensions: ['xls'],
        overwriteInitial: true,
        maxFilesNum: 1,//上传最大的文件数量
        initialCaption: "请上传你要上传的excel",//文本框初始话value
        showUpload: true, //是否显示上传按钮
        showCaption: true,//是否显示标题
        uploadAsync: true, //默认异步上传
        showRemove: true, //显示移除按钮
        showPreview: true, //是否显示预览
        browseClass: "btn btn-success", //按钮样式
        dropZoneEnabled: false,//是否显示拖拽区域
        minImageWidth: 100, //图片的最小宽度
        minImageHeight: 50,//图片的最小高度
        maxImageWidth: 100,//图片的最大宽度
        maxImageHeight: 50,//图片的最大高度
        maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
        minFileCount: 0,
        maxFileCount: 10, //表示允许同时上传的最大文件个数
        enctype: 'multipart/form-data',
        validateInitialCount: true,
        slugCallback: function (filename) {
            return filename;
        }
    });

</script>
</body>
</html>
