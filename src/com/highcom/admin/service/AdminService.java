package com.highcom.admin.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.highcom.admin.pojo.HomeInfo;
import com.highcom.admin.pojo.Order;

public interface AdminService {

	public boolean updateHomeInfo(MultipartFile fileImg, HomeInfo homeInfo);
	
	public List<HomeInfo> queryHomeInfo();
	
	public List<HomeInfo> queryHomeInfoByContentType(Integer contentType);
	
	public List<Order> queryOrderByCondition(Integer count,Integer flag);
	
	public List<HomeInfo> queryPagefooter();
}
