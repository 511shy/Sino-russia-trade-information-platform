<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>行业分类</title>
 	<script type="text/javascript">
    	$(function(){
    		//行业类别
    		$.ajax({
    			url:"home/queryBusiness",
    			type:"get",
    			dataType:"json",
    			success:function(result){
    				if(result.message=="success"){
    					var str="";
    					$.each(result.obj,function(key,business){
    						str+="<li><a href='#'>"+business.businessName+"</a></li>";
    					});
    					$("#nav").append(str);
    				}
    			}
    		});
    		
    		//供应信息展示
    		$.ajax({
    			url:"home/queryOrderByCondition",
    			type:"get",
    			dataType:"json",
    			success:function(result){
    				if(result.message=="success"){
    					$.each(result.obj,function(key,order){
    						if(key>3){ //国际采购订单
    							var str="<div class='col-md-12 col-sm-6 col-xs-6'>";
    							str+="<div>";
    							str+="<img src='"+order.img1+"'  class='img-responsive'>";
    							str+="<h4>"+order.orderNamech+"</h4>";
    							str+="<p>"+order.orderNameen+"</p>";
    							str+="<button>国际采购订单</button>";
    							str+=" </div>";  
    							str+=" </div>";  
    			                $("#gjcgdd").append(str);
    						}else{	  //国际供应订单
    							var str="<div class='col-md-12 col-sm-6 col-xs-6'>";
    							str+="<div>";
    							str+="<img src='"+order.img1+"'  class='img-responsive'>";
    							str+="<h4>"+order.orderNamech+"</h4>";
    							str+="<p>"+order.orderNameen+"</p>";
    							str+="<button>国际供应订单</button>";
    							str+=" </div>";  
    							str+=" </div>";  
    			                $("#gjgydd").append(str);
    						}
    					});
    				}
    			}
    		});
    	});
    </script>
</head>
<body>
 		<div class="industry">
                    <h2 class="aside-title">行业分类</h2>
                    <ul class="nav" id="nav">
                    <!--                                    
                        <li><a href="#">轻工业类</a></li>
                        <li><a href="#">重工业类</a></li>
                        <li><a href="#">农业类</a></li>
                        <li><a href="#">纺织类</a></li>
                        <li><a href="#">日用百货类</a></li>
                        <li><a href="#">食品类</a></li>
                        <li><a href="#">运动休闲类</a></li>
                        <li><a href="#">3C产品</a></li>
                    -->    
                    </ul>
                </div>
                <div>
                    <h2 class="aside-title">供需信息</h2>
                    <div class="row good1 good" id="gjcgdd">
                    	<!--  
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际采购订单</button>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际采购订单</button>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际采购订单</button>
                            </div>
                        </div >
                        -->
                    </div>
                    <div class="row good2 good" id="gjgydd">
                    	<!--  
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际供应订单</button>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际供应订单</button>
                            </div>
                        </div>
                        <div class="col-md-12 col-sm-6 col-xs-6">
                            <div>
                                <img src="img/info.jpg"  class="img-responsive">
                                <h4>阿里巴巴生活模式</h4>
                                <p>Alibaba Life Mode  </p>
                                <button>国际供应订单</button>
                            </div>
                        </div >
                        -->
                    </div>
                </div>
</body>
</html>