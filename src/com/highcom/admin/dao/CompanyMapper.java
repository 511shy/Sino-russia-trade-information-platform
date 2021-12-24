package com.highcom.admin.dao;
import java.util.List;

import com.highcom.admin.pojo.Company;

public interface CompanyMapper {
	
	public int addcompany(Company comany);
	
	public List<Company> findVipCompanyList(Company company);
	
	public List<Company> findNormalCompanyList(Company company);
	
	public Company findCompanyById(String comid);
	
	public int updateCompany(Company comany);
	
	public int deleteCompany(String comid);

	public Company findCompanyByName(String comName);

	public Company findVipCompanyByUserName(String userName);

	public List<Company> findVipCompanyListByCategory(int category);
	
}
