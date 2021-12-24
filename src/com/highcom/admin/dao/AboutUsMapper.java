package com.highcom.admin.dao;

import com.highcom.admin.pojo.AboutUs;

import java.util.List;

public interface AboutUsMapper {
    //保存 关于我们
    void saveAboutUs(AboutUs aboutUs);

    //更新 关于我们
    void updateAboutUs(AboutUs aboutUs);

    //显示 关于我们
    AboutUs dispAboutus();

    //通过id查询
    AboutUs dispAboutusById(int id);

}
