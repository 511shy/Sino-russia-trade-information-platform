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
    <title>企业入驻</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
</head>
<body>
	<!--头部-->
     <%@ include file="header.jsp" %>
    <!--普通企业注册-->
    <div class="modalfom comres container tipscode">
        <h3>普通企业注册</h3>
        <form >
            <div class="row">
                <label  class="col-sm-3">用户名</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="请输入用户名称">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
           
            <div class="row">
                <label  class="col-sm-3">企业全称</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="请输入企业全称">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
            
            <div class="row">
                <label  class="col-sm-3">主营项目</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="请输入主营项目">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
          
            <div class="row">
                <label  class="col-sm-3">联系人</label>
                <div class="col-sm-9">
                    <input type="text" placeholder="请输入联系人">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
           
            <div class="row">
                <label  class="col-sm-3">联系电话</label>
                <div class="col-sm-9">
                    <input type="text"  placeholder="请输入手机号码">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
            
            <div class="row">
                <label  class="col-sm-3">手机验证码</label>
                <div class="col-sm-9">
                    <div class="row codes">
                        <div class="col-sm-8 col-xs-7"><input type="text" name="code" placeholder="短信验证码"></div>
                        <div class="col-sm-4 col-xs-4"><button type="button" class="btn btn-default">获得验证码</button></div>
                    </div>
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>

            <div class="row">
                <label  class="col-sm-3">密  码</label>
                <div class="col-sm-9">
                    <input type="password"  placeholder="设置6到20登录密码">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
           
            <div class="row">
                <label  class="col-sm-3">确认密码</label>
                <div class="col-sm-9">
                    <input type="password" placeholder="再次输入登录密码">
                    <div class="alerter"><img src="img/error.png">错误！请进行一些更改。</div>
                </div>
            </div>
            
            <div class="row">
                <div class="col-sm-11">
                    <button type="button" class="btn btn-primary btn-lg btn-block">注 册</button>                    
                </div>
            </div>
        </form>
    </div>   
 	<!--底部-->
    <%@ include file="footer.jsp" %>
</body>
</html>