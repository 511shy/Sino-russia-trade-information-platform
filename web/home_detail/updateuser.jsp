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
    <title>企业资料</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script type="text/javascript">
    
    	$(function(){
    		
    		var query =decodeURI(window.location.search);
    		var userName=query.split("=")[1];
    		
    		//回显
    		$.ajax({
    			url:"home/queryCompanyByName",
				type:"post",
				dataType:"json",
				data:{
					userName:userName
				},
				success:function(result){
					if(result.message=="success"){
						$("#comid").val(result.obj.comid);
						$("#cname").val(result.obj.comName);
						$("#comName").val(result.obj.comName);
						$("#user").val(result.obj.userName);
						$("#uname").val(result.obj.userName);
						$("#telephone").val(result.obj.telephone);
						$("#tphone").val(result.obj.telephone);
						$("#contacts").val(result.obj.contacts);
						$("#management").val(result.obj.management);
					}
				}
    		});
    		
    		
    		//用户名失去焦点
    		$("#user").blur(function(){
    			if($(this).val()!=""){
    				if($(this).val()!=$("#uname").val()){
    					$.ajax({
        					url:"home/queryCompanyCountByName",
        					type:"post",
        					dataType:"json",
        					data:{
        						userName:$("#user").val()
        					},
        					success:function(result){
        						if(result.message=="exist"){
        							$("#user").val("");
        							$(".msg").eq(0).text("用户名已被注册过")
        						}else if(result.message=="noexist"){
        							$(".msg").eq(0).text("可以注册")
        						}
        					}
        				});
    				}
    			}else{
    				$(".msg").eq(0).text("用户名不许为空")
    				$(this).val("");
    			}
    		});
    		
    		//企业名失去焦点
    		$("#comName").blur(function(){
    			if($(this).val()!=""){
    				if($(this).val()!=$("#cname").val()){
        				$.ajax({
        					url:"home/queryCompanyCountByComName",
        					type:"post",
        					dataType:"json",
        					data:{
        						comName:$("#comName").val()
        					},
        					success:function(result){
        						if(result.message=="exist"){
        							$("#comName").val("");
        							$(".msg").eq(1).text("企业名已被注册过")
        						}else if(result.message=="noexist"){
        							$(".msg").eq(1).text("可以注册")
        						}
        					}
        				});
    				}
    			}else{
    				$(".msg").eq(1).text("企业名不许为空")
    				$(this).val("");
    			}
    		});
    		
    		//手机号失去焦点
    		$("#telephone").blur(function(){
    			if($(this).val()!=""){
    				var reg = /^1(3[0-9]|4[5,7]|5[0,1,2,3,5,6,7,8,9]|6[2,5,6,7]|7[0,1,7,8,6]|8[0-9]|9[1,8,9])\d{8}$/;
    				if(!reg.test($("#telephone").val()) || !$("#telephone").val().length==11){
    					$("#telephone").val("");
    					$(".msg").eq(4).text("手机号格式不正确")
    				}else{
    					if($(this).val()!=$("#tphone").val()){
        					$.ajax({
            					url:"home/checkTelephone",
            					type:"post",
            					dataType:"json",
            					data:{
            						telephone:$("#telephone").val()
            					},
            					success:function(result){
            						if(result.message=="exist"){
            							$("#telephone").val("");
            							$(".msg").eq(4).text("手机号已被注册过")
            						}else if(result.message=="noexist"){
            							$(".msg").eq(4).text("可以注册")
            						}
            					}
            				});
    					}
    				}
    			}else{
    				$(".msg").eq(4).text("手机号不允许为空")
    			}
    		});
    		
    		//发送验证码
    		$("#createCode").click(function(){
    			if($("#telephone").val()!=""){
    				//生成验证码
    				$.ajax({
    					url:"home/createMsgCode",
    					type:"post",
    					dataType:"json",
    					data:{
    						telephone:$("#telephone").val()
    					},
    					success:function(result){
    						if(result.message=="success"){    							
    							var num=60
    							$("#createCode").attr("disabled",true);    													
    							var t=setInterval(function() {
    								if(num<=0){
    									$("#createCode").html("获得验证码");
    									$("#createCode").attr("disabled",false); 
    									clearInterval(t)
    									return;
    								}
    								$("#createCode").html(num--+"秒")   
  								   								
								}, 1000);
    						}else{
    							$(".msg").eq(5).text("发送失败")
    						}
    					}
    				});
    			}
    			else{
    				$(".msg").eq(4).text("手机号不允许为空")
    			}
    		});
    		
    		
    		//注册
    		$("#but1").click(function(){
    			$(".msg").text("")
    			var result=false;
    			if($("#user").val()==""){
    				$(".msg").eq(0).text("用户名不许为空")
    				result=true;
    			}
    			if($("#comName").val()==""){
    				$(".msg").eq(1).text("企业名称不许为空")
    				result=true;
    			}
    			if($("#management").val()==""){
    				$(".msg").eq(2).text("主营项目不许为空")
    				result=true;
    			}
    			if($("#contacts").val()==""){
    				$(".msg").eq(3).text("联系人不许为空")
    				result=true;
    			}
    			if($("#telephone").val()==""){
    				$(".msg").eq(4).text("手机号不许为空")
    				result=true;
    			}
    			if($("#code").val()==""){
    				$(".msg").eq(5).text("验证码不许为空")
    				result=true;
    			}
    			if(!result){
    				$.ajax({
    					url:"home/verifyMsgCode",
    					type:"post",
    					dataType:"json",
    					data:{
    						msgCode:$("#code").val()
    					},
    					success:function(result){
    						if(result.message=="success"){ //通过
    							$.ajax({
    		    					url:"home/updateCompany",
    		    					type:"post",
    		    					dataType:"json",
    		    					data:{
    		    						comid:$("#comid").val(),
    		    						userName:$("#user").val(),
    		    						comName:$("#comName").val(),
    		    						contacts:$("#contacts").val(),
    		    						management:$("#management").val(),
    		    						telephone:$("#telephone").val()
    		    					},
    		    					success:function(result){
    		    						if(result.message=="error"){
    		    							$(".msg").eq(5).text("修改失败")
    		    						}else if(result.message=="success"){
    		    							$(".msg").eq(5).text("修改成功,请重新登陆")
    		    							 window.location.href="home_detail/login.jsp?s=1";
    		    						}
    		    					}
    		    				});
    						}else if(result.message=="delayed"){ //过期
    							$(".msg").eq(5).text("验证码已过期，请重新发送")
    							$("#code").val("");
    						}else if(result.message=="error"){ //验证未通过
    							$(".msg").eq(5).text("验证码不正确")
    							$("#code").val("");
    						}
    					}
    				});
    			}
    		});
    	});
    	
    </script>
</head>
<body>
	<!--头部-->
     <%@ include file="header.jsp" %>
    <div class="modalfom reg container ">
        <h3>企业资料</h3>
        <form  class="row">
        	<input type="hidden" id="comid"/>
        	<input type="hidden" id="uname"/>
        	<input type="hidden" id="cname"/>
        	<input type="hidden" id="tphone"/>
            <div class="row">
                <label class="col-sm-3 control-label">用户名</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login1.jpg" ></div>
                    <div class="col-sm-10"><input type="text" id="user"  name="user" placeholder="请输入用户名称" ></div>
                </div>             
            </div>
            <p class="msg"></p>   
            <div class="row">
                <label class="col-sm-3 control-label">企业全称</label> 
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login3.jpg" width="15px" height="20px"></div>
                    <div class="col-sm-10"><input type="text" id="comName" name="comName" placeholder="输入企业全称"></div>
                </div>
            </div>
             <p class="msg"></p>   
            <div class="row">
                <label class="col-sm-3 control-label">主营项目</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login3.jpg" width="15px" height="20px"></div>
                    <div class="col-sm-10"><input type="text" id="management" name="" placeholder="输入主营项目"></div>
                </div>
            </div>
            <p class="msg"></p>   
            <div class="row">
                <label class="col-sm-3 control-label">联系人</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login1.jpg" ></div>
                    <div class="col-sm-10"><input type="text" id="contacts"  name="" placeholder="请输入联系人" ></div>
                </div>             
            </div>        
             <p class="msg"></p>   
            <div class="row">
                <label class="col-sm-3 control-label">手机号</label>
                <div class="col-sm-9 fom row">
                    <div class="col-sm-1 hidden-xs"><img src="img/login3.jpg" width="15px" height="20px"></div>
                    <div class="col-sm-10"><input type="text" id="telephone" name="telephone" placeholder="输入手机号"></div>
                </div>
            </div>
             <p class="msg"></p>   
            <div class="row">
                <label class="col-sm-3 control-label">短信验证码</label>
                <div class="col-sm-9 fom row">
                <div class="col-sm-8 col-xs-6"> 
                	<div class="col-sm-1 hidden-xs paddingiocn"><img src="img/login4.jpg" width="17px" class="emailicon"></div>
                	<div class="col-sm-7 col-xs-8"><input type="text" name="code" id="code" placeholder="输入验证码"></div>
                    <div class="col-sm-4 col-xs-4" ><button type="button" id="createCode" class="btn btn-default">获得验证码</button></div>
                </div>
            </div>
            </div>
             <p class="msg"></p>   
            <div class="row">
                <div class="col-sm-2"></div>
                <div class="col-sm-10">
                    <button type="button" id="but1" class="btn btn-primary btn-lg btn-block">提 交</button>
                </div>
            </div>
        </form>
        
    </div>
  	<!--底部-->
    <%@ include file="footer.jsp" %>
</body>
</html>