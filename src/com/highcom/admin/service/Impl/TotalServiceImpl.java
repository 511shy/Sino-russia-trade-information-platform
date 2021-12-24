package com.highcom.admin.service.Impl;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.highcom.admin.dao.TotalMapper;
import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.TotalInfo;
import com.highcom.admin.service.TotalService;

/**
 * 汇总报表
 */
@Service
public class TotalServiceImpl implements TotalService {	
	@Resource
	private TotalMapper totalMapper; 
	
	/**
	 * 汇总订单信息 订单总数  已完成订单  未完成订单 采购订单 供应订单
	 */
	public Map<String, Integer> totalOrder() {
		//订单总数
		int orderCount =totalMapper.getOrderCount();
		//已完成订单
		int finishCount=totalMapper.getOrderCountByTradeFlag("1");  //交易状态  0交易中   1 已完结
		//未完成订单
		int noFinishCount=orderCount-finishCount;
		//采购订单
		int purchaseCount =totalMapper.getOrderCountBySupplyflag("1");  //采购供应标记  0采购 1供应
		//供应订单
		int supplyCount=orderCount-purchaseCount;
		
		Map <String,Integer>map=new LinkedHashMap<>(4);
		
		map.put("订单总数_供应+采购,已完成+未完成",orderCount);
		map.put("已完成订单_供应+采购",finishCount);
		map.put("未完成订单_供应+采购",noFinishCount);
		map.put("采购订单_已完成+未完成",purchaseCount);
		map.put("供应订单_已完成+未完成",supplyCount);
		
		return map;
	}

	/**
	 * 按年统计订单数据(用于条形图)
	 */
	public List<TotalInfo> totalOrderByYear(Integer year, String supplyflag,String tradeflag) {
		 List<TotalInfo> list=totalMapper.totalOrderByYear( year,  supplyflag,tradeflag);
		 
		 //把list处理一下,让它添满12个月
		 List<TotalInfo> fullList=new ArrayList<>(12);
		 
		 for(int i=1;i<=12;i++) {
			 TotalInfo node=new TotalInfo(i);
			 
			 int index=list.indexOf(node);
			 if(index!=-1) {
				 node=list.get(index);
			 }
			 
			 fullList.add(node);
		 }	 

		return fullList;
	}

	/**
	 * 统计企业数量,vip企业数量,普通企业数量
	 */
	public Map<String, Integer> totalCompany() {
		//企业总数
		int companyCount =totalMapper.getCompanyCount();
		//vip企业数量
		int vipCount=totalMapper.getVipCompanyCount(); 
		//普通企业数量
		int noVipCount=companyCount-vipCount;
	
		Map <String,Integer>map=new LinkedHashMap<>(3);
		
		map.put("企业总数_VIP+普通",companyCount);
		map.put("VIP企业_注册企业",vipCount);
		map.put("普通企业_注册企业",noVipCount);
	
		return map;
	}

	/**
	 * 统计某年某月企业数量
	 */
	public int getCompanyCountByYM(Integer year,Integer month) {
		return totalMapper.getCompanyCountByYM(year,month);
	}
	
	/**
	 * 统计某年某月vip企业数量
	 */
	public int getVipCompanyCountByYM(Integer year,Integer month) {
		return totalMapper.getVipCompanyCountByYM(year,month);
	}

	/**
	 * 订单点击量统计
	 */
	public Map<String, Integer> totalOrderClick() {
		//采购订单点击量
		Integer count1=totalMapper.getOrderClickCount("0"); 
		if(count1==null) {
			count1=0;
		}
		
		//供应订单点击量
		Integer count2=totalMapper.getOrderClickCount("1"); 
		if(count2==null) {
			count2=0;
		}
		
		//总点击总量
		Integer clickCount =count1+count2;
		
		Map <String,Integer>map=new HashMap<>(3);
		
		map.put("订单总访问量_采购+供应",clickCount);
		map.put("采购订单访问量_注册企业",count1);
		map.put("供应订单访问量_注册企业",count2);
	
		return map;
	}

	/**
	 * 得到点击量前n的某类订单列表
	 */
	public List<Order> getTopClick(Integer n, String supplyflag) {
		return totalMapper.getTopClick(n,supplyflag);
	}

	/**
	 * 返回订单对应的年份列表
	 */
	public List<String> getOrderYears() {
		return totalMapper.getOrderYears();
	}
	
	/**
	 * 返回订单对应的年份列表
	 */
	public List<String> getCompanyYears() {
		return totalMapper.getCompanyYears();
	}

}
