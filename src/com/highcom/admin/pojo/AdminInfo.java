package com.highcom.admin.pojo;

/**
 * 后台管理员信息
 */
public class AdminInfo {
	private int id; 
	//登录账号
	private String adminName;
	private String password;
	
	//真实姓名
	private String realName;
	private String phone;
	
	//添加时间
	private String addTime;
	
	//角色,只有两种,普通管理员0,超级管理员1 
	private String role;
	
	//删除标记 0 正常  1 删除
	private String delFlag;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
}
