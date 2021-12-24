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
   <title>国际物流</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script>
        $(function(){
            //新闻类型切换触发
            $("#logistics li").click(function(){
                v_category=$(this).index()+1;
                //导航样式设计
                $("#logistics li a").css({backgroundColor:"",color:"",border:""});
                $(this).find("a").css({backgroundColor:"#3366CC",color:"white",border:"0px solid red"});
                //调用新闻请求
                mflowLoad(v_category);
            });
            //初始化新闻请求
            mflowLoad(1);
        });

        //新闻加载
        // moduleid 类型 1:模块一(海运), 2:模块二(陆运), 3:模块三(空运), 4:模块四(物流仓)
        function mflowLoad(moduleid) {
            $.ajax({
                type: "GET",
                url: "home/mflowById?moduleid=" + moduleid,
                dataType: "json",
                success: function (data) {
                    json_data = eval(data);
                    //console.log(json_data)
                    $("#mflowTitle").html(json_data.obj.title);
                    $("#mflowCon").html(json_data.obj.detail);

                }
            });
        }

        //在线询价
       // function qqzx(){
            // window.location.href="tencent://message/?uin="+$("#qqNo").val()+"&Menu=yes";
       // }
    </script>
</head>
<body>
  	<!--头部-->
    <%@ include file="header.jsp" %>
    <!--国际物流菜单-->
    <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic4.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
      <div class="menu"> 当前位置：<a href="index.jsp">首页</a> >> 国际物流  </div>     
    </div>
   
    <!--物流选择-->
    <div class="container log" id="partner">    
         <div  class="row" >  
            <div class="col-md-12">
                <ul class="nav nav-pills nav-justified" id="logistics">
                    <li><a href="javascript:void(0); " ></a></li>
                    <li><a href="javascript:void(0); "></a></li>
                    <li><a href="javascript:void(0); "></a></li>
                    <li><a href="javascript:void(0); "></a></li>
                </ul> 
            </div>  
            <div class="partner-wrap gj" > 
                <div class="partner text wl"> 
                    <h1 id="mflowTitle">海运介绍</h1>
                    <p id="mflowCon">海运介绍，平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。平台简介文字，包括介绍、联系地址、电话、微信号等等信息。</p>


                    <button class="btn1" onclick="qqzx();"><img src="img/qq.png"  width="28px" height="28px">在线询价</button>
                </div>


            </div>
        </div>
     </div>
     <!--底部-->
    <%@ include file="footer.jsp" %>
    <!--咨询菜单-->
    <%@ include file="contact.jsp" %>
</body>
</html>
