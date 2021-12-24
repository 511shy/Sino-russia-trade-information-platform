package com.highcom.admin.controller;
import java.util.*;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.TotalService;

/**
 * 统计信息
 */
@Controller
@RequestMapping("/total")
public class AdminTotalController {
	
	@ResponseBody
	@RequestMapping("/getYears")
	public Result<List<List<String>>>  getOrderYears() {
		List<List<String>> list=new ArrayList<>(2);
		List<String> orderYears=totalService.getOrderYears();
		List<String> companyYears=totalService.getCompanyYears();
		
		list.add(orderYears);
		list.add(companyYears);
		
		return new Result<>(200,"" ,list);
	}
	
	@Resource
	private TotalService totalService;

	/**
	 * 统计 订单总数 已完成订单 未完成订单 采购订单 供应订单
	 */
	@ResponseBody
	@RequestMapping("/totalOrder")
	public Result<Map<String, Integer>> totalOrder() {
		Map<String, Integer> map = totalService.totalOrder();
		return new Result<>(200, "", map);
	}

	/**
	 * 统计 订单信息 (两个条形图, 0采购 1供应)
	 */
	@ResponseBody
	@RequestMapping("/totalOrderByDate")
	public Result <List<List<TotalInfo>>>totalOrderByDate(Integer year) {
		List<List<TotalInfo>> list = new ArrayList<>();

		// 采购订单(已完成,未完成)
		List<TotalInfo> totalList1 = totalService.totalOrderByYear(year, "0", "1");
		List<TotalInfo> totalList2 = totalService.totalOrderByYear(year, "0", "0");

		// 供应单(已完成,未完成)
		List<TotalInfo> totalList3 = totalService.totalOrderByYear(year, "1", "1");
		List<TotalInfo> totalList4 = totalService.totalOrderByYear(year, "1", "0");

		list.add(totalList1);
		list.add(totalList2);
		list.add(totalList3);
		list.add(totalList4);

		return new Result<>(200, "", list);
	}

	/**
	 * 注册企业数据分析: 企业总数,vip企业,普通企业
	 */
	@ResponseBody
	@RequestMapping("/totalCompany")
	public Result<Map<String, Integer>> totalCompany() {
		Map<String, Integer> map = totalService.totalCompany();
		return  new Result<>(200, "", map);
	}

	/**
	 * 统计 企业信息(条形图,按日期)
	 */
	@ResponseBody
	@RequestMapping("/totalCompanyByDate")
	public Result<List<List<TotalInfo> >> totalCompanyByDate(Integer year) {
		List<TotalInfo> listVip = new ArrayList<>();
		List<TotalInfo> listNormal = new ArrayList<>();

		// 企业数量(全部)
		for (int i = 1; i <= 12; i++) {
			int allCount = totalService.getCompanyCountByYM(year, i);
			int vipCount = totalService.getVipCompanyCountByYM(year, i);
			
			listVip.add(new TotalInfo(year,i,vipCount));
			listNormal.add(new TotalInfo(year,i,allCount-vipCount));
		}
				
		List<List<TotalInfo> >list=new ArrayList<>();
		list.add(listVip);
		list.add(listNormal);
		
		return new  Result<>(200, "", list);
	}
	
	/**
	 * 统计订单点击信息 (supplyflag 采购订单 0 ,需求订单 1) 
	 */
	@ResponseBody
	@RequestMapping("/totalOrderClick")
	public Result<Map<String, Integer>>totalOrderClick()
	{
		Map<String, Integer> map = totalService.totalOrderClick();
		return new Result<>(200,"",map);
	}
	
	/**
	 * 统计点击量前n的订单
	 */
	@ResponseBody
	@RequestMapping("/getTopClick")
	public Result <List<List<Order>>>getTopClick(Integer n) {	
		if(n==null) {
			n=10;
		}

		List<List<Order>> list = new ArrayList<>();
		
		// 采购订单
		List<Order> list1 = totalService.getTopClick(n, "0");

		// 供应订单
		List<Order> list2 = totalService.getTopClick(n, "1");

		list.add(list1);
		list.add(list2);
		
		return new Result<>(200, "", list);
	}
}
