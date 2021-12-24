$(function(){  
	//合作伙伴切换tab页
	 $(".tabs .row div .nav li").eq(0).css({backgroundColor:"#3366CC",color:"white",border:"0px"})
	    $(".tabs  .row div .nav li").click(function(){      
	        var n=$(this).index()        
	        $(".tabs .row div .nav li").css({backgroundColor:"",color:"",border:""})
	        $(this).css({backgroundColor:"#3366CC",color:"white",border:"0px"})
	        $(".tabs   .partner-wrap .tab").css({display:"none"})
	        $(".tabs  .partner-wrap .tab").eq(n).css({display:"block"})                
	    })
    //页面滚动条事件
    $(window).scroll(function() {   
      if ($(document).scrollTop()>=600){            
    	  $(".scrolltop").fadeIn(500)
      }
      else{
    	  $(".scrolltop").fadeOut(500)
      }
    })
    //返回顶部
    $(".scrolltop").click(function(){
    	 $("html,body").animate({scrollTop:"0px"},1000);
    })
    //图片切换事件
    $(".small div img").click(function(){
        var url=$(this).attr("src")
        $(".img-box .big img").attr("src",url)
    })
    //修改密码 
    $("#updatepass").click(function(){
        window.location.href="home_detail/forget.jsp"
    })
    //升级vip 
    $(".vipbtn").click(function(){
        window.location.href="home_detail/vipres.jsp"
    })
	
})
//在线咨询
function qqzx(){
 		window.location.href="tencent://message/?uin="+$("#qqNo").val()+"&Menu=yes";
}
