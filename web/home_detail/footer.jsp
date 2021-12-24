<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>底部</title>
	<script type="text/javascript">
		$(function(){
			//页脚信息展示
			$.ajax({
				url:"home/queryPagefooter",
				type:"get",
				dataType:"json",
				success:function(result){
					if(result.message=="success"){
						$.each(result.obj,function(key,homeInfo){
							if(homeInfo.contentType==3){
								$(".wximage").attr("src",homeInfo.imageUrl);
							}
							if(homeInfo.contentType==4){
								$(".whatsupimage").attr("src",homeInfo.imageUrl);
							}
							if(homeInfo.contentType==2){
								$("#qqNo").val(homeInfo.content);
							}
							
						});
					}
				}
			});
		});
	</script>
</head>
<body>
    <div id="footer">
    <div class="container" > 
        <div class="row">
            <div class="col-md-6 bth">
               <div class="row">
                <div class="col-md-6 ph">
                    <img class="wximage" src="img/wei.png" >
                    <p>微信在线客服</p>
                </div>
                <div class="col-md-6 ph">
                    <img class="whatsupimage" src="img/wei.png" >
                    <p>移动端网站（手机）</p>
                </div>
                <input type="hidden" id="qqNo"/>                   
               </div>
            </div>
            <div class="col-md-6 about">
                <h4>联系我们 <span>Contact</span> </h4>
                <div class="row">
                    <div class="col-md-6">
                        <p>地址：黑龙江省哈尔滨市西大直街XXXX号</p>
                        <p>邮编：150000</p>
                        <p>电话：0451-88886666</p>
                    </div>
                    <div class="col-md-6">             
                        <p>手机：18088886666 / 18088886666</p>
                        <p>传真：0451-88886666</p>
                        <p> 邮箱：XXXXXXX@163.com</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="bottom">
        <p>
            <span>CopyRight © 2021</span>    
            <span>中俄贸易供需服务平台</span>       
            <span>XXXX.com All Rights Reserved</span>        
            <span>备案：ICP备8888888号</span> 
        </p>
    </div>
</div>
   
</body>
</html>
