package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "返回对象")
public class Result<T> {
	@ApiModelProperty(name = "status",value = "状态码")
	private Integer status;
	@ApiModelProperty(name = "message",value = "消息")
	private String message;
	@ApiModelProperty(name = "obj",value = "内容")
	private T obj;
	public Result() {
		this.status = 200;
		this.message = "success";
	}
	public Result(Integer status, String message, T obj) {
		this.status = status;
		this.message = message;
		this.obj = obj;
	}
//	public Result() {
//		this.status=200;
//		this.message="success";
//	}

	public Result(Integer status, String message) {
		this.status = status;
		this.message = message;
	}
	public Result(Integer status) {
		this.status = status;
	}
	
	public Result(String s, String 发送成功, List<Assistant> assistants) {
		this.status=200;
		this.message="调用成功";
	}

	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
	
}
