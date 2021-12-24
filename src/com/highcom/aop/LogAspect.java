package com.highcom.aop;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.*;

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


