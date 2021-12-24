package com.highcom.admin.service.Impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.highcom.admin.dao.AdminInfoMapper;
import com.highcom.admin.pojo.AdminInfo;
import com.highcom.admin.service.AdminInfoService;

@Service
public class AdminInfoServiceImpl implements AdminInfoService{
	@Resource
	private AdminInfoMapper adminInfoMapper;
	/**
	 * 查询所有管理员
	 */
	public List<AdminInfo> selectAll() {
		return adminInfoMapper.selectAll();
	}
	
	
	/**
	 * 给管理员做删除标记
	 */
	public int delAdminById(int id) {
		return adminInfoMapper.delAdminById(id);
	}


	/**
	 * 根据id查询管理员信息
	 */
	public AdminInfo getAdminInfoById(int id) {
		return adminInfoMapper.getAdminInfoById(id);
	}


	/**
	 * 更新管理员信息
	 */
	public int updateAdmin(AdminInfo admin) {
		return adminInfoMapper.updateAdmin(admin);
		
	}


	/**
	 * 添加管理员
	 */
	public int addAdmin(AdminInfo admin) {
		return adminInfoMapper.addAdmin(admin);
	}


	@Override
	public AdminInfo login(String adminName, String password) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("adminName", adminName);
		map.put("password", password);
		return adminInfoMapper.login(map);
	}


	@Override
	public String updatePwd(Integer id, String password) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		map.put("password", password);
		int result=adminInfoMapper.updatePwd(map);
		if(result>0) {
			return "success";
		}
		return "error";
	}
	/**
	 * 检查用户名是否重复
	 */
	public AdminInfo getAdminByName(String adminName) {
		return adminInfoMapper.getAdminByName(adminName);
	}


	@Override
	public String queryAdminCountByPhone(String phone) {
		int count=adminInfoMapper.queryAdminCountByPhone(phone);
		if(count>0) {
			return "exist";
		}
		return "noexist";
	}


}
