package com.highcom.admin.controller;
import java.text.SimpleDateFormat;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.OrderService;
import com.highcom.common.UploadUtil;
import io.swagger.annotations.*;
@Api(tags = "后台管理-国际采购")
@Controller
@RequestMapping("/admin")
public class AdminPurchaseOrderController {
	@Autowired
	private OrderService orderService;

	@Autowired
    private AssignConfig assignConfig;

	@ApiOperation(value="查询采购订单列表")
    @ApiImplicitParams({
   	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query"),
   	 @ApiImplicitParam(name="comname",value="企业名称",paramType="query"),
   	 @ApiImplicitParam(name="businessid",value="行业id",paramType="query"),
   	 @ApiImplicitParam(name="publishtime",value="发布时间",paramType="query"),
   	 @ApiImplicitParam(name="pageNum",value="当前页码数",paramType="query"),
   	 @ApiImplicitParam(name="pageSize",value="每页显示记录数量",paramType="query")
   })
	@ResponseBody
	@RequestMapping(value="/findPurchaseOrderList",method= RequestMethod.POST )
	public Result<PageInfo<Order>> findSupplyOrderList(
			String orderNamech,
			String comname,
			String businessid,
			String publishtime,
			int pageNum//,
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
	@ResponseBody
	@RequestMapping(value="/findPurchaseOrderByid", method = RequestMethod.GET)
	public Result<Order> findPurchaseOrderByid(String orderid ) {
		Order order = this.orderService.findOrderById(orderid);
		// BeanUtils.copyProperties(temp, order);

		return new Result<Order>(200, "success", order);
	}
	@ApiOperation(value="预览采购订单")
	@ResponseBody
	@RequestMapping(value="/previewPurchaseOrder",method= RequestMethod.POST )
	public Result<Order> previewPurchaseOrder(Order order){
		Map<Integer,String> map =UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("0");    // Purchase order flag
		order.setPublishflag("0");   // not published
		String clickcount = order.getClickcount();
		if(null == clickcount || "".equals(clickcount)){
			order.setClickcount("1");
		}
		String publishtime = order.getPublishtime();
		
		if(null == publishtime || "".equals(publishtime)){
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date(System.currentTimeMillis());
			
			order.setPublishtime(formatter.format(date));
		}
		String orderid = order.getOrderid();
		if(null == orderid || "".equals(orderid)){
			this.orderService.addOrder(order);
		}else{
			this.orderService.updateOrder(order);
		}
		
		
		Result<Order> r =  new Result<Order>(Integer.parseInt(order.getOrderid()),"success");
		
		return r;
		
	}	
	
/*	@ResponseBody
	@RequestMapping(value="/saveandpreviewPurchaseOrder",method= RequestMethod.POST )
	public Result saveandpreviewOrder(HttpServletRequest request,Order order){
		Map<Integer,String> map =UploadUtil.imgsUpload(request, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("1");
		order.setPublishflag("0");
		order.setTradeflag("0");
		
		this.orderService.addOrder(order);
		
		Result r = new Result();
		
		return r;
		
	}	
	
    @ApiOperation(value="预览询采购订单，根据ID查询采购订单")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })     
	@ResponseBody
	@RequestMapping(value="/previewPurchaseOrder", method = RequestMethod.GET)
	public Result<Order> previewOrder(String orderid ) {
		Order order = this.orderService.findOrderById(orderid);
		// BeanUtils.copyProperties(temp, order);

		return new Result<Order>(200, "success", order);
	}
  
    @ApiOperation(value="修改采购订单")
	@ResponseBody
	@RequestMapping(value="/updateOrder", method = RequestMethod.POST)
	public Result updateOrder(Order order) {
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		if(map.get(0)!=null)
		order.setImg1(map.get(0));
		if(map.get(1)!=null)
		order.setImg2(map.get(1));
		if(map.get(2)!=null)
		order.setImg3(map.get(2));
		order.setSupplyflag("0");
		//order.setPublishflag("0");
		this.orderService.updateOrder(order);

		return new Result(200,"success");

	}
    */
    @ApiOperation(value="发布采购订单信息")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })
	@ResponseBody
	@RequestMapping(value="/publishPurchaseOrder", method = RequestMethod.GET)
	public Result publishPurchaseOrder(String orderid) {
		this.orderService.updatepublish(orderid);
		return new Result(200,"success");
	}
    
    @ApiOperation(value="删除采购订单信息")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })
	@ResponseBody
	@RequestMapping(value="/deletePurchaseOrder", method = RequestMethod.GET)
	public Result deletePurchaseOrder(String orderid) {
		this.orderService.deleteOrder(orderid);
		return new Result(200,"success");
	}
    
//修改    
    @RequestMapping(value = "/updatePurchaseOrder", method = RequestMethod.POST)
	public String updatePurchaseOrder( Order order) {
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		if(map.get(0)!=null)
		order.setImg1(map.get(0));
		if(map.get(1)!=null)
		order.setImg2(map.get(1));
		if(map.get(2)!=null)
		order.setImg3(map.get(2));
		order.setSupplyflag("0");
		String clickcount = order.getClickcount();
		if(null == clickcount || "".equals(clickcount)){
			order.setClickcount("1");
		}
		String publishtime = order.getPublishtime();
		
		if(null == publishtime || "".equals(publishtime)){
			order.setPublishtime("20210221");
		}
		this.orderService.updateOrder(order);

		//return new Result(200,"success");
		return "redirect:procurement.html";
	}

    //add 
    @RequestMapping(value = "/addPurchaseOrder", method = RequestMethod.POST)
	public String addPurchaseOrder(Order order) {
  
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("0");  //  supply order
		order.setPublishflag("1"); //  not published
		//order.setTradeflag("0");   //  交易进行中
		String clickcount = order.getClickcount();
		if(null == clickcount || "".equals(clickcount)){
			order.setClickcount("1");
		}
		String publishtime = order.getPublishtime();
		
		if(null == publishtime || "".equals(publishtime)){
			order.setPublishtime("20210221");
		}
		if("0".equals(order.getAddflag())){
			int flag = this.orderService.addOrder(order);
			//this.orderService.updatepublish(order.getOrderid());
		}else{
			
			this.orderService.updatepublish(order.getOrderid());
		}
		
		
		//Result<Order> r =  new Result<Order>(200,"success");
		return "redirect:procurement.html";

	}    
}
