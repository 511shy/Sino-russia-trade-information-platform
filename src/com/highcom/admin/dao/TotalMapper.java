package com.highcom.admin.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.TotalInfo;

/**
 * 汇总报表
 */
public interface TotalMapper {
	/**
	 * 查询订单总数
	 */
	int getOrderCount();

	/**
	 * 查询已完成或未完成的订单数量
	 * @param flag 0为未完成,1为已完成
	 */
	int getOrderCountByTradeFlag(String flag);

	/**
	 * 查询采购或供应订单的数量
	 * @param flag 0 采购 1供应
	 */
	int getOrderCountBySupplyflag(String flag);

	/**
	 * 查询采购或供应订单的数量(按年,统计出具体某月,用于条形图)
	 * @param year 年份
	 * @param supplyflag 订单类型 (0 采购 1供应)
	 * @param tradeflag 订单状态(0 未完成, 已完成)
	 * @return 一个装有月份和数量的对应关系的map集合
	 */
	List<TotalInfo>  totalOrderByYear(@Param("year") Integer year,@Param("supplyflag") String supplyflag,@Param("tradeflag") String tradeflag);

	/**
	 * 得到企业总数
	 * @return 企业总数
	 */
	int getCompanyCount();

	/**
	 * 得到VIP企业数量,注意,VIP企业是根据VIP到期时间来进行区分的
	 * @return vip企业数量
	 */
	int getVipCompanyCount();
	
	/**
	 * 统计某年下的VIP企业数量,按月分组
	 * @param year 年度
	 * @param month 月份
	 * @param vipEnd 用本年月拼成的时间字符串
	 * @return 分组后每个月对应的vip企业数量
	 */
	
	/**
	 * 统计某年某月企业数量
	 * @param year
	 * @param month
	 * @return 数量
	 */
	int getCompanyCountByYM(@Param("year") Integer year, @Param("month") Integer month);

	/**
	 * 统计某年某月注册的企业,其中VIP企业的数量
	 * @param year 年度
	 * @param month 月份
	 * @return 数量
	 */
	int getVipCompanyCountByYM(@Param("year") Integer year, @Param("month") Integer month);

	/**
	 * 查询订单访问量
	 * @param supplyflag 采购为0 供应为1
	 * @return 数量
	 */
	Integer getOrderClickCount(String supplyflag);

	/**
	 * 得到点击量前n的某类订单
	 * @param n 查询出多少个
	 * @param supplyflag 订单类型( 0采购 1供应)
	 * @return 前n条订单信息
	 */
	List<Order> getTopClick(@Param("n") Integer n, @Param("supplyflag") String supplyflag);

	/**
	 * 得到订单对应的年份列表
	 * @return 年份列表
	 */
	List<String> getOrderYears();

	/**
	 * 得到公司对应的年份列表
	 * @return 年份列表
	 */
	List<String> getCompanyYears();

}
