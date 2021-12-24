package com.highcom.admin.pojo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

@ApiModel(description = "首页信息")
public class HomeInfo  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(name = "id",value = "主键",required = true,example = "1")
	private Integer id;
	@ApiModelProperty(name = "content",value = "首页信息内容，如qq",required = true,example = "1206819532")
	private String content;  //内容
	@ApiModelProperty(value = "imageUrl",hidden = true)
	private String imageUrl; //图片地址
	@ApiModelProperty(name = "contentType",value = "1：轮播 ,2：qq,3：微信，4：What'sup",example = "2")
	private Integer contentType; //内容类型
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public Integer getContentType() {
		return contentType;
	}
	public void setContentType(Integer contentType) {
		this.contentType = contentType;
	}
	@Override
	public String toString() {
		return "HomeInfo [id=" + id + ", content=" + content + ", imageUrl=" + imageUrl + ", contentType=" + contentType
				+ "]";
	}
	
}
