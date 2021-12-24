package com.highcom.admin.pojo;

/**
 * 系统日志
 */
public class LogInfo {
	private int id;
	
	//操作时间
	private String logTime;
	
	//管理员账号
	private String adminName;
	
	//所属模块
	private String module;
	
	//操作概述
	private String title;
	
	//具体信息
	private String content;
	
	//查询起始时间(非数据库字段)
	private String searchbegin;
	
	//查询结束时间(非数据库字段)
	private String searchend; 


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogTime() {
		return logTime;
	}

	public void setLogTime(String logTime) {
		this.logTime = logTime;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSearchbegin() {
		return searchbegin;
	}

	public void setSearchbegin(String searchbegin) {
		this.searchbegin = searchbegin;
	}

	public String getSearchend() {
		return searchend;
	}

	public void setSearchend(String searchend) {
		this.searchend = searchend;
	}
	
	

}
