package com.highcom.admin.controller;
import java.io.*;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.TimerTask;
import javax.annotation.Resource;
import javax.servlet.http.*;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.google.code.kaptcha.Producer;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.HomeLoginService;
import com.highcom.utils.VerifyCodeUtils;
import io.swagger.annotations.*;
import sun.misc.BASE64Encoder;

@Api(tags = "首页用户登陆注册")
@Controller
@RequestMapping("/home")
public class HomeLoginController {

	@Autowired
	private HomeLoginService homeLoginService;
	
	@Resource
    private Producer captchaProducer;
	
	@Autowired
    private AssignConfig assignConfig;
		
    /**
     *             
     * 获取验证码图片
     * @param         request
     * @param         response
     * @return
     * @throws         IOException
     */
	/*
	@ApiOperation(value = "生成验证码")
    @RequestMapping("getCaptchaCode")
    public ModelAndView getCaptchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
        HttpSession session = request.getSession();
        
        response.setDateHeader("Expires", 0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg"); 
        
        //生成验证码文本
        String capText = captchaProducer.createText();  
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        System.out.println("生成验证码文本===="+capText);
        //利用生成的字符串构建图片
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();  
        ImageIO.write(bi, "jpg", out);  
        
        try {  
            out.flush();  
        } finally {  
            out.close();  
        }
        return null;
    }
	*/
	@ApiOperation(value = "生成验证码")
	@RequestMapping(value = "createCode", method = RequestMethod.GET)
	public void createCode(HttpSession session, HttpServletResponse response) {
		try {
			OutputStream outputStream = response.getOutputStream();
			int w = 100, h = 46;
			String code = VerifyCodeUtils.generateVerifyCode(4);
			// 存入session
			session.setAttribute("code", code);
			VerifyCodeUtils.outputImage(w, h, outputStream, code);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		try {
			//生成对应宽高的初始图片
			int width = 100;
			int height = 46;
			BufferedImage verifyImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			//功能是生成验证码字符并加上噪点，干扰线，返回值为验证码字符              
			String randomText = VerifyCodeUtils.java.drawRandomText(width, height, verifyImg);
			// 存入session
			session.setAttribute("code", randomText);
			response.setContentType("image/png");// 必须设置响应内容类型为图片，否则前台不识别
			OutputStream os = response.getOutputStream(); // 获取文件输出流
			ImageIO.write(verifyImg, "png", os);// 输出图片流
			os.flush();
			os.close();// 关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	@ApiOperation(value = "验证验证码")
	@ApiImplicitParam(name = "code", value = "验证码不区分大小写", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "verifyCode", method = RequestMethod.GET)
	@ResponseBody
	public Result verifyCode(String code, HttpSession session) {
		if (code.equalsIgnoreCase((String) session.getAttribute("code"))) {
			return new Result(200, "success");
		}
		return new Result(200, "error");
	}

	@ApiOperation(value = "登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "login", method = RequestMethod.POST)
	@ResponseBody
	public Result<Company> login(String userName, String password, HttpSession session) {
		Company company = homeLoginService.login(userName, password);
		if (company != null) {
			//判断是否为vip
			if(company.getVipbegin()!=null && company.getVipend()!=null) {
				String currentTime=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				if(company.getVipbegin().compareTo(currentTime)<=0 && company.getVipend().compareTo(currentTime)>=0) {
					company.setVipflag(1); //1 为vip
				}else {
					company.setVipflag(0); //0 为非vip
				}
			}else {
				company.setVipflag(0);
			}
			session.setAttribute("company", company);
			// 清空code
			session.removeAttribute("code");
			return new Result<Company>(200, "success", company);
		}
		return new Result(200, "error");
	}

	@ApiOperation(value = "注销")
	@RequestMapping(value = "logout", method = RequestMethod.POST)
	@ResponseBody
	public Result logout(HttpSession session) {
		try {
			session.removeAttribute("company");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(200, "error");
		}
		return new Result(200, "success");
	}

	@ApiOperation(value = "用户名唯一性验证")
	@RequestMapping(value = "queryCompanyCountByName", method = RequestMethod.POST)
	@ResponseBody
	public Result queryCompanyCountByName(String userName) {
		return new Result(200, homeLoginService.queryCompanyCountByName(userName));
	}
	
	@ApiOperation(value = "企业名唯一性验证")
	@RequestMapping(value = "queryCompanyCountByComName", method = RequestMethod.POST)
	@ResponseBody
	public Result queryCompanyCountByComName(String comName) {
		return new Result(200, homeLoginService.queryCompanyCountByComName(comName));
	}

	@ApiOperation(value = "注册")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "comName", value = "企业全称", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "contacts", value = "联系人", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "management", value = "主营项目", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "register", method = RequestMethod.POST)
	@ResponseBody
	public Result register(String userName, String password, String telephone,String comName,String contacts,String management) {
		return new Result(200, homeLoginService.register(userName, password, telephone,comName,contacts,management));
	}
	

	@ApiOperation(value = "生成短信验证码，过期时间60s")
	@ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String", paramType = "query")
	@RequestMapping(value = "createMsgCode", method = RequestMethod.POST)
	@ResponseBody
	public Result createMsgCode(String telephone, HttpSession session) {
		// 生成6位验证码
		//String msgCode = String.valueOf(new Random().nextInt(899999) + 100000);
		
		//临时用固定值处理
		String msgCode = "888888";
		System.out.println(telephone+"--短信验证：" + msgCode);
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


	@ApiOperation(value = "验证短信验证码,验证通过，就会移除")
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

	@ApiOperation(value = "修改密码根据用户名查询用户")
	@ApiImplicitParam(name = "userName", value = "用户账户", dataType = "String", paramType = "query")
	@RequestMapping(value = "queryCompanyByName", method = RequestMethod.POST)
	@ResponseBody
	public Result queryCompanyByName(String userName) {
		Company c = homeLoginService.queryCompanyByName(userName);
		if (c != null) {
			return new Result<Company>(200, "success", c);
		}
		return new Result<Company>(200, "error");
	}
	
	@ApiOperation(value = "获取当前用户")
	@RequestMapping(value = "queryCurrentUser", method = RequestMethod.POST)
	@ResponseBody
	public Result queryCurrentUser(HttpSession session) {
		Company company=(Company) session.getAttribute("company");
		if(company!=null) {
			return new Result<Company>(200, "success", company);
		}
		return new Result<Company>(200, "error");
	}

	@ApiOperation(value = "确定修改密码")
	@ApiImplicitParams({ @ApiImplicitParam(name = "comid", value = "用户id", dataType = "Integer", paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "String", paramType = "query") })
	@RequestMapping(value = "updatePwd", method = RequestMethod.POST)
	@ResponseBody
	public Result updatePwd(Integer comid, String password) {
		return new Result(200, homeLoginService.updatePwd(comid, password));
	}

	@ApiOperation(value = "升级vip下载入驻表格")
	@RequestMapping(value = "/downloadVIPTable", method = RequestMethod.GET)
	public ResponseEntity downloadVIPTable(HttpServletRequest request) throws Exception {
		//String path = request.getServletContext().getRealPath("/resourcefile");
		String path = "";
		if(assignConfig.uploadtype.equals("1")){  //1 服务器路径
			path=assignConfig.uploadUrl;
		}else{
			path=assignConfig.uploadpath;	//0 开发路径
		}
		
		// 设置响应头对象
		HttpHeaders headers = new HttpHeaders();

		// 中文响应回去乱码解决
		String fileName = this.getFilename(request, "viptemplate.doc");
		File file = new File(path + File.separator + fileName);
		if (!file.exists()) {
			MediaType mediaType = new MediaType("text", "html", Charset.forName("utf-8"));
			headers.setContentType(mediaType);
			return new ResponseEntity<String>("对不起，下载的资源不存在", headers, HttpStatus.OK);
		}

		// attachment附件形式，filename下载文件的默认名称
		headers.setContentDispositionFormData("attachment", fileName);
		// 设置Mime类型
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// 设置显示文件的大小
		headers.setContentLength(file.length());
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
	}

	
	// 下载资源中文回显乱码解决
	public String getFilename(HttpServletRequest req, String fileName) throws Exception {
		// 先从request中获取浏览器的信息
		String agent = req.getHeader("User-Agent");

		// 判断是什么浏览器
		if (agent.contains("Firefox")) {
			// 如果是火狐浏览器，使用Base64编码
			BASE64Encoder base64Encoder = new BASE64Encoder();
			try {
				fileName = "=?UTF-8?B?" + new String(base64Encoder.encode(fileName.getBytes("UTF-8"))) + "?=";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		} else {
			// 如果是IE或者谷歌浏览器 使用URL编码
			fileName = URLEncoder.encode(fileName, "UTF-8");
			// IE浏览器会把空格变成 + 号，所以这里变回来
			fileName = fileName.replace("+", " ");
		}
		return fileName;
	}
	
	@ApiOperation(value = "修改前台企业信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "comid", value = "企业id", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "comName", value = "企业全称", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "contacts", value = "联系人", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "management", value = "主营项目", required = true, dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "telephone", value = "手机号", required = true, dataType = "String", paramType = "query") })
	@RequestMapping(value = "updateCompany", method = RequestMethod.POST)
	@ResponseBody
	public Result updateCompany(String comid,String userName,String telephone,String comName,String contacts,String management,HttpSession session) {
		String result=homeLoginService.updateCompany(comid, userName, telephone, comName, contacts, management);
		if(result.equals("success")) { //修改成功，去掉session
			session.removeAttribute("company");
		}
		return new Result(200,result);
	}
	
	//检查手机号是否可用
	@RequestMapping(value = "/checkTelephone", method = RequestMethod.POST)
  	@ResponseBody
  	public Result checkTelephone(String telephone){
  		return new Result(200, homeLoginService.queryCompanyCountByTelephone(telephone));
  	}
}
