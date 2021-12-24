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
    <title>国际供应</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
</head>
<body>
  <%@ include file="header.jsp" %>
    <!-- 国际采购菜单-->
     <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic3.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
      <div class="menu"> 当前位置：<a href="index.jsp">首页</a> >> <a href="home_detail/supply.jsp">国际供应</a> <span id="businessName"></span></div>     
    </div>
     <!--国际采购订单-->
     <div class="container" >
        <div class="row">           
            <div  class="col-md-9" id="purchase">   
                <div class="row purchase-title" >   
                    <div class="col-md-12 ">
                        <h2 class="div-title"> 国际供应 <span>IMPORT SUPPLY</span></h2>
                    </div>                   
                </div>
                <div class="order" id="orderlist"> 
                <!-- 
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="row">     
                        <div class="col-sm-4" >
                            <img src="img/info.jpg"  class="img-responsive">
                        </div>  
                        <div class="col-sm-8">
                            <h4><a href="<%=basePath%>home_detail/supply_details.jsp">国际供应订单中文名称</a></h4>
                            <span>English name of imported goods</span>
                            <p>中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液、车用尿素、汽车养护品等产品。中外合资企业，所生产的A9 系列高级润滑油涵盖汽车润滑油、工业用油、润滑脂、防冻液。</p>
                            <div class="deail">
                                <p class="date">2021-01-05</p>
                                <p class="state1">订单状态：进行中</p>
                                <p><object data="img/ched.svg" width="19" height="16"  ></object>
                                    轻工业类</p>                               
                            </div>

                        </div>                                                      
                    </div>
                    <div class="pagelist">
                        <ul class="nav  nav-pills">
                            <li><a href="#"> < </a></li>
                            <li class="active"><a href="#">1</a></li>
                            <li><a href="#">2</a></li>
                            <li><a href="#">3</a></li>
                            <li><a href="#">4</a></li>
                            <li><a href="#">5</a></li>
                            <li><a href="#"> > </a></li>
                        </ul>
                    </div>
                     -->
                </div> <!-- id="orderlist" -->
                
            </div>  <!-- id="purchase" -->
            <div class="col-md-3 aside" >
                <%@ include file="supply_classify.jsp" %> 
            </div>
        </div>
     </div>
	<!--底部-->
    <%@ include file="footer.jsp" %>
    <!--咨询菜单-->
    <%@ include file="contact.jsp" %>
<script>
//保存查询条件行业类型
var g_businessId;
//按模板填充数据
function getRowHtml(order){
	var trade =  "进行中";
	var className="state1";
	if(order.tradeflag == "1"){
		trade =  "已完成";
		className="state";
	}
		var strRow  ='<div class="row">'     
					+'    <div class="col-sm-4" >'
					+'        <img src="homeImages/'+ order.img1  +'"  class="img-responsive">'
					+'    </div>'  
					+'    <div class="col-sm-8">'
					+'        <h4><a href="<%=basePath%>home_detail/supply_details.jsp?orderid='+order.orderid+'">'
					+         order.orderNamech +'</a></h4>'
					+'        <span>'+ order.orderNameen +'</span>'
					+'        <p>'+ order.summary +'</p>'
					+'        <div class="deail">'
					+'            <p class="date">'+ order.publishtime +'</p>'
					+'            <p class='+className+'>订单状态：'+ trade +'</p>'
					+'            <p><object data="img/ched.svg" width="19" height="16"  ></object>'
					+'                '+ order.businessName +'</p>'                               
					+'        </div>'

					+'    </div>'                                                     
					+'</div>';
		return strRow;
		
	}
//创建分页工具栏	
function createPageBar(obj){
	
	strBar =
    ' <div class="pagelist">'
    +'    <ul class="nav  nav-pills">'
    
    +'         <li><a href="#" onclick="displaySupplyList('+ (obj.pageNum-1) +');return false;"> < </a></li>';
	if(obj.navigatepageNums){
		for(i=0;i<obj.navigatepageNums.length;i++){
			var tempactive = "";
			if((obj.navigatepageNums[i])== obj.pageNum){
				tempactive ="class=active";
			}
			strBar = strBar + ' <li '+ tempactive +'><a href="#" onclick="displaySupplyList('+ (obj.navigatepageNums[i]) +');return false;">'+ obj.navigatepageNums[i] +'</a></li>';
		}
	}
    strBar = strBar
    +'         <li><a href="#" onclick="displaySupplyList('+ (obj.pageNum+1) +');return false;"> > </a></li>'
    +'     </ul>'
    +'  </div>';
	
	return strBar;
}

function getQueryParam(paramName)
{
	//alert("获得请求参数");
	   var query =decodeURI(window.location.search).substring(1);
	  
	   var vars = query.split("&");
	   for (var i=0;i<vars.length;i++) {
			   var pair = vars[i].split("=");
			   if(pair[0] == paramName){
				   return pair[1];
			   }
	   }
	   return(false);
}
//获得ajax请求数据,生成列表html
function createList(url,method,id){
	var listHtml = "";
	$.ajax({
		url:url,
		type:method,
		dataType:"json",
		success:function(result){
			if(result.message=="success"){
				//解析ajax响应结果，填充模板
				$.each(result.obj.list,function(key,order){
					listHtml = listHtml + getRowHtml(order);
				});
				listHtml = listHtml + createPageBar(result.obj);
				//使用模板渲染页面节点
				$(id).html(listHtml);
			}
		}
	});

}

function displaySupplyList(pageNum){
	//按页码查询。默认第一页
	params = "?";
	if(!pageNum){
		pageNum="1";
	}
	//判断是否按行业查询
	var businessid = getQueryParam("businessid");
	//alert("businessid  "+businessid);
	if(businessid){
		g_businessId = businessid;
	}
	params = params + "pageNum="+pageNum;
	if(g_businessId){
		params = params + "&businessid="+g_businessId;
	}
	var url="home/homeSupplyOrderList"+params;
	//alert(url);
	var method="GET";
	var id = "#orderlist";
	
	createList(url,method,id)
}

//按行业查询订单列表  本页按行业查询
function findOrdersByBusinessId(businessid){
	
	g_businessId = businessid;
	displaySupplyList(1);
	
}


//初始化页面
	$(function(){
	
		displaySupplyList(1);
		
		//获取行业分类名称
		var businessName = getQueryParam("businessName");
		if(businessName!=false){
			$("#businessName").html(">> "+businessName);
		}
	});

</script>
</body>
</html>
