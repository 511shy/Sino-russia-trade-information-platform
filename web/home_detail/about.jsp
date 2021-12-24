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
    <title>关于我们</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script>
        $(function(){
            aboutLoad();
        });

        //关于我们加载
        function aboutLoad() {
            $.ajax({
                type: "GET",
                url: "home/dispaboutus",
                dataType: "json",
                success: function (data) {
                    json_data = eval(data);

                   // console.log(json_data);
                    $("#aboutTitle").html(json_data.obj.title);
                    $("#aboutCon").html(json_data.obj.detail);
                }
            });
        }
        
        $(function(){
    		//国际采购订单
    		$.ajax({
    			url:"home/queryOrderByCondition",
    			type:"get",
    			data:{
    				count:2,
    				flag:1
    			},
    			dataType:"json",
    			success:function(result){
    				if(result.message=="success"){
    					$.each(result.obj,function(key,order){
    						var str="<div class='col-md-12 col-sm-6 col-xs-6'>";
							str+="<div>";
							str+="<img src='homeImages/"+order.img1+"'  class='img-responsive'>";
							str+="<h4 onclick=\"window.location.href='home_detail/purchase_details.jsp?orderid="+order.orderid+"'\">"+order.orderNamech+"</h4>";
							str+="<p>"+order.orderNameen+"</p>";
							str+="<button>国际采购订单</button>";
							str+=" </div>";  
							str+=" </div>";  
			                $("#gjcgdd").append(str);
    					});
    				}
    			}
    		});
    		
    		//国际供应订单
    		$.ajax({
    			url:"home/queryOrderByCondition",
    			type:"get",
    			data:{
    				count:2,
    				flag:2
    			},
    			dataType:"json",
    			success:function(result){
    				if(result.message=="success"){
    					$.each(result.obj,function(key,order){
    						var str="<div class='col-md-12 col-sm-6 col-xs-6'>";
							str+="<div>";
							str+="<img src='homeImages/"+order.img1+"'  class='img-responsive'>";
							str+="<h4 onclick=\"window.location.href='home_detail/supply_details.jsp?orderid=" +order.orderid +"'\">"+order.orderNamech+"</h4>";
							str+="<p>"+order.orderNameen+"</p>";
							str+="<button>国际供应订单</button>";
							str+=" </div>";  
							str+=" </div>";  
			                $("#gjgydd").append(str);
    					});
    				}
    			}
    		});
    	});
    </script>
</head>
<body>
   	<!--头部-->
    <%@ include file="header.jsp" %>
    <!--关于我们菜单-->
    <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic5.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
      <div class="menu"> 当前位置：<a href="index.jsp">首页</a> >> 关于我们  </div>     
    </div>
     <!--关于我们-->
     <div class="container" id="new-wrap">
        <div class="row">           
            <div  class="col-md-9 information" id="partner">   
                <div class="row">   
                    <div class="col-md-4">
                        <h2 class="div-title">关于我们 <span>ABOUT US</span></h2>
                    </div>
                    <div class="col-md-8" >
   						 <%-- 
   						 <ul class="nav nav-pills nav-justified">
                            <li><a href="<%=basePath%>home_detail/register.jsp" >企业入驻</a></li>
                            <li><a href="<%=basePath%>home_detail/vipres.jsp">成为VIP企业</a></li>
                         </ul>
                         
                         --%>
 						<ul class="nav nav-pills nav-justified btn-group about"  id="newstype" role="group" aria-label="">
                            <li  class="btn btn-default" onclick="window.location.href='<%=basePath%>home_detail/register.jsp'">企业入驻</li>
                            <li  class="btn btn-default" onclick="window.location.href='<%=basePath%>home_detail/vipres.jsp'">成为VIP企业</li>
                        </ul>
                    </div> 
                    
                </div>
                <div class="about-text content1">
                    <h2 id="aboutTitle"> 中俄贸易供需服务平台</h2>
                    <P class="paragraph" id="aboutCon"></P>


                </div>
            </div>
            <div class="col-md-3 aside">               
                <div>
                    <h2 class="aside-title"> <img src="img/font3.png" /></h2>
                    <div class="row good1 good" id="gjcgdd">
                    </div>
                    <div class="row good2 good" id="gjgydd">
                    </div>
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
