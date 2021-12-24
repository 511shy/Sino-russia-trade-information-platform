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
 		<title>中俄贸易供需服务平台管理系统</title>
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/base.css">
        <script src="js/jquery-1.10.2.js"></script>
        <script src="js/bootstrap.js"></script>
        <script src="js/base.js"></script>
		<script type="text/javascript">
        	$(function(){
        		//用户名失去焦点
        		$("#user").blur(function(){
        			if($(this).val()==""){        				
        				$(".msg").text("用户名不允许为空")
        				$(this).val("");
        			}
        		});
        		
        		//密码失去焦点
        		$("#password").blur(function(){
        			if($(this).val()==""){
        				$(".msg").text("密码不允许为空")
        				$(this).val("");
        			}
        		});
        		
        		
        		$("#but1").click(function(){
        			var result=false;
        			if($("#user").val()==""){
        				$(".msg").text("用户名不允许为空")
        				result=true;
        			}
        			else if($("#password").val()==""){
        				$(".msg").text("密码不允许为空")
        				result=true;
        			}
        			else if($("#code").val()==""){
        				$(".msg").text("验证码不允许为空")
        				result=true;
        			}
        			if(!result){
        				$.ajax({
        					url:"home/verifyCode",
        					type:"get",
        					dataType:"json",
        					data:{
        						code:$("#code").val()
        					},
        					success:function(result){
        						if(result.message=="error"){
        							$("#code").val("");
        							$(".msg").text("验证码错误")
        						}else{
        	        				$.ajax({
        	        					url:"admin/login",
        	        					type:"post",
        	        					dataType:"json",
        	        					data:{
        	        						adminName:$("#user").val(),
        	        						password:$("#password").val()
        	        					},
        	        					success:function(result){
        	        						if(result.message=="error"){
        	        							$(".msg").text("用户名或密码错误")
        	        						}else if(result.message=="success"){
        	        						   //window.location.href="admin/index.html";
        	        							window.location.href="${pageContext.request.contextPath}/admin/index.html";
        	        							//window.open("admin/index.html","_blank");
        	        						}
        	        					}
        	        				});
        						}
        					}
        				});
        			}
        		});
        	});
        	
        	//避免缓存
        	function myRefersh(e) {
        		var source = e.src ; // 获得原来的 src 中的内容
        		var date = new Date(); // 创建一个 Date 对象的 一个 实例
        		var time = date.getTime() ; // 从 新创建的 Date 对象的实例中获得该时间对应毫秒值
        		e.src =source+"?time=" + time ; // 将 加了 尾巴 的 地址 重新放入到 src 上
        	}
        </script>
    </head>
<body>
	
    <div class="modalfom logon container">
        <div class="login-logo"><img src="img/logo-logo.png" class="img-responsive" style="margin:0px auto;"></div>
        <form  class="row">
        	<p class="msg"></p> 
            <div class="form-group row">
                <label class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login1.jpg" ></div>
                    <div class="col-sm-10"><input type="text" autocomplete="off"  name="user" id="user" placeholder="请输入用户名称" ></div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 control-label">密 码</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login2.jpg" ></div>
                    <div class="col-sm-10"><input type="password" id="password"  name="password" placeholder="设置6到20位登录密码"></div>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-3 control-label">验证码</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login2.jpg" ></div>
                    <div class="col-sm-7 col-xs-7"><input type="text" id="code" autocomplete="off" name="code" placeholder="输入验证码"></div>
                    <div class="col-sm-3 col-xs-3"> <img src="home/createCode" onclick="myRefersh(this)"></div>
                </div>
            </div> 
            <div class="res row">
                <div class="col-sm-11"><a href="home_detail/forgetadminpassword.jsp">忘记密码？</a></div>
            </div>
            <div class="row">     
                <label class="col-sm-2 control-label"></label>
                <div class="col-sm-10">
                    <button type="button" class="btn btn-primary btn-lg btn-block" id="but1">登 录</button>
                </div>
            </div>
        </form>
        
    </div>

</body>
</html>