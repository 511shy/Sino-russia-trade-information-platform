package com.highcom.admin.service.Impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.highcom.admin.dao.CompanyMapper;
import com.highcom.admin.pojo.Company;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyMapper companyMapper;
	@Override
	public int addcompany(Company company) {
		return this.companyMapper.addcompany(company);
	}

	@Override
	public List<Company> findVipCompanyList(Company company) {
		return this.companyMapper.findVipCompanyList( company);
	}

	@Override
	public List<Company> findNormalCompanyList(Company company) {
		return this.companyMapper.findNormalCompanyList( company);
	}

	@Override
	public Company findCompanyById(String comid) {
		Company c= this.companyMapper.findCompanyById(comid);
		c.setMainImage(AssignConfig.virtualUrl+c.getMainImage());   //virtualUrl 来源于配置文件,值为 homeImages/
		return c;
	}

	@Override
	public int updateCompany(Company comany) {
		return this.companyMapper.updateCompany(comany);
	}

	@Override
	public int deleteCompany(String comid) {
		return this.companyMapper.deleteCompany(comid);
	}

	@Override
	public Company findCompanyByName(String comName) {
		return this.companyMapper.findCompanyByName(comName);
	}

	@Override
	public Company findVipCompanyByUserName(String userName) {
		return this.companyMapper.findVipCompanyByUserName(userName);
	}

	@Override
	public List<Company> findVipCompanyListByCategory(int category) {
		 List<Company> list= this.companyMapper.findVipCompanyListByCategory(category);
		for(Company c:list) {
			c.setMainImage(AssignConfig.virtualUrl+c.getMainImage());   //virtualUrl 来源于配置文件,值为 homeImages/
		}
		
		return list;
	}
}
