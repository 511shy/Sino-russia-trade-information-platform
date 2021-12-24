package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

public class Mtype {
    @ApiModelProperty(name = "id",value = "主键")
    private int id;

    @ApiModelProperty(name = "module",value = "模块名称", required = true)
    private String module;

    @ApiModelProperty(name = "mname",value = "前台物流模块名称", required = true)
    private String mname;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }
}
