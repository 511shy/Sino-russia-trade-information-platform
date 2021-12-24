<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<base href="<%=basePath%>">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新闻资讯详情页</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script>
    $(function(){
    	//获取news传参(新闻id)    
        var searchURL = window.location.search;
        searchURL = searchURL.substring(1, searchURL.length);
        var newId = searchURL.split("&")[0].split("=")[1];
        
        $.ajax({
            type:"GET",
            url:"home/showNewsById?id="+newId,
            dataType:"json",
            success:function(data){   
            	json_data=eval(data);
            	console.log(json_data);
            	
            	//新闻标题
            	$("#new-wrap .new-title h2").append(json_data.obj.title);
            	//<p>发布时间：2021/01/07 发布人：管理员</p>
            	$("#new-wrap .new-title p").append(
            			"发布时间:"
            			+json_data.obj.publish
            			+"  发布人:"+json_data.obj.writer);
            	
            	//新闻内容
            	$("#new-wrap .news-content").append(json_data.obj.content);
             	
            }
        }); 
        
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
						str+="<h4 onclick=\"window.location.href='home_detail/purchase_details.jsp?orderid=" +order.orderid +"'\">"+order.orderNamech+"</h4>";
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
    <!--新闻资讯菜单-->   
      <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic1.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
      <div class="menu">当前位置：<a href="index.jsp">首页</a> >> <a href="home_detail/news.jsp">新闻资讯</a> >> 新闻详情页</div>  
    </div>
        <!--新闻资讯-->
     <div class="container" id="new-wrap">
        <div class="row">           
            <div  class="col-md-9 information" >   
               <div class="new-title">
                    <h2></h2>
                    <p></p>
               </div>
               <div class="news-content"></div>
             </div>
            <div class="col-md-3 aside">
                <div class="helper">
                     <h2 class="aside-title"> <img src="img/font1.png" /></h2>
                    <ul class="nav">
                        <li><a href="#">国际快递查询</a></li>
                        <li><a href="#">海关业务查询</a></li>
                        <li><a href="#">每日外汇牌价查询</a></li>
                        <li><a href="#">出口退税率查询</a></li>
                        <li><a href="#">世界时间查询</a></li>
                        <li><a href="#">全球汇率换算器</a></li>
                    </ul>
                </div>
                <div>
                   <h2 class="aside-title"> <img src="img/font3.png" /></h2>
                    <div id="gjcgdd" class="row good1 good">
                    </div>
                    <div id="gjgydd" class="row good2 good">
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