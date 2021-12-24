package com.highcom.admin.service;

import java.util.List;

import com.highcom.admin.pojo.Company;

public interface CompanyService {

	
	public int addcompany(Company company);
	
	public List<Company> findVipCompanyList(Company company);
	
	public List<Company> findNormalCompanyList(Company company);
	
	public Company findCompanyById(String comid);
	
	public int updateCompany(Company company);
	
	public int deleteCompany(String comid);

	public Company findCompanyByName(String comName);

	public Company findVipCompanyByUserName(String userName);

	public List<Company> findVipCompanyListByCategory(int i);
	

}
