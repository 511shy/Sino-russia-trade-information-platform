package com.highcom.admin.service;

import com.highcom.admin.pojo.AboutUs;
import org.springframework.web.multipart.MultipartFile;

public interface AboutUsService {
    //保存 关于我们
    String saveAboutUs(MultipartFile fileImg, AboutUs aboutUs);

    //更新 关于我们
    String updateAboutUs(MultipartFile fileImg, AboutUs aboutUs);

    //更新 关于我们(不更新图片)
    String updateAbout(AboutUs aboutUs);

    //显示 关于我们
    AboutUs dispAboutus();

    //通过id查询
    AboutUs dispAboutusById(int id);

}
