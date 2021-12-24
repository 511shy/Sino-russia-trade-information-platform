package com.highcom.admin.controller;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.OrderService;
import io.swagger.annotations.*;

@Api(tags = "前台-国际采购")
@Controller
@RequestMapping("/home")
public class HomePurchaseOrderController {
	@Autowired
	private OrderService orderService;
	
	@ApiOperation(value="查询采购订单列表")
    @ApiImplicitParams({
   	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query"),
   	 @ApiImplicitParam(name="comname",value="企业名称",paramType="query"),
   	 @ApiImplicitParam(name="businessid",value="行业id",paramType="query"),
   	 @ApiImplicitParam(name="publishtime",value="发布时间",paramType="query"),
   	 @ApiImplicitParam(name="pageNum",value="当前页码数",paramType="query"),
   	 //@ApiImplicitParam(name="pageSize",value="每页显示记录数量",paramType="query")
   })
	@ResponseBody
	@RequestMapping(value="/homePurchaseOrderList",method= RequestMethod.GET )
	public Result<PageInfo<Order>> findSupplyOrderList(
			String orderNamech,
			String comname,
			String businessid,
			String publishtime,
			int pageNum
			//int pageSize
			) {
    	Order order = new Order();
    	
		
    	order.setOrderNamech(orderNamech);
    	order.setComname(comname);
    	order.setBusinessid(businessid);
    	order.setPublishtime(publishtime);
    	order.setPageNum(pageNum);
    	order.setPageSize(10);
		order.setSupplyflag("0"); // set supply flag as 0 to find supply orders.
		PageHelper.clearPage();
		PageHelper.startPage(order.getPageNum(), order.getPageSize());

		List<Order> list = this.orderService.findOrderList(order);
		PageInfo<Order> pageInfo = new PageInfo<>(list, order.getPageSize());
		return new Result<PageInfo<Order>>(200, "success", pageInfo);

	}
	
    @ApiOperation(value="预览询采购订单，根据ID查询采购订单")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })     
	@ResponseBody
	@RequestMapping(value="/homeFindPurchaseOrderById", method = RequestMethod.GET)
	public Result<Order> findPurchaseOrderById(String orderid ,HttpSession session) {
		Order order = this.orderService.findOrderById(orderid);
		//BeanUtils.copyProperties(temp, order);

		//session.setAttribute("company", company);
		//session.setAttribute("adminInfo", adminInfo);
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
