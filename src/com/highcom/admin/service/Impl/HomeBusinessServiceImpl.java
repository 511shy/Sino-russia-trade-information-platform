package com.highcom.admin.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highcom.admin.dao.HomeBusinessMapper;
import com.highcom.admin.pojo.Business;
import com.highcom.admin.service.HomeBusinessService;

@Service
public class HomeBusinessServiceImpl implements HomeBusinessService {

	@Autowired
    private HomeBusinessMapper homeBusinessMapper;
	
	@Override
	public List<Business> queryBusiness() {
		return homeBusinessMapper.queryBusiness();
	}

}
