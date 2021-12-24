package com.highcom.admin.service;

import java.util.List;
import java.util.Map;

import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.TotalInfo;

/**
 * 汇总报表
 */
public interface  TotalService {
	/**
	 * 订单信息汇总 (订单总数  已完成订单  未完成订单 采购订单 供应订单)
	 * @return 汇总后的map集合
	 */
	Map <String,Integer>totalOrder();

	/**
	 * 按年统计订单数据(用于条形图)
	 * @param year 年度
	 * @param supplyflag 订单类型( 0采购 1供应)
	 * @param tradeflag 完成状态( 0未完成 1已完成)
	 */
	List<TotalInfo> totalOrderByYear(Integer year,String supplyflag,String tradeflag);

	/**
	 * 企业信息统计 企业总数, vip企业数,普通企业数
	 * @return 汇总后的map集合
	 */
	Map<String, Integer> totalCompany();

	/**
	 * 企业信息统计,得到某年下某月所有企业总数
	 * @param year 年份
	 * @param month 月份
	 * @return 该年该月下的企业总数
	 */
	public int getCompanyCountByYM(Integer year,Integer month) ;
	/**
	 * 企业信息统计,得到某年下某月所有vip企业总数
	 * @param year 年份
	 * @param month 月份
	 * @return 该年该月下的vip企业总数
	 */
	public int getVipCompanyCountByYM(Integer year,Integer month) ;

	/**
	 * 订单访问信息统计,总点击量,需求订单点击量,采购订单点击量
	 * @return 汇总后的map集合
	 */
	Map<String, Integer> totalOrderClick();

	/**
	 * 得到点击量前n的某类订单
	 * @param n 查询出多少个
	 * @param supplyflag 订单类型( 0采购 1供应)
	 * @return 前n条订单信息
	 */
	List<Order> getTopClick(Integer n, String supplyflag);

	/**
	 * 得到订单都跨了哪些年份
	 * @return 年份列表
	 */
	List<String> getOrderYears();

	/**
	 * 得到公司注册都跨了哪些年份
	 * @return 年份列表
	 */
	List<String> getCompanyYears();
}
