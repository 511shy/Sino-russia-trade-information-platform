package com.highcom.admin.dao;
import java.util.List;
import java.util.Map;

import com.highcom.admin.pojo.AdminInfo;

public interface AdminInfoMapper {

	/**
	 * 查询所有管理员
	 * @return 管理员列表
	 */
	List<AdminInfo> selectAll();

	/**
	 * 做删除标记
	 * @param id 管理员id
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
	 * @return 成功返回1
	 */
	int updateAdmin(AdminInfo admin);

	/**
	 * 添加管理员
	 * @param admin
	 * @return 成功返回1
	 */
	int addAdmin(AdminInfo admin);
	
	/**
	 * 登陆
	 */
	public AdminInfo login(Map<String,String> map);
	
	public int updatePwd(Map<String,Object> map);

	/**
	 * 根据用户名查询用户(用于检查账号是否可用)
	 * @param adminName
	 * @return 用户信息
	 */
	AdminInfo getAdminByName(String adminName);
	
	public int queryAdminCountByPhone(String phone);
}
