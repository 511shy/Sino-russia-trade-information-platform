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
    <title>中俄贸易供需服务平台-首页</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">  
    <script src="admin/js/jquery.min.js"></script>
    
    <script>
    	$(function(){
    		$.ajax({
    			url:"admin/indexCompList",
    			cache:false,
    			dataType:"json",
    			success:function(result){
    				var map=result.obj;
    				var list1=map["1"];
    				var list2=map["2"];
    				var list3=map["3"];
    				
    				var str1="";
    				var str2="";
    				var str3="";
    				
    				for(var i=0;i<list1.length;i++){
	   					var c=list1[i];
	   					str1+= "<div class='col-md-3 col-sm-6 col-xs-6'>";
	   					str1+= "<img src='"+c.mainImage+"' class='img-responsive'>";	
	   					str1+= "<h5 title='"+c.comName+"'>"
	   					str1+= c.shortComName;  
	   					str1+="</h5>";
	   					str1+= "</div>";
	   				}
	   				for(var i=0;i<list2.length;i++){
	   					var c=list2[i];
	   					str2+= "<div class='col-md-3 col-sm-6 col-xs-6'>";
	   					str2+= "<img src='"+c.mainImage+"' class='img-responsive'>";
	   					str2+= "<h5 title='"+c.comName+"'>"
	   					str2+= c.shortComName;  
	   					str2+="</h5>";
		   				str2+= "</div>";
	   				}
	   				for(var i=0;i<list3.length;i++){
	   					var c=list3[i];
	   					str3+= "<div class='col-md-3 col-sm-6 col-xs-6'>";
	   					str3+= "<img src='"+c.mainImage+"' class='img-responsive'>";
	   					str3+= "<h5 title='"+c.comName+"'>"
	   					str3+= c.shortComName;  
	   					str3+="</h5>";
		   				str3+= "</div>";
	   				}
		
	   				$("#div_com1").append(str1);
	   				$("#div_com2").append(str2);
	   				$("#div_com3").append(str3);
    			}
    		
    		});
    	});
    </script>
</head>
<body>

    <!--头部-->
     <%@ include file="home_detail/header.jsp" %>
    <!--轮播图-->
   <div id="myCarousel" class="carousel slide">
        <!-- 轮播（Carousel）指标 -->
         <ol class="carousel-indicators"> </ol> 
        <!-- 轮播（Carousel）项目 -->
        <div class="carousel-inner"> </div>
        <!-- 轮播（Carousel）导航 -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"> </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"></a>
    </div> 
    
    <!--平台简介-->
    <div class="container" id="platform">
        <div class="row">
            <div class="col-md-6">
                <img id="pic" src="img/info.jpg"  class="img-responsive">
            </div>
            <div class="col-md-6">
                <h2 class="div-title" >平台简介 <span>ABOUT US</span></h2>
                <p id="aboutCon"></p>
                <button type="button" onclick="javascript:location.href='home_detail/about.jsp'">查看详情</button></div>           
        </div>
    </div>
    <div class="position">
    <!--服务内容-->
    <div id="service">
        <div class="container" >
            <div class="row">                
                <div class="col-md-2  col-sm-6 col-xs-6">
                    <object data="img/img1.png" width="200" height="336"></object>                   
                </div>
                <div class="col-md-2 col-sm-6 col-xs-6">
                     <object data="img/img2.png" width="200" height="336"></object>                     
                </div>
                <div class="col-md-2 col-sm-4  col-xs-6">
                     <object data="img/img3.png" width="200" height="336"></object>                    
                </div>
                <div class="col-md-2 col-sm-4  col-xs-6">
                     <object data="img/img4.png" width="200" height="336"></object>                
                </div>     
                <div class="col-md-2 col-sm-4  col-xs-6">
                     <object data="img/img5.png" width="200" height="336"></object>                    
                </div>
                  <div class="col-md-2 col-sm-4  col-xs-6">
                    <object data="img/img6.png" width="200" height="336"></object>                     
                </div>
                
            </div>
        </div>
    </div>
    <!--供需信息-->
    <div class="container"  id="goods">       
        <h2 class="div-title">供需信息 <span>PICK OF THE WEEK</span></h2>
        <div id="gjcgdd" class="row good1 good">
      
        </div>
        <div id="gjgydd" class="row good2 good">
		</div>
    </div>
    </div>
    
    <!--合作伙伴-->
    <div class="container tabs" id="partner">   
        <div class="row">   
            <div class="col-md-5 ">
                <h2 class="div-title">合作伙伴 <span>COOPERATIVE PARTHER</span></h2>
            </div>
            <div class="col-md-7">
                <ul class="nav nav-pills nav-justified btn-group" role="group" aria-label="">
                    <li class="btn btn-default">中国入驻企业</li>
                    <li class="btn btn-default">俄罗斯入驻企业</li>
                    <li class="btn btn-default">事业合作企业</li>
                </ul>
            </div> 
        </div>
        <div class="partner-wrap"> 
            <div class="row partner tab text" id="div_com1">               
            </div>
            <div class="row partner tab" id="div_com2">                               
            </div>
            <div class="row partner tab"  id="div_com3">                
            </div>
        </div>
    </div>
    <!--底部-->
    <%@ include file="home_detail/footer.jsp" %>
    <!--咨询菜单-->
    <%@ include file="home_detail/contact.jsp" %>
     
</body>
 <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script> 
    <script>   
    $(document).ready(function(){
    	//轮播图加载
    	function bannerslide(){ 
            $.ajax({
                type:"GET",
                url:"home/queryHomeInfoByContentType?contentType=1",
                dataType:"json",
                async:false,
                success:function(data){   
                	json_data=eval(data);
                	//console.log(json_data.message);                	                	
                	$.each(json_data.obj, function(i, item) {
                		//i key值，item value值
					    //图片轮播点
					    $(".carousel-indicators").append(
					    			" <li data-target='#myCarousel' data-slide-to='"
					    			+i
					    			+"'></li>");					    	
					    //轮播图片
					    $(".carousel-inner").append("<div class='item'>"	
					    			+ "<img src="+item.imageUrl+">" 
					    			+ "</div>");
					    //对首个元素添加激活样式
					    $(".carousel-indicators li:first").addClass("active");
					    $(".carousel-inner div:first").addClass("active");                		
					}); 
                 },
                error:function(jqXHR){
                    console.log("Error: "+jqXHR.status);
                }
            });   
          //轮播图自动播放
          $("#myCarousel").carousel('cycle');
    	}
    	bannerslide();

    	//关于我们
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
                $("#pic").attr("src", json_data.obj.pic);
                $("#aboutCon").html(json_data.obj.intro);
            }
        });
    }
    
    $(function(){
		//1 代表采购信息
		$.ajax({
			url:"home/queryOrderByCondition",
			type:"get",
			dataType:"json",
			data:{
				count:4,
				flag:1
			},
			success:function(result){
				if(result.message=="success"){
					$.each(result.obj,function(key,order){
						var str="<div class='col-md-3 col-sm-6 col-xs-6'>";
						str+="<div>";
						str+="<img src='homeImages/"+order.img1+"'  class='img-responsive' >";
						str+="<h4 onclick=\"window.location.href='home_detail/purchase_details.jsp?orderid="+order.orderid+"'\">"+order.orderNamech+"</h4>";
						str+="<p>"+order.orderNameen+"</p>";
						str+="<button onclick=\"window.location.href='home_detail/purchase.jsp'\">国际采购订单</button>";
						str+=" </div>";  
						str+=" </div>"; 
		                $("#gjcgdd").append(str);
					});
				}
			}
		});
		
		
		//2代表供应订单
		$.ajax({
			url:"home/queryOrderByCondition",
			type:"get",
			dataType:"json",
			data:{
				count:4,
				flag:2
			},
			success:function(result){
				if(result.message=="success"){
					$.each(result.obj,function(key,order){
						var str="<div class='col-md-3 col-sm-6 col-xs-6'>";
						str+="<div>";
						str+="<img src='homeImages/"+order.img1+"'  class='img-responsive' >";
						str+="<h4 onclick=\"window.location.href='home_detail/supply_details.jsp?orderid=" +order.orderid +"'\">"+order.orderNamech+"</h4>";
						str+="<p>"+order.orderNameen+"</p>";
						str+="<button onclick=\"window.location.href='home_detail/supply.jsp'\">国际供应订单</button>";
						str+=" </div>";  
						str+=" </div>";  
		                $("#gjgydd").append(str);
					});
				}
			}
		});
    	
    });
    </script>  
</html>