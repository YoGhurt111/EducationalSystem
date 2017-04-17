<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>上海大学教务管理系统</title>

</head>
<body>
<div class="container">
    <div class="row" style="margin-top:100px">
        <div class="col-lg-offset-4 col-lg-4">
            <h1>上海大学教务管理系统</h1>
            <hr>
        </div>
    </div>
    <div class="row" style="margin-top: 20px">
        <form class="form-horizontal" role="form" action="/selectUser" method="post">
            <div class="col-lg-offset-4 col-lg-4">
                <div class="form-group">
                    <label class="control-label"> 学号/工号 </label>
                    <input type="text" class="form-control" name="username" placeholder="请输入您的学号或工号" required>
                </div>
            </div>
            <div class="col-lg-offset-4 col-lg-4">
                <div class="form-group">
                    <label class="control-label"> 密码 </label>
                    <input type="password" class="form-control" name="pwd" placeholder="请输入您的密码" required>
                </div>
            </div>
            <div class="col-lg-offset-4 col-lg-4">
                <div class="form-group">
                    <label class="control-label">登录身份</label>
                    <select class="form-control" onchange="change()">
                        <option value="s" selected="selected">学生</option>
                        <option value="t">教师</option>
                        <option value="a">管理员</option>
                    </select>
                </div>
            </div>
            <div class="col-lg-offset-4 col-lg-1">
                <button type="commit" class="btn btn-success">登录</button>
            </div>
            <div class="col-lg-offset-2 col-lg-1">
                <button type="reset" class="btn btn-warning">重置</button>
            </div>
            <div class="col-lg-offset-4 col-lg-4" style="margin-top: 20px">
                <p id="tips">提醒：您现在的身份是学生，请输入8位学号登录系统</p>
            </div>
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

<script>
    function change(){
        switch ($("select").val()){
            case "s":
                $("#tips").html("提醒：您现在的身份是学生，请输入8位学号登录系统");
                break;
            case "t":
                $("p").html("提醒：您现在的身份是教师，请输入10位工号登录系统");
                break;
            case "a":
                $("#tips").html("提醒：您现在的身份是管理员，请输入4位工号登录系统");
                break;
        }
    }
</script>

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>