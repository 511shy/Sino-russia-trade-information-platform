package com.highcom.admin.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.highcom.admin.pojo.HomeInfo;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "后台首页管理")
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
    @Autowired
    private AdminService adminService;

    /**
     * 修改首页信息
     */
    @ApiOperation(value="添加首页信息")
	/*@ApiImplicitParams({
		 @ApiImplicitParam(name="id",value="主键",required=true,dataType="Integer",paramType="query"),
	     @ApiImplicitParam(name="content",value="消息内容",required=false,paramType="query"),
	     @ApiImplicitParam(name="contentType",value="内容类型1：轮播 ,2：qq,3：微信，4：What'sup",required=true,dataType="Integer",paramType="query")
	})*/
    @RequestMapping(value="updateHomeInfo", method= RequestMethod.POST)
    public String updateHomeInfo(MultipartFile fileImg, HomeInfo homeInfo,HttpServletRequest req){
    	int s=1;
    	if(adminService.updateHomeInfo(fileImg, homeInfo)) {
    		s=2;
    	}
    	return "redirect:administration.html?s="+s;
    }

    /**
     *
     * 查询
     * @return
     */
    @RequestMapping(value="queryHomeInfo", method= RequestMethod.GET)
    @ResponseBody
    public Result<List<HomeInfo>> queryHomeInfo(){
        return new Result<List<HomeInfo>>(200,"success",adminService.queryHomeInfo());
    }
}
