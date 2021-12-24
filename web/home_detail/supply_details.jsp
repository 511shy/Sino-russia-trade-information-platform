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
<title>国际供应详情</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/base.css">
    <script src="js/jquery-1.10.2.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/base.js"></script>
</head>
<body>
    <!--头部-->
    <%@ include file="header.jsp" %>
    <!--国际供应菜单-->   
      <div class="containerbox">
        <div class="container navmenu">
        <div class="jumbotron">
             <img src="img/pic3.jpg" class="img-responsive"/>         
        </div>                                          
     </div>
       <div class="menu"> 当前位置：<a href="index.jsp">首页</a> >> <a href="home_detail/supply.jsp">国际供应</a> >> 国际供应详情页</div> 
    </div>
     <!--国际采购详情-->
     <div class="container" >
        <div class="row">           
            <div  class="col-md-9" id="purchase">   
                <div class="row purchase-title" >   
                    <div class="col-md-12 detail-title">
                        <h2 id="orderNamech" > 电热产品、采暖设备设施的研发、制造及销售</h2>
                        <h3 id="orderNameen" >English name of imported goods</h3>
                        <p><span id="publishtime" >发布日期：2021-01-05</span>　　　　　
                            <span id="businessName" >行业分类：轻工业类</span>　</p>
                    </div>                   
                </div>
                <div class="content-wrap" > 
                    <div class="row img-box">
                        <div class="col-md-9 big">
                            <img id="img0" src="img/info.jpg"  class="img-responsive">
                        </div>
                        <div class="col-md-3 row small">
                                <div class="col-md-12 col-sm-4 col-xs-4"><img id="img1"  src="img/info.jpg"  class="img-responsive"></div>
                                <div class="col-md-12 col-sm-4 col-xs-4"><img id="img2"  src="img/order1.png"  class="img-responsive"></div>
                                <div class="col-md-12 col-sm-4 col-xs-4"><img id="img3"  src="img/order3.png"  class="img-responsive"></div>
                        </div>
                    </div>
                    <div class="hotspot">
                        <p id="clickcount"  class="pot">点击量：88888</p>
                        <p id="tradeflag"  class="state1">订单状态：已完成</p>
                        <p><button class="btn1" onclick="qqzx();"><img src="img/qq.png"  width="28px" height="28px">在线询价</button></p>
             
                    </div>
                    <div id="details"  class="content1">
                     <!-- 
                        <p>以下内容为管理员编辑录入，图文形式</p>
                        <p>采购商品的详细介绍，可以采用图文方式，具体内容由管理员自行编辑。</p>
                        <p><span>商品数量：10000件</span><span>商品类型：类型</span></p>
                        <p><span>商品规格：100KG</span><span>供应时间：2020年5月</span></p>
                        <p class="paragraph">
                            吉尔吉斯斯坦蜂蜜被称为是世界上最好的蜂蜜，而在吉尔吉斯斯坦国内把最顶级的蜂蜜称为吉斯缪德(吉尔吉斯斯坦蜂蜜俄语的音译)，早在2013年国内多家知名媒体报道，吉斯缪德在世界性大赛中曾屡次拿下最高奖项。吉斯缪德参加在基辅举行的第43届国际蜂联展大会上战胜了超过1.2万名参会的各国代表，其中包含中国和新西兰等在内的蜂产品大国，一举斩获三金两银。
                        </p>
                        <p  class="paragraph">
                            在2015年吉斯缪德又再次在韩国获得第44届国际蜂联展金奖。一个个赛事、一个个金奖的见证，吉斯缪德作为世界上最好的蜂蜜名至实归。
                        </p>
                        <p><img src="img/order4.png">
                            <img src="img/order4.png"></p>
                        <p  class="paragraph">
                            　吉尔吉斯坦风景优美，自然资源丰富，空气清新，水源纯净，是全球极少数环境没有被污染的国家之一。吉尔吉斯斯坦拥有众多全球著名的野生动植物自然保护区，其养蜂基地正是建设于这些地区。自然保护区内长着茂密的原始森林、漫山遍野的花草及各种名贵中草药。境内生长着3786种植物，其中草本植物3175种，约1600种具有经济价值，绝大多数具有药用价值，而且这里拥有世界上最大的野生核桃林和野苹果林。这种原始的自然环境为吉斯缪德成为世界上最好的蜂蜜创造了良好的基础。
                        </p>
                        <p  class="paragraph">
                            蜂蜜种类：阿特巴世蜂蜜， 驴食草蜂蜜 ，荞麦蜜，乌兹根蜂蜜等特色吉尔吉斯坦山区蜂蜜，花粉，蜂王浆，蜂胶，喜来芝等多种蜂蜜产品。
                        </p>
                        <span>采购合作业务请联系：13028597171 - Vera</span>
                        <p><img src="img/order4.png">
                        <img src="img/order4.png"></p>
                        -->
                    </div>
                     <div class="ordinary" id="normaluser" style="display:none;">
                     
                    	<h2>请升级企业<img src="img/vip.jpg" width="50px" height="26px" >查看订单详细信息</h2>
						<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='home_detail/vipres.jsp'">我要升级vip</button>
                    </div>              
                     <div class="ordinary" id="anonymous" style="display:none;">
                     
                    	<h2>注册或登录查看订单详细信息</h2>
						<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='home_detail/register.jsp'">注册</button>
						<button type="button" class="btn btn-primary btn-lg" onclick="window.location.href='home_detail/login.jsp'">登录</button>

                    </div>              


                </div>
            </div>
            <div class="col-md-3 aside">
                  <%@ include file="supply_details_classify.jsp" %>
            </div>
        </div>
     </div>
    <!--底部-->
    <%@ include file="footer.jsp" %>
    <!--咨询菜单-->
    <%@ include file="contact.jsp" %>
<script type="text/javascript">
//向模板填充数据
function displayDetails(order){
	$("#orderNamech").html(order.orderNamech); //chinese name of imported goods
	$("#orderNameen").html(order.orderNameen); //English name of imported goods
	$("#publishtime").html("发布日期："+order.publishtime); //发布日期：2021-01-05
	$("#businessName").html("行业分类："+order.businessName); //行业分类：轻工业类
	$("#img0").attr("src","homeImages/"+order.img1); //img1
	$("#img1").attr("src","homeImages/"+order.img1); //img1
	$("#img2").attr("src","homeImages/"+order.img2); //img2
	$("#img3").attr("src","homeImages/"+order.img3); //img3
	$("#clickcount").html("点击量："+order.clickcount); //点击量：88888
	var trade="进行中";
	$("#tradeflag").attr("class","state1");
	if(order.tradeflag == "1"){
		trade="已完成";
		$("#tradeflag").attr("class","state");
	}
	//if(order.tradeflag=="0"){ //如果是进行中，则有qq，否则没有
	//	$(".hotspot").append("<p><button class='btn1'><img src='img/qq.png' width='28px' height='28px'>在线询价</button></p>");
	//}
	$("#tradeflag").html("订单状态："+trade); //订单状态：已完成
	 //详细内容
	$("#details").html(order.details);
	
}
//获得ajax请求数据,生成列表html
function createOrder(url){
	
	$.ajax({
		url:url,
		type:"get",
		dataType:"json",
		success:function(result){
			if(result.message=="success"){
				// vip admin
				if(result.status==3){
					$("#anonymous").attr("style","display:none;");//隐藏div
					$("#normaluser").attr("style","display:none;");//隐藏div
					$("#details").attr("style","display:block;");
					
				}
				if(result.status==1){
					$("#anonymous").attr("style","display:none;");//隐藏div
					$("#details").attr("style","display:none;");//隐藏div
					$("#normaluser").attr("style","display:block;");
				}
				if(result.status==0){
					$("#normaluser").attr("style","display:none;");//隐藏div
					$("#details").attr("style","display:none;");//隐藏div
					$("#anonymous").attr("style","display:block;");
				}
				displayDetails(result.obj);
			}
		}
	});

}
//获取请求参数orderid
function initDetailsPage(){
	var paramsStr=window.location.search.substring(1);
	
	var url="home/homeSupplyOrderByid"+'?'+paramsStr;
	//alert(url);
	createOrder(url)
}


//初始化页面
	$(function(){
	
		initDetailsPage();

	});

</script>   
</body>
</html>