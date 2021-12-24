package com.highcom.admin.controller;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.OrderService;
import com.highcom.common.UploadUtil;
import io.swagger.annotations.*;

@Api(tags = "后台管理-国际供应")
@Controller
@RequestMapping("/admin")
public class AdminSupplyOrderController {
	@Autowired
    private AssignConfig assignConfig;
	
	@Autowired
	private OrderService orderService;
	
    @ApiOperation(value="查询供应订单列表")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query"),
    	 @ApiImplicitParam(name="comname",value="企业名称",paramType="query"),
    	 @ApiImplicitParam(name="businessid",value="行业id",paramType="query"),
    	 @ApiImplicitParam(name="publishtime",value="发布时间",paramType="query"),
    	 @ApiImplicitParam(name="pageNum",value="当前页码数",paramType="query"),
    	 @ApiImplicitParam(name="pageSize",value="每页显示记录数量",paramType="query")
    })
	@ResponseBody
	@RequestMapping(value = "/findSupplyOrderList", method = RequestMethod.POST)
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
		//System.out.println(pageInfo.getList());
		return new Result<PageInfo<Order>>(200, "success", pageInfo);

	}
    /*

    @ApiOperation(value="添加供应订单")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query")
 
    })    
	@ResponseBody
	@RequestMapping(value = "/saveSupplyOrder", method = RequestMethod.POST)
	public Result<Order> saveOrder(Order order) {
    	
  
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("1"); // supply order
		order.setPublishflag("1"); // not published
		//order.setTradeflag("0"); //
		int flag = this.orderService.addOrder(order);
		Result<Order> r =  new Result<Order>(200,"success");

		return r;

	}
*/

    @ApiOperation(value="根据id查询供应订单")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })     
	@ResponseBody
	@RequestMapping(value="/findSupplyOrderByid", method = RequestMethod.GET)
	public Result<Order> previewOrder(String orderid ) {
		Order order = this.orderService.findOrderById(orderid);
		// BeanUtils.copyProperties(temp, order);

		return new Result<Order>(200, "success", order);
	}

    
    @ApiOperation(value="修改供应订单")
	@RequestMapping(value="/updateSupplyOrder", method = RequestMethod.POST)

	public String updateOrder( Order order) {
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		if(map.get(0)!=null)
		order.setImg1(map.get(0));
		if(map.get(1)!=null)
		order.setImg2(map.get(1));
		if(map.get(2)!=null)
		order.setImg3(map.get(2));
		order.setSupplyflag("1");
		//order.setPublishflag("0");
		this.orderService.updateOrder(order);
		
		//return new Result(200,"success");
		return "redirect:supply.html";
	}
	@ApiOperation(value="预览采购订单")
	@ResponseBody
	@RequestMapping(value="/previewSupplyOrder",method= RequestMethod.POST )
	public Result<Order> previewSupplyOrder(Order order){
		Map<Integer,String> map =UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("1");    // Purchase order flag
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
//添加订单    
    @RequestMapping(value = "/addSupplyOrder", method = RequestMethod.POST)
	public String addOrder(Order order) {
  
		Map<Integer, String> map = UploadUtil.imgsUpload(assignConfig.uploadpath, order.getFiles());
		order.setImg1(map.get(0));
		order.setImg2(map.get(1));
		order.setImg3(map.get(2));
		order.setSupplyflag("1");  //  supply order
		order.setPublishflag("1"); //  published
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
		
		return "redirect:supply.html";

	}    
    
    @ApiOperation(value="根据主键查询订单信息")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })  
	@ResponseBody
	@RequestMapping(value="/findOrderById", method = RequestMethod.GET)
	public Result<Order> findOrderById(String orderid) {
		Order temp = this.orderService.findOrderById(orderid);
		// BeanUtils.copyProperties(temp, order);
		return new Result<Order>(200, "success", temp);
	}
    
    @ApiOperation(value="发布订单信息")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })
	@ResponseBody
	@RequestMapping(value="/publishOrder", method = RequestMethod.GET)
	public Result publishOrder(String orderid) {
		this.orderService.updatepublish(orderid);
		return new Result(200,"success");
	}

    @ApiOperation(value="删除订单信息")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderid",value="订单主键",paramType="query")
 
    })
	@ResponseBody
	@RequestMapping(value="/deleteOrder", method = RequestMethod.GET)
	public Result deleteOrder(String orderid) {
		this.orderService.deleteOrder(orderid);
		return new Result(200,"success");
	}
 
    /*
    @ApiOperation(value="layui查询供应订单列表")
    @ApiImplicitParams({
    	 @ApiImplicitParam(name="orderNamech",value="订单名称",paramType="query"),
    	 @ApiImplicitParam(name="comname",value="企业名称",paramType="query"),
    	 @ApiImplicitParam(name="businessid",value="行业id",paramType="query"),
    	 @ApiImplicitParam(name="publishtime",value="发布时间",paramType="query"),
    	 @ApiImplicitParam(name="pageNum",value="当前页码数",paramType="query"),
    	 @ApiImplicitParam(name="pageSize",value="每页显示记录数量",paramType="query")
    })
	@ResponseBody
	@RequestMapping(value = "/layuiSupplyOrderList", method = RequestMethod.GET)
	public Result<PageInfo<Order>> layuiSupplyOrderList(
			String orderNamech,
			String comname,
			String businessid,
			String publishtime,
			int pageNum,
			int pageSize
			) {
    	Order order = new Order();
    	
		// set supply flag as 1 to find supply orders.
    	order.setOrderNamech(orderNamech);
    	order.setComname(comname);
    	order.setBusinessid(businessid);
    	order.setPublishtime(publishtime);
    	order.setPageNum(pageNum);
    	order.setPageSize(pageSize);
		order.setSupplyflag("1");
		PageHelper.clearPage();
		PageHelper.startPage(order.getPageNum(), order.getPageSize());

		List<Order> list = this.orderService.findOrderList(order);
		PageInfo<Order> pageInfo = new PageInfo<>(list, order.getPageSize());
		return new Result<PageInfo<Order>>(200, "success", pageInfo);

	}
    */
}
