<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>上海大学教务管理系统</title>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
</head>
<body>
<h1>上海大学教务管理系统</h1>
<form action="/selectUser" method="post">
    学号/工号：<input type="text"   name="username"><br>
    密码：<input   type="password" name="pwd">
    <input type="submit" value="Submit">
    <%--<button id="t666">ttt</button>--%>
</form>
</body>
</html>