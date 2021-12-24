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
     <title>升级vip</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script type="text/javascript">
    $(function(){
		$.ajax({
			url:"home/queryCurrentUser",
			type:"post",
			dataType:"json",
			success:function(result){
				if(result.message=="success"){ //如果登陆过，升级vip中的企业注册不让点击
					 $("#butqy").attr("disabled",true); 
				}
			}
		});
	});
    </script>
</head>
<body>
	<!--头部-->
    <%@ include file="header.jsp" %>
    <div class="modalfom vip container">
        <h3>升级企业VIP <img src="img/vip.jpg" width="50px" height="26px" ></h3>
        <form  class="row">
            <p class="step">第<span>1</span>步</p>
            <h4>注册成为本站企业会员，若已完成请勿略</h4>
            <div class="row">
                <div class="col-sm-12">
                    <button type="button" id="butqy" class="btn btn-primary btn-lg btn-block"  onclick="window.location.href='home_detail/register.jsp'">企业注册</button>
                </div>
            </div>
            <p class="step">第<span>2</span>步</p>
            <h4>点击“下载VIP企业入驻表格”，</h4>
            <h4>填写完毕发送管理员邮箱<span>8888XXXX@163.com</span></h4>
            <h4>我们会第一时间联系您！</h4>
            <div class="row">
                <div class="col-sm-12">
                    <button type="button" class="btn btn-primary btn-lg btn-block" onclick="window.location.href='home/downloadVIPTable'">下载VIP企业入驻表格</button>
                </div>
            </div>
        </form>
    </div>    
 	<!--底部-->
    <%@ include file="footer.jsp" %>
</body>
</html>