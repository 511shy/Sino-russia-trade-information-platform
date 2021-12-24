# Sino-russia-trade-information-platform
=============================================中俄平台 ======================================================
1 项目发布
2 项目背景和前台功能
3 项目的后台功能
4 技术细节
============================================================================================================
==== 1 项目发布
   数据库 mysql 5 
   
   非maven项目 
   
   导入的时候 file ->General ->Exiting Project to workspacke
   
   更新一下依赖的 tomcat
     在构建路径那儿,先移除错误的tomcat依赖 , 选 add libary -->server runtime ,选中要用的tomcat
     
     改完tomcat依赖以后,项目上可能还有一个红叉 
     可以在工程上,右键,选 properties, 然后选 target runtimes ,选中我们要用的tomat
     
    配置虚拟目录:
     如果是在 STS 开发环境中,要选中Servers , 打开 Server.xml
     
     配置如下虚拟目录
     
      <Context docBase="e:/upload/" path="/rctms/homeImages" reloadable="true"/>
      
      要注意放置的位置,不要放错
      
      其中 e:/upload/ 是磁盘上图片存放的路径
      
    启动运行就可以了
    
    前台:
    http://localhost:8080/rctms/  
    
    后台
    http://localhost:8080/rctms/home_detail/adminlogin.jsp
    用户名 和密码
      bbbbbb
      bbbbbb
      
==== 2 项目背景和前台功能
		客户
		  黑龙江大学外国语学院 (俄语学院) 委托 
		  
		  目的: 分享商贸信息
		     中国 和俄罗斯 之间 供应,采购信息 
		     
		     
		   前台主导航
		     首页
		     国际采购 (显示采购信息,多组)
		     际际供应  (显示供应信息,多组)
		     
		     国际物流  (就是一个由用户维护的页面,里面展示用户想展示的信息)
		         海运
		         空运
		         陆运
		         物流仓
		        
		     新闻资讯 (多组)
		     			国际市场动态
		     			国内市声动态
		     			国际贸易最新政策
		     			
		     关于我们 (一页信息)
		     
		     其他附属功能
		         外贸助手
		            国际快递查询
		            海关业务查询
		            每日外汇牌价查询
		            出口退税率查询
		            世界时间查询
		            全球汇率换算器
		            
		            
		     前台用户的登录和注册功能 
		       前台用户: 企业 (企业和用户是同一个表) 
		                    一般用户(企业)  ==>可以升级为VIP
		                    vip用户(企业)
		       
		     
==== 3 项目的后台功能

	VIP企业管理
	普通企业管理
	国际采购管理
	国际供应管理
	国际物流管理
	新闻资讯管理
	关于我们
	首页管理
	统计分析
	系统权限管理
	
	
	VIP企业管理 
	     新增vip企业  //前台已经有vip企业注册功能,为什么后台也有这个功能,这是用户提出的需求,可以加关系用户
	     							//添的信息更完善,用了富文本编辑器
	     							//有校验功能				
	     上传申请模板
	     
	     所有vip列表的多条件复合查询,修改,删除等功能
	   
	普通企业管理
	    普通企业后台没有录入功能,只有删除,修改,升级vip的功能
	    
	国际采购管理
	    发布采购信息
   
	国际物流管理
	    发布供应信息
	    
	新闻资讯管理
	    发布新闻信息
	    外贸助手信息维护
	关于我们
	    就是一组信息
	首页管理
	  维护首页上的图片
	  QQ号,微信二维码
	  
	统计分析
	   订单访问量数据分析
	     	供应订单访问量
			  采购订单访问量
			  总计

	   订单数据分析
	     订单总数
	     已完成订单 数量
	     未完成订单 数量
	     采购订单 数量
	     供应订单 数量
	     
	   订单数据分析的表格 
	      可以按年统计
	   
	   注册企业数据分析
	       vip企业数
	       普通企业数
	       
	   注册企业统计报表
	   
	   
	   采购订单访问量数据排行(前30)
	   供应订单访问量数据排行(前30)
	   
	系统权限管理
	   管理管理员列表
	   停用管理员
	   新增管理员
	   
	   普通管理员和超级管理虽的区别是
	     普通管理员看不到 "系统权限管理"这个菜单
	     
==== 4 技术细节
  SSM  
   Spring + SpringMVC + Mybatis
   
   后台: 按前后端分离的模式设计的 
  
  
   附 layui分页
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <link rel="stylesheet" href="./js/layui/css/layui.css"></link>
  <script src="./js/layui/layui.js"></script>
  <script src="./js/jquery.min.js"></script>
  <script>
  
  $(function(){    
	  	layui.use('table', function(){
	  	  var table = layui.table;
	  	  
	  	  table.render({
	  	    elem: '#table',
	  	    url:'../admin/page',
	  	    limit: 3,
	  	    limits: [2, 3, 5],
   
	         //自定义参数
	         where:{
	        	// comName:"上海某某公司",
	        	// management:"珠宝,首饰",
	        	// searchbegin:"2000-10-12",
	        	// searchend:"2000-12-12"
			 },
	            
	  	    cols: [[
	  	      {field:'comid', width:180, title: '公司id',sort: false },
	  	      {field:'comName', width:180, title: '公司名'},
	  	      {field:'registerTime', width:180, title: '注册时间'},
	  	      {field:'management', width:180, title: '经营泛围'}
	  	    ]]
			 
	  	    ,page: true
	  	    
	  	  });
	  	}); 
  });
  
  </script>
</head>

<body>
	<table class="layui-table" id="table" lay-filter="test"></table>
</body>

</html>
  
  
/**
	 * 本例为layi分页使用示例
	 * @param comName 自定义参烽
	 * @param management  自定义参烽
	 * @param searchbegin  自定义参烽
	 * @param searchend  自定义参烽
	 * @param page 固定参数名,页码
	 * @param limit  固定参数名 页面大小

	 * @return layui固定的数据格式
	 */
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public Map<String, Object> test(String comName, String management, String searchbegin, String searchend,
			String page, Integer limit) {

		System.out.println(comName);
		System.out.println(management);
		System.out.println(searchbegin);
		System.out.println(searchend);

		Company company = new Company();
		company.setComName(comName);
		company.setManagement(management);
		company.setSearchbegin(searchbegin);
		company.setSearchend(searchend);
		if (page == null || "".equals(page)) {
			company.setPageNum(1);
		} else {
			company.setPageNum(Integer.parseInt(page));
		}

		company.setPageSize(limit);
		PageHelper.clearPage();
		PageHelper.startPage(company.getPageNum(), company.getPageSize());
		List<Company> list = this.companyService.findVipCompanyList(company);

		//下面是layui 分页要求返回的固定数据式
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", 0);
		map.put("totalCount", 11);
		map.put("data", list);
		map.put("count", 8);
		return map;
	}
	   
	   
	 切面 日志处理:

/**
 * 系统操作日志处理
 */
@Aspect @Component  
public class LogAspect {
	private  static  Map<String,String> methodMap=new HashMap<>();
	static {
		methodMap.put("updateaboutus", "关于我们_信息更新");
		methodMap.put("updateAssitant", "新闻资讯_更新外贸助手信息");
		methodMap.put("updateHomeInfo", "首页管理_更新首页信息");
		methodMap.put("delAdmin", "系统权限管理_停用管理员");
		methodMap.put("updateAdmin", "系统权限管理_更新管理员");
		methodMap.put("addAdmin", "系统权限管理_新增管理员");
		methodMap.put("updatePwd", "系统权限管理_修改管理员密码");
		methodMap.put("adminUpdatePwd", "系统权限管理_修改管理员密码");
		methodMap.put("updateflow", "国际物流_信息更新");
		methodMap.put("savenews", "新闻资迅管理_发布新闻");
		methodMap.put("updatenews", "新闻资迅管理_更新新闻");
		methodMap.put("delnews", "新闻资迅管理_删除新闻");
		methodMap.put("comSave", "普通企业管理_添加普通企业");  
		methodMap.put("upgradevip", "vip企业管理_升级vip"); 
		methodMap.put("updateNormalCompany", "普通企业管理_更新普通企业");
		methodMap.put("deleteNormalCompany", "普通企业管理_删除普通企业");
		methodMap.put("addPurchaseOrder", "国际采购_添加采购订单");
		methodMap.put("publishPurchaseOrder", "国际采购_发布采购信息"); 
		methodMap.put("deletePurchaseOrder", "国际采购_删除订单");  
		methodMap.put("updatePurchaseOrder", "国际采购_修改订单");
		methodMap.put("updateOrder", "国际供应_修改订单");
		methodMap.put("addOrder", "国际供应_添加订单");
		methodMap.put("publishOrder", "国际供应_发布订单");
		methodMap.put("deleteOrder", "国际供应_删除订单");
		methodMap.put("vipComSave", "vip企业管理_添加vip企业");
		methodMap.put("updateCompany", "vip企业管理_修改vip企业");
		methodMap.put("deleteCompany", "vip企业管理_删除vip企业");
		methodMap.put("uploadVipDoc", "vip企业管理_上传vip模板");
	}
	
	@Resource
	private LogService logService;
	
	@Resource
	private AdminInfoService adminInfoService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private CompanyService companyService;
	
	@Resource
	private OrderService orderService;
	
	@Pointcut("execution(* com.highcom.admin.controller.*.*(..))")  //切入点
	private void anyMethodAAA() {}

	@Around("anyMethodAAA()")
	public Object aroundMethod(ProceedingJoinPoint point) {
		Object result=null;

		ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
    	HttpSession session=attr.getRequest().getSession(true);
    	AdminInfo admin=(AdminInfo)session.getAttribute("adminInfo");
    	
    	String methodName=point.getSignature().getName();

    	if(admin!=null) {
    		String v=methodMap.get(methodName);
    		if(v!=null) {
    			String [] moduleAndTitile=v.split("_");  //例: 企业管理_添加vip企业
    			
    			//详细信息,要根据不同的情况具体处理
    			String content="";
    			
    			//得到所有方法的参数
    			Object [] params=point.getArgs();
	
    			//有几个情况要特别处理
    			if("delAdmin".equals(methodName)) {
    				AdminInfo tmpAdmin=adminInfoService.getAdminInfoById(Integer.parseInt(params[0].toString()));
    				content="停用了账号为 ["+tmpAdmin.getAdminName() +"] 的管理员";
    			}
    			else if("updateAdmin".equals(methodName)) {
    				AdminInfo tmpAdmin=(AdminInfo)params[0];
    				content="更新了了账号为 ["+tmpAdmin.getAdminName() +"] 的管理员信息";
    			}
    			else if("addAdmin".equals(methodName)) {
    				AdminInfo tmpAdmin=(AdminInfo)params[0];
    				content="新建账号为  ["+tmpAdmin.getAdminName() +"] 的管理员 ";
    			}
    			else if("savenews".equals(methodName)) {
    				News news=(News)params[1];
    				content="发布标题为 ["+news.getTitle()+"] 的新闻信息";
    			}
    			else if("updatenews".equals(methodName)) {
    				News news=(News)params[1];
    				content="更新标题为 ["+news.getTitle()+"] 的新闻内容";
    			}
    			else if("delnews".equals(methodName)) {
    				int id=Integer.parseInt(params[0].toString());
    				News tmpNews=newsService.findNewsById(id);		
    				content="删除了标题为 [ "+tmpNews.getTitle()+" ] 的新闻信息";
    			}
	
    			else if("comSave".equals(methodName)) {
    				Company comp=(Company)params[0];
    				content="添加了名为 [ "+comp.getComName()+" ] 的普通企业";
    			}
    			
    			else if("updateNormalCompany".equals(methodName)) {
    				Company comp=(Company)params[0];
    				content="更新了名为 [ "+comp.getComName()+" ] 的普通企业信息";
    			}
    			
    			else if("deleteNormalCompany".equals(methodName)) {
    				Company tmpCompany=companyService.findCompanyById(params[0].toString());
    				content="删除了名为 [ "+tmpCompany.getComName()+" ] 的普通企业";
    			}
    			
    			
    			else if("upgradevip".equals(methodName)) {
    				Company tmpCompany=companyService.findCompanyById(params[0].toString());
    				content="将企业 [ "+tmpCompany.getComName()+" ] 升级为vip  vip开始时间 [ "+params[1]  +" ]  vip结束时间 [ "+params[2]+" ]";
    			}
    			
    			else if("vipComSave".equals(methodName)) {
    				Company comp=(Company)params[0];
    				content="添加vip企业  [ "+comp.getComName()+" ] vip开始时间 [ "+comp.getVipbegin()  +" ]  vip结束时间 [ "+comp.getVipend()+" ]" ;
    			}
    			
    			else if("updateCompany".equals(methodName)) {
    				Company comp=(Company)params[0];
    				//查出数据库中的数据,和要提交的数据进行比对,看看是否修改了vip开始时间和结束时间
    				String newBegin=comp.getVipbegin();
    				String newEnd=comp.getVipend();
    				
    				Company tmpCompany=companyService.findCompanyById(comp.getComid());
    				
    				String oldBegin=tmpCompany.getVipbegin();
    				String oldEnd=tmpCompany.getVipend();
    				
    				//没做vip时间调整，只更新了基本信息
    				if(newBegin.equals(oldBegin)&&newEnd.equals(oldEnd)) {
    					content="更新  [ "+comp.getComName()+" ] 的基本信息" ;
    				}
    				
    				//更新了vip时间
    				else {
    					content="更新  ["+comp.getComName()+"] 的企业信息  更新后：vip开始时间 [ "+comp.getVipbegin()  +" ]  vip结束时间 [ "+comp.getVipend()+" ]" ;
    				}
    			}
    			
    			else if("deleteCompany".equals(methodName)) {
    				Company tmpCompany=companyService.findCompanyById(params[0].toString());
    				content="删除了vip企业 [ "+tmpCompany.getComName()+" ]";
    			}
    			
    			else if("addPurchaseOrder".equals(methodName)) {
    				Order order=(Order)params[0];
    				content="添加订单 [ "+order.getOrderNamech()+" ] ";
    			}
    			
    			else if("publishPurchaseOrder".equals(methodName)) {
    				Order tmpOrder=orderService.findOrderById(params[0].toString());
    				content="发布订单 [ "+tmpOrder.getOrderNamech()+" ] ";
    			}
    			
    			
    			else if("updatePurchaseOrder".equals(methodName)) {
    				Order order=(Order)params[0];
    				content="修改订单 [ "+order.getOrderNamech()+" ] ";
    			}
	
    			else if("deletePurchaseOrder".equals(methodName)) {
    				Order tmpOrder=orderService.findOrderById(params[0].toString());
    				content="删除订单 [ "+tmpOrder.getOrderNamech()+" ] ";
    			}

    			else if("updateOrder".equals(methodName)) {
    				Order order=(Order)params[0];
    				content="修改订单  [ "+order.getOrderNamech()+" ]" ;
    			}
    			
    			else if("addOrder".equals(methodName)) {
    				Order order=(Order)params[0];
    				content="添加订单  [ "+order.getOrderNamech()+" ]" ;;
    			}
    
    			else if("publishOrder".equals(methodName)) {
    				
    				Order tmpOrder=orderService.findOrderById(params[0].toString());
    				content="发布订单 [ "+tmpOrder.getOrderNamech()+" ] ";
    			}
    			
    			else if("deleteOrder".equals(methodName)) {
    				Order tmpOrder=orderService.findOrderById(params[0].toString());
    				content="删除订单 [ "+tmpOrder.getOrderNamech()+" ] ";
    			}
    			
    			LogInfo logInfo=new LogInfo();
        		logInfo.setAdminName(admin.getAdminName());
        		logInfo.setLogTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        		logInfo.setModule(moduleAndTitile[0]);
        		logInfo.setTitle(moduleAndTitile[1]);
        		logInfo.setContent(content);
        		logService.addLog(logInfo);
    		} 		
    	}
    
		try {
			result = point.proceed();
		}
		catch(Throwable ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}
	
		return  result;
	}
}


   报表 统计分析
     <script src="./js/echarts.js"></script>	
    
