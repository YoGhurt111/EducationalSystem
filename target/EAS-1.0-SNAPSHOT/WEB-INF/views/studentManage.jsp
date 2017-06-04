<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 2017/5/28
  Time: 17:41
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
    <link rel="stylesheet" type="text/css" href="../statics/bootstrap-3.3.7-dist/css/fileinput.min.css">
    <link rel="stylesheet" type="text/css" href="../statics/DataTables-1.10.13/media/css/editor.dataTables.min.css">
    <title>上海大学教务管理系统</title>

</head>
<body>
<div class="container">
    <div class="row">
        <h3 id="name"></h3>
        <h5>身份：管理员</h5>
        <h5 id="id">管理员编号：</h5>
        <hr>
    </div>
    <br>
    <div class="row">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/admin/">主页</a></li>
            <li role="presentation"><a href="">课程管理</a></li>
            <li role="presentation" class="active"><a href="">学生管理</a></li>
            <li role="presentation"><a href="">教师管理</a></li>
            <li role="presentation"><a href="">院系管理</a></li>
            <li role="presentation"><a href="/logout">退出</a></li>
        </ul>
    </div>
    <br>
    <div class="row">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">当前已有学生</h3>
            </div>
            <div class="panel-body">
                <table id="studentList" class="table table-striped table-bordered">
                    <thead>
                    <tr>
                        <th></th>
                        <th>学生编号</th>
                        <th>学生名称</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <button class="btn btn-success" onclick="dsClick()">确认删除</button>
            </div>
        </div>
    </div>
    <a href="/admin/download">下载注册课程表</a>
    <br>
    <div class="row col-md-6">
        <label for="file">上传注册课程表</label>
        <form enctype="multipart/form-data">
            <input id="file" type="file" name="uploadfile"
                   multiple data-min-file-count="1">
        </form>
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
<script src="../statics/bootstrap-3.3.7-dist/js/fileinput.min.js"></script>
<!-- DataTables -->
<script src="../statics/DataTables-1.10.13/media/js/jquery.dataTables.js"></script>
<script src="../statics/DataTables-1.10.13/Select-1.2.2/js/dataTables.select.min.js"></script>
<script>
    $.ajax({
        url: '/admin/admin_info',
        type: 'post',
        data: {},
        dataType: 'json',
        success: function (data) {
            $('#name').append(data.name);
            $('#id').append(data.id);
        },
        error: function () {
            location.assign("/");
        }
    });



    var sList = $('#studentList').DataTable({
        ajax: {
            url: "/admin/get_student_list"
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
            {"data": "s_id"},
            {"data": "s_name"},
        ],
        select: {
            style:    'single',
            selector: 'td:first-child'
        }
    });

    function dsClick() {
        var jsonData = sList.row({selected:true}).data();
        $.ajax({
            url: "/admin/delete_student",
            type: 'post',
            data: {'jsonData':JSON.stringify(jsonData)},
            dataType: 'json',
            success:function (data) {
                if(data.status == "success"){
                    alert("删除学生成功！")
                    location.reload(true);
                }
                else {
                    alert("删除学生失败！");
                }
            }
        });
    };

    $("#file").fileinput({
        uploadUrl:"/admin/upload",
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
