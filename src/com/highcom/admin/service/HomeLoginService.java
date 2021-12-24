package com.highcom.admin.service;

import com.highcom.admin.pojo.Company;

public interface HomeLoginService {

	public Company login(String userName,String password);
	
	public String queryCompanyCountByName(String userName);
	
	public String queryCompanyCountByComName(String comName);
	public String queryCompanyCountByTelephone(String telephone);
	
	public String register(String userName,String password,String telephone,String comName,String contacts,String management);
	
	public String updatePwd(Integer comid,String password);
	
	public Company queryCompanyByName(String userName);
	
	public String updateCompany(String comid,String userName,String telephone,String comName,String contacts,String management);
	
}
