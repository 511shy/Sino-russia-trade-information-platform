package com.highcom.admin.service;
import java.util.List;
import com.highcom.admin.pojo.AdminInfo;
import com.highcom.admin.pojo.Company;

/**
 * 后台管理员
 */
public interface AdminInfoService {

	/**
	 * 查询所有
	 */
	List<AdminInfo> selectAll();

	/**
	 * 添加删除标记
	 * @param id 管理员
	 */
	int delAdminById(int id);

	/**
	 * 根据id查询管理员信息
	 * @param id 管理员id
	 * @return 管理员信息
	 */
	AdminInfo getAdminInfoById(int id);

	/**
	 * 更新管理员信息
	 * @param admin 管理员信息
	 * return  成功返回1
	 */
	int updateAdmin(AdminInfo admin);

	/**
	 * 添加管理员
	 * @param admin 管理员信息
	 * return  成功返回1
	 */
	int addAdmin(AdminInfo admin);
	
	/*
	 * 登陆
	 */
	public AdminInfo login(String adminName,String password);
	
	public String updatePwd(Integer id,String password);

	/**
	 * 根据名字查询用户(用于账号重复验证)
	 * @param adminName 账号
	 * return 查到的第一条信息
	 */
	AdminInfo getAdminByName(String adminName);
	
	public String queryAdminCountByPhone(String phone);
	
}
