package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class News {
    @ApiModelProperty(name = "id",value = "主键")
    private int id;

    @ApiModelProperty(name = "category",value = "1：国内市场动态 ,2：国际市场动态,3：国际贸易最新政策",required = true,example = "2")
    private int category;

    @ApiModelProperty(name = "title",value = "资讯标题",required = true)
    private String title;

    @ApiModelProperty(name = "pic",value = "上传图片", hidden = true)
    private String msgpic;

    @ApiModelProperty(name = "intro",value = "新闻摘要",required = true)
    private String intro;

    @ApiModelProperty(name = "content",value = "新闻内容",required = true)
    private String content;

    @ApiModelProperty(name = "publish", value = "发布时间", hidden = true)
    private String publish;

    @ApiModelProperty(name = "updatetime", value = "更新时间", hidden = true)
    private Date updatetime;

    @ApiModelProperty(name = "disp", value = "1: 推荐, 0: 不推荐", required = true,example = "1")
    private int disp;

    @ApiModelProperty(name = "writer", value = "发布者", required = true)
    private String writer;

    @ApiModelProperty(name = "tid", value = "新闻类型id", hidden = true)
    private int tid;

    @ApiModelProperty(name = "typename", value = "新闻类型名称", hidden = true)
    private String typename;

    private String isPic;  //数据库中已存在的上传图片名

    public String getIsPic() {
        return isPic;
    }

    public void setIsPic(String isPic) {
        this.isPic = isPic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMsgpic() {
        return msgpic;
    }

    public void setMsgpic(String msgpic) {
        this.msgpic = msgpic;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public int getDisp() {
        return disp;
    }

    public void setDisp(int disp) {
        this.disp = disp;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}
