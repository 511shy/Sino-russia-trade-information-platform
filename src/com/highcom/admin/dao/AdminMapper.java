package com.highcom.admin.dao;

import java.util.List;
import java.util.Map;

import com.highcom.admin.pojo.HomeInfo;
import com.highcom.admin.pojo.Order;

public interface AdminMapper {

	public int updateHomeInfo(HomeInfo homeInfo);
	public List<HomeInfo> queryHomeInfo();
	public List<HomeInfo> queryHomeInfoByContentType(Integer contentType);
	
	public List<Order> queryOrderByCondition1(Integer count);
	public List<Order> queryOrderByCondition2(Integer count);
	
	public List<HomeInfo> queryPagefooter();
}
