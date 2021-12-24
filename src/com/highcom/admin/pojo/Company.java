package com.highcom.admin.pojo;      //

import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "企业信息")
public class Company {
	
	@ApiModelProperty(name = "comid",value = "企业id",example = "")
	private String	comid;      // 企业id
	@ApiModelProperty(name = "comName",value = "企业全称",example = "")
	private String	comName;      //企业全称
	@ApiModelProperty(name = "shortComName",value = "企业全称截短",example = "")
	private String	shortComName;      //企业名称,用于在显示的时候处理过长的企业名
	
	//处理企业名称过长的显示
	public String getShortComName() {
		if(comName==null) {
			return  "";
		}
		if(comName.length()<12) {
			return comName;
		}
		else {
			return comName.substring(0,12)+"...";
		}
	}
	
	@ApiModelProperty(name = "contacts",value = "联系人",example = "")
	private String	contacts;      //   联系人
	@ApiModelProperty(name = "telephone",value = " 联系方式",example = "")
	private String	telephone;      //  联系方式
	@ApiModelProperty(name = "userName",value = "用户名",example = "")
	private String	userName;      //   用户名
	@ApiModelProperty(name = "password",value = "登录密码",example = "")
	private String	password;      //   登录密码
	@ApiModelProperty(name = "vipbegin",value = "vip开始时间",example = "")
	private String	vipbegin;      //   vip开始时间
	@ApiModelProperty(name = "vipend",value = "vip到期时间",example = "")
	private String	vipend;          //  vip到期时间
	@Override
	public String toString() {
		return "Company [comid=" + comid + ", comName=" + comName + ", contacts=" + contacts + ", telephone="
				+ telephone + ", userName=" + userName + ", password=" + password + ", vipbegin=" + vipbegin
				+ ", vipend=" + vipend + ", management=" + management + ", recommend=" + recommend + ", category="
				+ category + ", mainImage=" + mainImage + ", brief=" + brief + ", detailed=" + detailed + ", applyTime="
				+ applyTime + ", registerTime=" + registerTime + ", modifyTime=" + modifyTime + ", deleteFlag="
				+ deleteFlag + ", role=" + role + ", vipflag=" + vipflag + ", status=" + status + ", files="
				+ Arrays.toString(files) + ", searchbegin=" + searchbegin + ", searchend=" + searchend
				+ ", confirmpassword=" + confirmpassword + ", message=" + message + ", pageNum=" + pageNum
				+ ", pageSize=" + pageSize + "]";
	}
	@ApiModelProperty(name = "management",value = "主营项目",example = "")
	private String	management;      // 主营项目
	
	@ApiModelProperty(name = "shortManagement",value = "主营项目短显示",example = "")
	private String	shortManagement;      // 主营项目短显示
	
	public String getShortManagement() {
		if(management==null) {
			return  "";
		}
		if(management.length()<15) {
			return management;
		}
		else {
			return management.substring(0,15)+"...";
		}
	}

	@ApiModelProperty(name = "recommend",value = "首页推荐",example = "")
	private Integer		recommend;      //  首页推荐
	@ApiModelProperty(name = "category",value = " 企业类别",example = "")
	private Integer 	category;       //  企业类别
	@ApiModelProperty(name = "mainImage",value = "企业主图路径",example = "")
	private String	mainImage;      // 企业主图
	@ApiModelProperty(name = "brief",value = "企业简介",example = "")
	private String	brief;         // 企业简介
	@ApiModelProperty(name = "detailed",value = "详细介绍",example = "")
	private String	detailed;      //  详细介绍
	@ApiModelProperty(name = "applyTime",value = "申请时间",example = "")
	private String	applyTime;      // 申请时间
	@ApiModelProperty(name = "registerTime",value = "入住时间",example = "")
	private String	registerTime;      //入住时间
	//@ApiModelProperty(name = "",value = "",example = "")
	private String	modifyTime;      // 最后更新时间
	//@ApiModelProperty(name = "",value = "",example = "")
	private String	deleteFlag;      // 删除标记  0有效  1 删除状态
	@ApiModelProperty(name = "",value = "",example = "")
	private String	role;           // 0 普通 1 vip  3 admin  7 supper admin
	//@ApiModelProperty(name = "",value = "",example = "")
	private Integer     vipflag;
	//@ApiModelProperty(name = "",value = "",example = "")
	private Integer     status;          // 状态 0 未发布  1 发布  
	
	@ApiModelProperty(name = "files",value = "上传图片",example = "")
	private MultipartFile [] files;  
	
	@ApiModelProperty(name = "searchbegin",value = "查询开始时间",example = "")
	private String searchbegin;
	@ApiModelProperty(name = "searchend",value = "查询结束时间",example = "")
	private String searchend;
	@ApiModelProperty(name = "confirmpassword",value = "确认密码",example = "")
	private String confirmpassword;
	@ApiModelProperty(name = "message",value = "短信验证码",example = "")
	private String message;
	
	@ApiModelProperty(name = "sortType",value = "排序方式",example = "")
	private String sortType;
	
	
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	private Integer pageNum = 1;
	private Integer pageSize = 5;;
	
	
	
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getVipbegin() {
		return vipbegin;
	}
	public void setVipbegin(String vipbegin) {
		this.vipbegin = vipbegin;
	}
	public String getVipend() {
		return vipend;
	}
	public void setVipend(String vipend) {
		this.vipend = vipend;
	}
	public String getManagement() {
		return management;
	}
	public void setManagement(String management) {
		this.management = management;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public String getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getVipflag() {
		return vipflag;
	}
	public void setVipflag(Integer vipflag) {
		this.vipflag = vipflag;
	}
	
	
}
