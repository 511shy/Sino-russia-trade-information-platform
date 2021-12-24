package com.highcom.admin.controller;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.AdminInfo;
import com.highcom.admin.pojo.Company;
import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "前台-国际供应")
@Controller
@RequestMapping("/home")
public class HomeSupplyOrderController {
	@Autowired
	private OrderService orderService;
	
    @ApiOperation(value="查询供应订单列表")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query"),
    	 @ApiImplicitParam(name="comname",value="企业名称",paramType="query"),
    	 @ApiImplicitParam(name="businessid",value="行业id",paramType="query"),
    	 @ApiImplicitParam(name="publishtime",value="发布时间",paramType="query"),
    	 @ApiImplicitParam(name="pageNum",value="当前页码数",paramType="query"),
    	 //@ApiImplicitParam(name="pageSize",value="每页显示记录数量",paramType="query")
    })
	@ResponseBody
	@RequestMapping(value = "/homeSupplyOrderList", method = RequestMethod.GET)
	public Result<PageInfo<Order>> findSupplyOrderList(
			String orderNamech,
			String comname,
			String businessid,
			String publishtime,
			int pageNum
			//int pageSize
			) {
    	Order order = new Order();
    	
		// set supply flag as 1 to find supply orders.
    	order.setOrderNamech(orderNamech);
    	order.setComname(comname);
    	order.setBusinessid(businessid);
    	order.setPublishtime(publishtime);
    	order.setPageNum(pageNum);
    	order.setPageSize(10);
		order.setSupplyflag("1");
		PageHelper.clearPage();
		PageHelper.startPage(order.getPageNum(), order.getPageSize());

		List<Order> list = this.orderService.findOrderList(order);
		PageInfo<Order> pageInfo = new PageInfo<>(list, order.getPageSize());
		return new Result<PageInfo<Order>>(200, "success", pageInfo);

	}
    
    @ApiOperation(value="根据id查询供应订单")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })     
	@ResponseBody
	@RequestMapping(value="/homeSupplyOrderByid", method = RequestMethod.GET)
	public Result<Order> previewOrder(String orderid,HttpSession session ) {
		Order order = this.orderService.findOrderById(orderid);
		//session.setAttribute("company", company);
		//session.setAttribute("adminInfo", adminInfo);
		//区分匿名访问，普通用户  vip  status 0 匿名访问  1 普通用户  3 vip admin
		Result<Order> r = new Result<Order>(0, "success", order);
		Company com = (Company)session.getAttribute("company");
		//普通用户
		if(com != null && (com.getVipflag()==0)){
			r.setStatus(1);
		}		
		
		//vip
		if(com != null && (com.getVipflag()==1)){
			r.setStatus(3);
		}
		
		//admin
		AdminInfo adminInfo = (AdminInfo)session.getAttribute("adminInfo");

		if(adminInfo != null){
			r.setStatus(3);
		}
		return r;
	}
}
