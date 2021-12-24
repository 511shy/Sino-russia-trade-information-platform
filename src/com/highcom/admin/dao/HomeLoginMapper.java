package com.highcom.admin.dao;

import java.util.Map;

import com.highcom.admin.pojo.Company;

public interface HomeLoginMapper {

	public Company login(Map<String,String> map);
	
	public int queryCompanyCountByName(String userName);
	public int queryCompanyCountByComName(String comName);
	public int queryCompanyCountByTelephone(String telephone);
	
	
	public int register(Map<String,String> map);
	
	public int updatePwd(Map<String,Object> map);
	
	public int updateCompany(Map<String,Object> map);
	
	public Company queryCompanyByName(String userName);
	
}
