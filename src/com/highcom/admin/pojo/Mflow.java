package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

public class Mflow {
    @ApiModelProperty(name = "id",value = "主键")
    private int id;

    @ApiModelProperty(name = "title",value = "标题",required = true)
    private String title;

    @ApiModelProperty(name = "detail",value = "详细内容",required = true)
    private String detail;

    @ApiModelProperty(name = "moduleid",value = "所属模块id: 1:模块一(海运), 2:模块二(陆运), 3:模块三(空运), 4:模块四(物流仓)",required = true, example = "1")
    private int moduleid;


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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getModuleid() {
        return moduleid;
    }

    public void setModuleid(int moduleid) {
        this.moduleid = moduleid;
    }
}
