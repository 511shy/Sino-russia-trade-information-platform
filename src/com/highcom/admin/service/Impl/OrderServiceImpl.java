package com.highcom.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highcom.admin.dao.OrderMapper;
import com.highcom.admin.pojo.Order;
import com.highcom.admin.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;
	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		return this.orderMapper.addOrder(order);
	}

	@Override
	public List<Order> findOrderList(Order order) {
		// TODO Auto-generated method stub
		return this.orderMapper.findOrderList(order);
	}

	@Override
	public Order findOrderById(String orderid) {
		// TODO Auto-generated method stub
		updateclickcount(orderid);
		return this.orderMapper.findOrderById(orderid);
	}

	@Override
	public int updateOrder(Order order) {
		// TODO Auto-generated method stub
		return this.orderMapper.updateOrder(order);
	}

	@Override
	public int deleteOrder(String orderid) {
		// TODO Auto-generated method stub
		return this.orderMapper.deleteOrder(orderid);
	}

	@Override
	public int updateclickcount(String orderid) {
		// TODO Auto-generated method stub
		return this.orderMapper.updateclickcount(orderid);
	}

	@Override
	public int updatepublish(String orderid) {
		// TODO Auto-generated method stub
		return this.orderMapper.updatepublish(orderid);
	}

	@Override
	public List<Order> findRecommonOrder() {
		// TODO Auto-generated method stub
		return this.orderMapper.findRecommonOrder();
	}

	
	
	

}
