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
    <title>忘记密码</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <style type="text/css">
    	.modalfom{
    		display: none;
    	}
    	.forget{
    		display: block;
    	}
    </style>
    <script type="text/javascript">
    	$(function(){
    	
    		
    		$("#but1").click(function(){
    			if($("#user").val()!=""){
    				//根据用户名查询
    				$.ajax({
    					url:"home/queryCompanyByName",
    					type:"post",
    					dataType:"json",
    					data:{
    						userName:$("#user").val()
    					},
    					success:function(result){
    						if(result.message=="success"){
    							$(".modalfom").hide();
    							$(".tipscode").show();
    							
    							$(".userName").html(result.obj.userName);
    							
    							var telephone =result.obj.telephone;
    							var telephoneLength = telephone.length;
    							$("#telephone").html("******"+telephone.substring(telephoneLength-4,telephoneLength));
    							
    							$("#comid").val(result.obj.comid);
    							$("#phone").val(result.obj.telephone);
    						}else{
    							$(".msg").eq(0).text("对不起，当前用户不存在")
    						}
    					}
    				});
    				
    			}
    			else{
    				$(".msg").eq(0).text("用户名不允许为空")
    			}
    		});
    		
    		
    		$("#createCode").click(function(){
    			//生成验证码
				$.ajax({
					url:"home/createMsgCode",
					type:"post",
					dataType:"json",
					data:{
						telephone:$("#phone").val()
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
							$(".msg").eq(1).text("发送失败")
						}
					}
				});
    		});
    		
    		
    		//验证验证码
    		$("#but2").click(function(){
    			if($("#code").val()!=""){
    				$.ajax({
    					url:"home/verifyMsgCode",
    					type:"post",
    					dataType:"json",
    					data:{
    						msgCode:$("#code").val()
    					},
    					success:function(result){
    						if(result.message=="success"){ //通过
    							$(".modalfom").hide();
    							$(".setpass").show();
    						}else if(result.message=="delayed"){ //过期
    							$(".msg").eq(1).text("验证码已过期，请重新发送")
    							$("#code").html("");
    						}else if(result.message=="error"){ //验证未通过
    							$(".msg").eq(1).text("验证码不正确")
    							$("#code").html("");
    						}
    					}
    				});
    			}else{
    				$(".msg").eq(1).text("验证码不允许为空")
    			}
    		});
    		
    		$("#but3").click(function(){
    			var password=$("#password").val();    
    			var password1=$("#password1").val();
    			var reg=/^(.){6,20}$/;
    			if(password==""){
    				$(".msg").eq(2).text("密码不允许为空")
    			}
    			else if(reg.test(password)==false){
    				$(".msg").eq(2).text("不符合规则，满足6-20位")
    			}
    			else if(password!=password1){
    				$(".msg").eq(2).text("两次密码输入不一致")
    			}
    			else{
    					//修改密码
    					$.ajax({
    						url:"home/updatePwd",
    						type:"post",
    						dataType:"json",
    						data:{
    							comid:$("#comid").val(),
    							password:password
    						},
    						success:function(result){
    							if(result.message=="success"){
    								$(".modalfom").hide()
    								$(".success").show()
    								
    							}else{;
    								$(".msg").eq(2).text("修改失败")
    							}
    						}
    					});  				
    			}
    		});
    		$("#but4").click(function(){
    			location.href="home_detail/login.jsp"
    		})
    	});
    </script>
</head>
<body>
	<!--头部-->
    <%@ include file="header.jsp" %>
    <!--忘记密码 -->   
    <div class="modalfom forget container">
        <h3>忘记密码</h3>
        <form  class="row">
            <div class="form-group">
                <label for="name">请输入要重置密码的用户名</label>
                <input type="text" class="form-control" id="user" name="user" placeholder="请输入要重置密码的用户">
            </div>
            <p class="msg"></p>  
            <div class="row">
                <div class="col-sm-11">
                    <button type="button" id="but1" class="btn btn-primary btn-lg btn-block">下一步</button>
                </div>
            </div>
        </form>
    </div>
    
     <!--输入验证码-->
    <div class="modalfom tipscode container">
        <h3>输入验证码</h3>
        <form  class="row">
            <div class="tips">您正在对<span class="userName">XXXX</span>进行重置密码操作，请先进行安全验证：</div>
            <div class="form-group">
                <label for="name">点击获取验证码，短信将发送至安全手机<span id="telephone">********191</span></label>
                <div class="row">
                	<input type="hidden" id="comid">
                	<input type="hidden" id="phone">
                    <div class="col-sm-8 col-xs-6"><input type="text" name="code" id="code" placeholder="输入验证码"></div>
                    <div class="col-sm-4 col-xs-4"><button type="button" id="createCode" class="btn btn-default">获得验证码</button></div>
                </div>
            </div>
            <p class="msg"></p>  
          
            <div class="row">
                <div class="col-sm-11">
                    <button type="button" id="but2" class="btn btn-primary btn-lg btn-block">下一步</button>                    
                </div>
            </div>
            <p> 若无法验证，请联系管理员 </p>
        </form>
    </div>   
    <!--设置新密码-->
    <div class="modalfom setpass container">
        <h3>设置新密码</h3>
        <form  class="row">
            <div class="form-group">
                <label >请设置<span class="userName">XXXX</span>的新密码，建议使用数字、字母、字符的组合密码，提高密码安全等级</label>
                 <div class="pas"><input type="password" class="form-control" name="password" id="password" placeholder="6-20位密码，区分大小写"></div>
                <div class="pas"><input type="password" class="form-control" name="password1" id="password1" placeholder="6-20位密码，区分大小写"></div>
            </div>
            <p class="msg"></p>  
            <div class="row">
                <div class="col-sm-11">
                    <button type="button" id="but3" class="btn btn-primary btn-lg btn-block">确 认</button>                    
                </div>
            </div>
        </form>
    </div>  
     <!--设置新密码-->
    <div class="modalfom success container">
        <h3>重置密码</h3>
        <div class="row">
            <div class="col-sm-12">
                <img src="img/success.png">
                <p>重置密码成功</p>
            </div>
            <div class="col-sm-12">
                    <button type="button" id="but4" class="btn btn-primary btn-lg btn-block">确 认</button>                    
            </div>
        </div>
    </div>  
    <!--底部-->
    <%@ include file="footer.jsp" %>
</body>
</html>