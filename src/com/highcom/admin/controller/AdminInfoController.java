package com.highcom.admin.controller;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.AdminInfoService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 后台管理员
 */
@Controller @RequestMapping("/admin")
public class AdminInfoController {
	@Resource
	private AdminInfoService adminInfoService;
	
	//查询列表
	@ResponseBody @RequestMapping("/listAdmin")
	public Result<List<AdminInfo>> listAdmin(HttpSession session) {
		
		List<AdminInfo>adminList=adminInfoService.selectAll();
		for(AdminInfo a:adminList) {
			a.setAddTime(a.getAddTime().substring(0,10));  //2021-02-04 10:02:56.0  截取成 2021-02-04
		}
		
		AdminInfo admin=(AdminInfo)session.getAttribute("adminInfo");
		
		//把当前登录用户的id传给前台(前台用它来判断用户不能删除自已)
		String sessionAdminId="";
		if(admin!=null) {
			 sessionAdminId=admin.getId()+"";
		}
		return new Result<>(200,sessionAdminId,adminList);
	}
	
	//停用账号
	@ResponseBody @RequestMapping("/delAdmin")
	public Result<Object> delAdmin(int id) {
		adminInfoService.delAdminById(id);
		return new Result<>(200,"账号停用成功!","附: 客户端收到返回数据后,应再次调用 /delAdmin 请求刷新数据");
	}
	
	//根据id查询用户信息(用于修改)
	@ResponseBody @RequestMapping("/searchAdmin")
	public Result<AdminInfo> searchAdminForUpdate(int id)
	{
		AdminInfo admin=adminInfoService.getAdminInfoById(id);
		return new Result<AdminInfo>(200,"",admin);
	}
	
	//更新管理员信息
	@ResponseBody @RequestMapping("/updateAdmin")
	public Result<Object> updateAdmin(AdminInfo admin)
	{
		adminInfoService.updateAdmin(admin);
		return new Result<>(200,"管理员信息更新成功!" ,"");
	}
	
	//添加管理员
	@ResponseBody @RequestMapping("/addAdmin")
	public Result<Object> addAdmin(AdminInfo admin)
	{
		String date=DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss" ).format(LocalDateTime.now());
		admin.setAddTime(date);
		admin.setDelFlag("0");
		adminInfoService.addAdmin(admin);
		return new Result<>(200,"管理员信息添加成功!" ,"");
	}
	
	//客户端取得Session中的Admin信息
	@ResponseBody @RequestMapping("/getSessionAdmin")
	public Result<AdminInfo> getSessionAdmin(HttpSession session)
	{
		AdminInfo admin=(AdminInfo)session.getAttribute("adminInfo");
		return new Result<>(200,"" ,admin);
	}

	//检查账号是否可用
	@ResponseBody @RequestMapping("/checkAdminName")
	public Result<String> checkAdminName(String adminName){
		AdminInfo admin=adminInfoService.getAdminByName(adminName);
		if(admin!=null) {
			return new Result<>(200,"账号已被其他用户使用!","0");
		}
		else {
			return new Result<>(200,"账号可用","1");
		}	
	}
	@ApiOperation(value = "后台登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "adminName", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Result<AdminInfo> login(String adminName, String password, HttpSession session) {
		AdminInfo adminInfo = adminInfoService.login(adminName, password);
		if (adminInfo!= null) {
			session.setAttribute("adminInfo", adminInfo);
			// 清空code
			session.removeAttribute("code");
			return new Result<AdminInfo>(200, "success", adminInfo);
		}
		return new Result(200, "error");
	}
	
	@ApiOperation(value = "后台修改密码根据用户名查询用户")
	@ApiImplicitParam(name = "adminName", value = "用户账户", dataType = "String", paramType = "query")
	@RequestMapping(value = "queryAdminInfoByName", method = RequestMethod.POST)
	@ResponseBody
	public Result queryAdminInfoByName(String adminName) {
		AdminInfo adminInfo = adminInfoService.getAdminByName(adminName);
		if (adminInfo != null) {
			return new Result<AdminInfo>(200, "success", adminInfo);
		}
		return new Result<Company>(200, "error");
	}
	
	@ApiOperation(value = "后台生成短信验证码，过期时间60s")
	@ApiImplicitParam(name = "phone", value = "手机号", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "createMsgCode", method = RequestMethod.POST)
	@ResponseBody
	public Result createMsgCode(String phone, HttpSession session) {
		// 生成6位验证码
		//String msgCode = String.valueOf(new Random().nextInt(899999) + 100000);
		String msgCode ="888888";
		System.out.println(phone+"--短信验证：" + msgCode);
		session.setAttribute("msgCode", msgCode);
		removeAttrbute(session,"msgCode"); //定时1分钟删除验证码
		// 发送短信验证
		// .......
		return new Result(200, "success");
	}
	
	/**
	 * 设置1分钟后删除session中的验证码
	 * @param session
	 * @param attrName
	 */
	private void removeAttrbute(final HttpSession session, final String attrName) {
		final Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// 删除session中存的验证码
				session.removeAttribute(attrName);
				timer.cancel();
			}
		}, 1 * 60 * 1000);
	}
	
	@ApiOperation(value = "后台验证短信验证码,验证通过，就会移除")
	@ApiImplicitParam(name = "msgCode", value = "短信验证码", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "verifyMsgCode", method = RequestMethod.POST)
	@ResponseBody
	public Result verifyMsgCode(String msgCode, HttpSession session) {
		Object obj = session.getAttribute("msgCode");
		if (obj != null) { // 说明没过期
			if (msgCode.equals((String) obj)) {
				// 移除
				session.removeAttribute("msgCode");
				return new Result(200, "success"); //通过
			} else {
				return new Result(200, "error");  //未通过
			}
		}
		return new Result(200, "delayed"); //验证码过期
	}
	
	@ApiOperation(value = "后台确定修改密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户id", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query") })
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public Result updatePwd(Integer id, String password) {
		return new Result(200, adminInfoService.updatePwd(id, password));
	}
	
	@ApiOperation(value = "后台注销")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	@ResponseBody
	public Result logout(HttpSession session) {
		session.invalidate();
		return new Result(200, "success");
	}
	
	/**
	 * 防止session过期
	 */
	@ResponseBody
    @RequestMapping("/refreshSession")
	public void refreshSession(HttpSession session) {
		AdminInfo adminInfo=(AdminInfo)session.getAttribute("adminInfo");
		session.setAttribute("adminInfo", adminInfo);
	}
    
    @ApiOperation(value = "后台密码修改")
	@RequestMapping(value = "adminUpdatePwd", method = RequestMethod.POST)
	@ResponseBody
	public Result adminUpdatePwd(String oldpwd,String newpwd,HttpSession session) {
    	AdminInfo adminInfo=(AdminInfo) session.getAttribute("adminInfo");
    	if(adminInfo!=null) {
    		//先验证原密码是否正确
    		if(adminInfo.getPassword().equals(oldpwd)) {
    			//修改密码
    			if(adminInfoService.updatePwd(adminInfo.getId(), newpwd).equals("success")) {
    				//替换session中的old数据
    				adminInfo.setPassword(newpwd);
    				session.setAttribute("adminInfo", adminInfo);
    				return new Result(200,"success");
    			}else { //修改失败
    				return new Result(200,"error");
    			}
    		}else {
    			//原密码错误
    			return new Result(200, "verifyerror");
    		}
    	}
		return null;
	}
    
    
    //检查手机号是否可用,
  	@ResponseBody @RequestMapping("/checkPhone")
  	public Result checkPhone(String phone){
  		return new Result(200, adminInfoService.queryAdminCountByPhone(phone));
  	}
	
}
