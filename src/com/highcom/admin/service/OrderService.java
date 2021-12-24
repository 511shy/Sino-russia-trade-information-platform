package com.highcom.admin.service;

import java.util.List;

import com.highcom.admin.pojo.Order;

public interface OrderService {

	
	public int addOrder(Order order);
	
	public List<Order> findOrderList(Order order);
	
	public Order findOrderById(String orderid);
	
	public int updateOrder(Order order);
	
	public int deleteOrder(String orderid);
	
	public int updateclickcount(String orderid);
	
	public int updatepublish(String orderid);
	
	public List<Order> findRecommonOrder();
	

}
