<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
  	
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>咨询菜单</title>
    <script type="text/javascript">
    	/*function qqzx(){
    		window.location.href="tencent://message/?uin="+$("#qqNo").val()+"&Menu=yes";
    	}*/
    </script>
  
</head>
<body style="background:transparent;">
    <div class="contact">   
    	<div class="scrolltop host">
            <img src="img/touch1.png" width="30px" height="30px" class="hidden-xs">
            <p>返回顶部</p>
        </div>   
        <div class="follow host">
            <img src="img/touch2.png" width="31px" height="25px" class="hidden-xs">
            <p>微信客服</p>
            <div class="elevator ">
                <h5>即时通讯</h5>
                <p class="hidden-xs">1v1客服即时沟通</p>
                <img class="wximage" src="img/wei.png" >
                <p class="hidden-xs">微信扫一扫关注</p>
                <p class="hidden-xs">或搜素关注：中俄贸易供需服务平台</p>
            </div>
        </div>
        <div class="host hidden-xs" onclick="qqzx();">
            <img src="img/touch3.png" width="31px" height="25px">
            <p>QQ咨询</p>
            <input type="hidden" id="qqNo"/>
        </div>
        <div class="follow host">
            <img src="img/touch4.png" width="31px" height="25px" class="hidden-xs">
            <p>What'sup</p>
            <div class="elevator">
                <h5>What'sup</h5>
                <p class="hidden-xs">1v1客服即时沟通</p>
                <img class="whatsupimage" src="img/wei.png" >
                <p class="hidden-xs">What'sup扫一扫关注</p>
                <p class="hidden-xs">或搜素关注：中俄贸易供需服务平台</p>
            </div>
        </div>
        
    </div>
   
</body>
</html>