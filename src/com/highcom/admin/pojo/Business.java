package com.highcom.admin.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "行业信息")
public class Business implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "id",value = "主键",required = true,example = "1")
	private Integer id;
	@ApiModelProperty(name = "businessName",value = "行业信息",required = true,example = "轻工业类")
	private String businessName;
	@ApiModelProperty(name = "businessDes",value = "行业描述",required = true,example = "这是一个轻工业类")
	private String businessDes;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getBusinessDes() {
		return businessDes;
	}
	public void setBusinessDes(String businessDes) {
		this.businessDes = businessDes;
	}
	@Override
	public String toString() {
		return "Business [id=" + id + ", businessName=" + businessName + ", businessDes=" + businessDes + "]";
	}
	
	
}
