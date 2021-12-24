package com.highcom.admin.service.Impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.highcom.admin.dao.HomeLoginMapper;
import com.highcom.admin.pojo.Company;
import com.highcom.admin.service.HomeLoginService;

@Service
public class HomeLoginServiceImpl implements HomeLoginService {
	
	@Autowired
	private HomeLoginMapper homeLoginMapper;
	
	@Override
	public Company login(String userName, String password) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("userName", userName);
		map.put("password", password);
		return homeLoginMapper.login(map);
	}

	@Override
	public String queryCompanyCountByName(String userName) {
		int count=homeLoginMapper.queryCompanyCountByName(userName);
		if(count>0) {
			return "exist";
		}
		return "noexist";
	}
	

	@Override
	public String register(String userName, String password, String telephone,String comName,String contacts,String management) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("userName", userName);
		map.put("password", password);
		map.put("telephone", telephone);
		map.put("comName", comName);
		map.put("contacts", contacts);
		map.put("management", management);
		Date d=new Date();   
	    SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
		String currentTime=df.format(d);
		map.put("registerTime",currentTime ); //注册时间
		map.put("deleteFlag", "0"); //删除标记  0有效  1 删除状态
		map.put("role", "0");		//0 普通 1 vip  3 admin  7 supper admin
		map.put("vipend", df.format(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000))); //当前日期减一天
		map.put("vipbegin", currentTime);
		int result=homeLoginMapper.register(map);
		if(result>0) {
			return "success";
		}
		return "error";
	}

	@Override
	public String updatePwd(Integer comid, String password) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("comid", comid);
		map.put("password", password);
		map.put("modifyTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		int result=homeLoginMapper.updatePwd(map);
		if(result>0) {
			return "success";
		}
		return "error";
	}

	@Override
	public Company queryCompanyByName(String userName) {
		return homeLoginMapper.queryCompanyByName(userName);
	}

	@Override
	public String queryCompanyCountByComName(String comName) {
		int count=homeLoginMapper.queryCompanyCountByComName(comName);
		if(count>0) {
			return "exist";
		}
		return "noexist";
	}

	@Override
	public String updateCompany(String comid, String userName, String telephone, String comName, String contacts,
			String management) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("comid", comid);
		map.put("userName", userName);
		map.put("telephone", telephone);
		map.put("comName", comName);
		map.put("contacts", contacts);
		map.put("management", management);
		int result=homeLoginMapper.updateCompany(map);
		if(result>0) {
			return "success";
		}
		return "error";
	}

	@Override
	public String queryCompanyCountByTelephone(String telephone) {
		int count=homeLoginMapper.queryCompanyCountByTelephone(telephone);
		if(count>0) {
			return "exist";
		}
		return "noexist";
	}

}
