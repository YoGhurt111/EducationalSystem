<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link href="https://cdn.bootcss.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" rel="stylesheet">
    <title>上海大学教务管理系统</title>
</head>
<body>
<h1>上海大学教务管理系统</h1>
<form class="form-horizontal" role="form" action="/selectUser" method="post">
    <div class="form-group">
        <label class="control-label"> 学号/工号 </label>
        <input type="text" class="form-control" name="username" placeholder="请输入您的学号或工号" required>
    </div>
    <div class="form-group">
        <label class="control-label"> 密码 </label>
        <input type="password" class="form-control" name="pwd" placeholder="请输入您的密码" required>
    </div>
    <div class="form-group">
        <label class="control-label">登录身份</label>
        <select class="form-control">
            <option>学生</option>
            <option>教师</option>
            <option>管理员</option>
        </select>
    </div>
    <button type="commit" class="btn btn-success">登录</button>
    <button type="reset" class="btn btn-warning">重置</button>
</form>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/4.0.0-alpha.5/js/bootstrap.min.js"></script>
</body>
</html>