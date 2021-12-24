package com.highcom.admin.pojo;

import io.swagger.annotations.ApiModelProperty;

public class Assistant {
    @ApiModelProperty(name = "id",value = "主键")
    private int id;

    @ApiModelProperty(name = "aname",value = "名称", required = true)
    private String aname;

    @ApiModelProperty(name = "address",value = "访问地址", required = true)
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Assistant{" +
                "id=" + id +
                ", aname='" + aname + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
