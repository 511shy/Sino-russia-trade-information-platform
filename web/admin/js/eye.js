var show=false;
function togglePwd(item,img){
	if(show==false){
	//	$("#"+item).prop("type","input");
		$("#"+item).attr("type","input");
		$(img).attr("src","images/2.png");
		show=true;
	}
	else{
		$("#"+item).attr("type","password");
		$(img).attr("src","images/1.png");
		show=false;
	}
}