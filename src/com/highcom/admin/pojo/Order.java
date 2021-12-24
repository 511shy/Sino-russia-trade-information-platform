package com.highcom.admin.pojo;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(description = "订单信息")
public class Order {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "orderid",value = "主键",example = "1")
	private String 	orderid;
	@ApiModelProperty(name = "orderNamech",value = "中文名称",example = "")
	private String 	orderNamech;// 中文名称
	@ApiModelProperty(name = "orderNameen",value = "英文名称",example = "")
	private String 	orderNameen;// 英文名称
	@ApiModelProperty(name = "summary",value = "订单摘要",example = "")	
	private String 	summary;// 订单摘要
	@ApiModelProperty(name = "businessid",value = "行业类别id",example = "")
	private String 	businessid;// 行业类别
	@ApiModelProperty(name = "publishtime",value = "发布时间",example = "")
	
	private String 	publishtime;// 发布时间
	@ApiModelProperty(name = "img1",value = "图片路径",example = "")
	private String 	img1;// 图片路径
	@ApiModelProperty(name = "img2",value = "图片路径",example = "")
	private String 	img2;// 图片路径
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	img3;// 图片路径
	@ApiModelProperty(name = "clickcount",value = "点击量",example = "")
	private String 	clickcount;// 点击量
	@ApiModelProperty(name = "goodsname",value = "商品名称",example = "")
	private String 	goodsname;// 商品名称
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	number;// 商品数量
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	categoryid;// 商品类型
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	specifications;// 商品规格
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	price;//商品价格
	@ApiModelProperty(name = "",value = " ",example = "")
	private String 	supplytime;// 供应时间
	@ApiModelProperty(name = "comid",value = "发布企业id",example = "")
	private String 	comid;// 发布企业id
	@ApiModelProperty(name = "details",value = "详细介绍",example = "")
	private String 	details;// 详细介绍  
	@ApiModelProperty(name = "recomflag",value = "是否推荐   0 不推荐  1 推荐",example = "")
	private String 	recomflag;// 是否推荐   0 不推荐  1 推荐
	@ApiModelProperty(name = "publishflag",value = "发布状态  0 未发布  1 已发布",example = "")
	private String 	publishflag;// 发布状态  0 未发布  1 已发布
	@ApiModelProperty(name = "tradeflag",value = "交易状态  0交易中   1 已完结",example = "0")
	private String 	tradeflag;// 交易状态  0交易中   1 已完结
	@ApiModelProperty(name = "supplyflag",value = "采购供应标记  0采购 1供应",example = "0")
	private String 	supplyflag;// 采购供应标记  0采购 1供应

	private String 	delflag;// 删除标记  0未删除   1 删除

	private String 	updatetime;// 修改时间
	@ApiModelProperty(name = "comname",value = " 所属企业名称",example = "")
	private String 	comname;// 所属企业名称
	@ApiModelProperty(name = "category",value = "所属品类名称 ",example = "")
	private String 	category;//所属品类名称

	@ApiModelProperty(name = "files",value = "上传3个图片 ",example = "")
	private MultipartFile [] files;  
	
	private String searchbegin;
	private String searchend;
	@ApiModelProperty(name = "pageNum",value = "当前页码数",example = "")
	private int pageNum = 1;
	@ApiModelProperty(name = "pageSize",value = "每页显示记录数量 ",example = "")
	private int pageSize = 5;
	private  String businessName;
	private String	detailed;  
	private String addflag;
	private String	shortOrderName;      //企业名称,用于在显示的时候处理过长的企业名
	private String  shortComname;
	//处理企业名称过长的显示
	public String getShortComname() {
		if(comname==null) {
			return  "";
		}
		if(comname.length()<6) {
			return comname;
		}
		else {
			return comname.substring(0,6)+"...";
		}
	}
	//处理订单名称过长的显示
	public String getShortOrderName() {
		if(orderNamech==null) {
			return  "";
		}
		if(orderNamech.length()<12) {
			return orderNamech;
		}
		else {
			return orderNamech.substring(0,12)+"...";
		}
	}	
	
	public String getAddflag() {
		return addflag;
	}
	public void setAddflag(String addflag) {
		this.addflag = addflag;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getOrderNamech() {
		return orderNamech;
	}
	public void setOrderNamech(String orderNamech) {
		this.orderNamech = orderNamech;
	}
	public String getOrderNameen() {
		return orderNameen;
	}
	public void setOrderNameen(String orderNameen) {
		this.orderNameen = orderNameen;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getBusinessid() {
		return businessid;
	}
	public void setBusinessid(String businessid) {
		this.businessid = businessid;
	}
	public String getPublishtime() {
		return publishtime;
	}
	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
	public String getImg1() {
		return img1;
	}
	public void setImg1(String img1) {
		this.img1 = img1;
	}
	public String getImg2() {
		return img2;
	}
	public void setImg2(String img2) {
		this.img2 = img2;
	}
	public String getImg3() {
		return img3;
	}
	public void setImg3(String img3) {
		this.img3 = img3;
	}
	public String getClickcount() {
		return clickcount;
	}
	public void setClickcount(String clickcount) {
		this.clickcount = clickcount;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getCategoryid() {
		return categoryid;
	}
	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getSupplytime() {
		return supplytime;
	}
	public void setSupplytime(String supplytime) {
		this.supplytime = supplytime;
	}
	public String getComid() {
		return comid;
	}
	public void setComid(String comid) {
		this.comid = comid;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getRecomflag() {
		return recomflag;
	}
	public void setRecomflag(String recomflag) {
		this.recomflag = recomflag;
	}
	public String getPublishflag() {
		return publishflag;
	}
	public void setPublishflag(String publishflag) {
		this.publishflag = publishflag;
	}
	public String getTradeflag() {
		return tradeflag;
	}
	public void setTradeflag(String tradeflag) {
		this.tradeflag = tradeflag;
	}
	public String getSupplyflag() {
		return supplyflag;
	}
	public void setSupplyflag(String supplyflag) {
		this.supplyflag = supplyflag;
	}
	public String getDelflag() {
		return delflag;
	}
	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getComname() {
		return comname;
	}
	public void setComname(String comname) {
		this.comname = comname;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
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
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getbusinessName() {
		businessName = "";
		if(businessid == null){
			businessid="1";
		}
		switch(businessid){
			case "1":businessName="轻工业类";break;
			case "2":businessName="重工业类";break;
			case "3":businessName="农业类";break;
			case "4":businessName="纺织类";break;
			case "5":businessName="日用百货类";break;
			case "6":businessName="食品类";break;
			case "7":businessName="运动休闲类";break;
			case "8":businessName="3C产品类";break;
			default: businessName="其他类别";
			
		}
		
		return businessName;
	}
	public void setbusinessName(String businessName) {
		this.businessName = businessName;
	}
	
	
    public static void main(String[] args) {
		Order order = new Order();
		System.out.println(order.getbusinessName());
	}
	
}
