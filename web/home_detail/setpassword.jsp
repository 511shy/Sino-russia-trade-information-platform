<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<base href="<%=basePath%>">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>修改密码</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
</head>
<body>
    <div class="modalfom updatepass container">
        <h3>设置新密码</h3>
        <form  class="row">
            <p>请设置 XXXXX 的新密码，建议使用数字、字母、字符的组合密码，提高密码安全等级</p>
            <div class="form-group">
                <input type="text" class="form-control" name="user" placeholder="输入原密码">
                <input type="text" class="form-control" name="user" placeholder="输入新密码，8-16位密码">
                <input type="text" class="form-control" name="user" placeholder="再次输入新密码，8-16位密码">
            </div>
            <div class="row">
                <div class="col-sm-12">
                    <button type="button" class="btn btn-primary btn-lg btn-block">确 认</button>                    
                </div>
            </div>
        </form>
    </div>
</body>
</html>