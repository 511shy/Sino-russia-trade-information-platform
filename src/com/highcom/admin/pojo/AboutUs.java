package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

public class AboutUs {
	//这是注释

    @ApiModelProperty(name = "id",value = "主键")
    private int id;

    @ApiModelProperty(name = "title",value = "标题",required = true)
    private String title;

    @ApiModelProperty(name = "pic",value = "上传图片", hidden = true)
    private String pic;

    @ApiModelProperty(name = "intro",value = "首页摘要",required = true)
    private String intro;

    @ApiModelProperty(name = "detail",value = "详细内容",required = true)
    private String detail;

    private String havepic;  //数据库中已存在的上传图片名

    public String getHavepic() {
        return havepic;
    }

    public void setHavepic(String havepic) {
        this.havepic = havepic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
