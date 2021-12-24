package com.highcom.admin.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.AdminService;
import io.swagger.annotations.*;

@Api(tags = "前台首页")
@RestController
@RequestMapping("/home")
public class HomeController extends BaseController {
	
    @Autowired
    private AdminService adminService;

    /**
     *
     * 查询轮播信息
     * @return
     */
    @ApiOperation(value="查询首页信息根据内容类别")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="contentType",value="1：轮播 ,2：qq,3：微信，4：What'sup",dataType="Integer",paramType="query")
    })
    @RequestMapping(value="queryHomeInfoByContentType", method= RequestMethod.GET)
    public Result<List<HomeInfo>> queryHomeInfoByContentType(Integer contentType){
    	return new Result<List<HomeInfo>>(200,"success",adminService.queryHomeInfoByContentType(contentType));
    }
    
    /**
    *
    * 查询供需信息
    * @return
    */
   @ApiOperation(value="推荐供需信息(采购信息|供应订单)，按修改时间倒序")
   @ApiImplicitParams({
  	 @ApiImplicitParam(name="count",value="查询几条",dataType="Integer",paramType="query"),
  	 @ApiImplicitParam(name="flag",value="1 代表采购信息 2代表供应订单",dataType="Integer",paramType="query")
  })
   @RequestMapping(value="queryOrderByCondition", method= RequestMethod.GET)
   public Result<List<Order>> queryOrderByCondition(Integer count,Integer flag){ //count查询数据条数，flag 1查询采购信息 2查询供应订单
   		return new Result<List<Order>>(200,"success",adminService.queryOrderByCondition(count,flag));
   }
   
   /**
   *
   * 查询供需信息
   * @return
   */
  @ApiOperation(value="查询首页页脚信息 [contentType 1：轮播 ,2：qq,3：微信，4：What'sup]")
  @RequestMapping(value="queryPagefooter", method= RequestMethod.GET)
  public Result<List<HomeInfo>> queryPagefooter(){
  		return new Result<List<HomeInfo>>(200,"success",adminService.queryPagefooter());
  }
}
