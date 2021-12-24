package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

public class NewsType {
    @ApiModelProperty(name = "tid",value = "新闻类型主键")
    private int tid;

    @ApiModelProperty(name = "typeName",value = "新闻类型",required = true)
    private String typeName;

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }


    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
