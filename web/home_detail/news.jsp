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
    <title>新闻资讯</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
    <script>   
    $(function(){
    	//新闻类型切换触发
    	$("#newstype li").click(function(){
    		v_category=$(this).index()+1;
    		//导航样式设计
    		$("#newstype li").css({backgroundColor:"",color:"",border:""});
    		$(this).css({backgroundColor:"#3366CC",color:"white",border:"0px solid red"}); 
    		//调用新闻请求
    		newsLoad(1,v_category);
    	});
        //初始化新闻请求和样式
        $("#newstype li:first").css({backgroundColor:"#3366CC",color:"white",border:"0px solid red"}); 
        newsLoad(1,1);
        //侧栏外贸助手
        showAssitants();
    });
    
	//新闻加载
	//arg：pn页数
	//     category 类型   1 国内市场新闻   2国际市场新闻  3国际贸易新闻
	function newsLoad(pn,category){ 
	    $.ajax({
            type:"GET",
            url:"home/news?pn="+pn+"&category="+category,
            dataType:"json",
            success:function(data){   
            	json_data=eval(data);
            	//移除标签内容
        		$("#partner_content div[class='row font']").remove();
        		$("#partner_content #partner_row").empty();
        		$("#partner_content .pagelist ul").empty();
            	
            	//新闻
            	$.each(json_data.obj.list, function(i, item) {  
            		console.log(i+"   "+item);
            		//新闻介绍字符长度截取，超过80省略号
        			s_intro="";
        			if (parseInt(i)<2){
        				if(item.intro.length>80){
            				s_intro=item.intro.substring(0,80)+"...";
            			}
                		//二个大新闻
                		 $("#partner_content").prepend(
                				 '<div class="row font" ><div class="col-md-6">'
                				 +'<img src="'+ item.msgpic+'"   class="img-responsive" width="392px" height="242px">' 
                				 +'</div><div class="col-md-6">'
                				 +'<h4 onclick="javascript:location.href=\'home_detail/detail.jsp?id='+item.id+'\'">'+item.title+'</h4>'
                				 +'<span>'+item.publish+'</span>'
                				 +'<p>'+s_intro+'</p>'
                				 +'<button type="button" class="btn btn-primary" '
                				 +'onclick="javascript:location.href=\''+"home_detail/detail.jsp?id="+item.id+'\'">详细信息</button>'
                				 +'</div> </div>');
                		
                	}else{
                		if(item.intro.length>170){
            				s_intro=item.intro.substring(0,170)+"...";
            			}
                		//多个小新闻
              		    $("#partner_content #partner_row").append(
                				'<div class="col-md-12">'
                				+'<h4><a href="javascript:location.href=\'home_detail/detail.jsp?id='+item.id+'\'">'+item.title+'</a></h4>'
                				+'<span>'+item.publish+'</span>'
                				+'<p>'+s_intro+'</p>'
                				+'<button type="button" class="btn btn-primary" '
                				+'onclick="javascript:location.href=\''+"home_detail/detail.jsp?id="+item.id+'\'">详细信息</button>'
                				+'</div>'
                		);
                	}
                });
            	
            	//分页
            	pageFun(json_data,category);
            }	            	
        });
	}
	
	//分页函数
	function pageFun(json_data,category){
		pageinfo="";
		//总页数
     	pagenums=parseInt(json_data.obj.pages);
		//当前页
     	pagecurrent=parseInt(json_data.obj.pageNum); 
		//可视页数
		showpages=13;
		
     	if(pagecurrent>1){
     		pageinfo+="<li><a href='javascript:void(0);' onclick='newsLoad("+(pagecurrent-1)+","+category+")'> < </a></li>";
     	}else{
     		pageinfo+="<li><a href='javascript:void(0);'> < </a></li>";
     	}     	
     	for_begin=1;
     	for_judge=1;
     	pageinf_begin="";
     	pageinf_end="";
     	if(pagenums>0){
	     	if(pagenums<=showpages){ 
	     		for_begin=1;
	     		for_judge=pagenums;
	     		pageinf_begin="";
	     		pageinf_end="";
	     	}
	     	else if(pagenums>showpages && pagecurrent==1){
     		  for_begin=1;
	     	  for_judge=showpages;
	     	  pageinf_begin="";
	     	  pageinf_end="<li><a>...</a></li>";     		   
     	   }
	     	else if(pagenums<=(pagecurrent+showpages) && pagecurrent>1){
     		  for_begin=pagecurrent;
	     	  for_judge=pagenums;
	     	  pageinf_begin="<li><a>...</a></li>";
	     	  pageinf_end="";
     	   }
	     	else if(pagenums>(pagecurrent+showpages) && pagecurrent>1){
     		  for_begin=pagecurrent;
	     	  for_judge=pagecurrent+showpages-1;
	     	  pageinf_begin="<li><a>...</a></li>";
	     	  pageinf_end="<li><a>...</a></li>";
     	   }
     	   
     	  pageinfo+=pageinf_begin; 
     	  for(i=for_begin;i<=for_judge;i++){
   			if(i==pagecurrent){
   				pageinfo+="<li  class='active'><a href='javascript:void(0);' onclick='newsLoad("+i+","+category+")'>"+i+"</a></li>";
   			}else{
   				pageinfo+="<li><a href='javascript:void(0);' onclick='newsLoad("+i+","+category+")'>"+i+"</a></li>";
   			}
   	      }  
     	  pageinfo+=pageinf_end;
     	
     	 if(pagecurrent<pagenums){
     		pageinfo+="<li><a href='javascript:newsLoad("+(pagecurrent+1)+","+category+")'> > </a></li>";
     	 }else{
     		pageinfo+="<li><a href='javascript:void(0);'> > </a></li>";
     	 }            	
     	 $("#partner_content .pagelist ul").append( pageinfo );
     	 }
	}
	
	//侧栏外贸助手
	function showAssitants(){
		 $.ajax({
	            type:"POST",
	            url:"home/showAssitants",
	            dataType:"json",
	            success:function(data){   
	            	json_data=eval(data);  
	            	console.log(json_data);
	              	//新闻
	            	$.each(json_data.obj, function(i, item) {  	            		
	            		$("#nav_assitant").append(
	            		  "<li><a href='"+item.address+"' target='_blank'>"+item.aname+"</a></li>"		
	            		);            	   
	                });
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
    <!--新闻资讯菜单-->
   <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic1.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
      <div class="menu"> 当前位置：<a href="index.jsp">首页</a> >> 新闻咨询  </div>     
    </div>
    <!--新闻资讯-->
    <div class="container" id="new-wrap">
        <div class="row information">           
            <div  class="col-md-9 " id="partner">   
                <div class="row ">   
                    <div class="col-md-4 ">
                        <h2 class="div-title">新闻资讯 <span>NEWS</span></h2>
                    </div>
                    <div class="col-md-8" >
                        <ul class="nav nav-pills nav-justified btn-group"  id="newstype" role="group" aria-label="">
                            <li  class="btn btn-default">国内市场动态</li>
                            <li  class="btn btn-default">国际市场动态</li>
                            <li  class="btn btn-default">国际贸易最新政策</li>
                        </ul>
                    </div> 
                </div>
               <div class="partner-wrap" id="news"> 
                    <div class="partner text" id="partner_content">           
	                    <div class="row font1" id="partner_row"></div>
	                    <div class="pagelist">
	                         <ul class="nav  nav-pills"></ul>
	                    </div> 
                    </div>
                </div>
             </div>    
            <div class="col-md-3 aside">
                <div class="helper">
                    <h2 class="aside-title"> <img src="img/font1.png" /></h2>
                    <ul class="nav" id="nav_assitant">
                      <!--   <li><a href="#">国际快递查询</a></li>
                        <li><a href="#">海关业务查询</a></li>
                        <li><a href="#">每日外汇牌价查询</a></li>
                        <li><a href="#">出口退税率查询</a></li>
                        <li><a href="#">世界时间查询</a></li>
                        <li><a href="#">全球汇率换算器</a></li> -->
                    </ul>
                </div>
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
