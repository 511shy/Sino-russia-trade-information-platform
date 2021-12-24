package com.highcom.admin.service.Impl;

import com.highcom.admin.dao.AboutUsMapper;
import com.highcom.admin.pojo.AboutUs;
import com.highcom.admin.pojo.UploadFile;
import com.highcom.admin.service.AboutUsService;
import com.highcom.admin.service.AssignConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("AboutUsService")
public class AboutUsServiceImpl implements AboutUsService {
    @Autowired
    private AboutUsMapper aboutUsMapper;
    @Autowired
    private AssignConfig assignConfig;

    @Override
    public String saveAboutUs(MultipartFile fileImg, AboutUs aboutUs) {
        String pic = "";
        if(fileImg!=null && !fileImg.getOriginalFilename().equals("")){
            try{
                if(assignConfig.uploadtype.equals("1")){
                    pic = UploadFile.smbPut(assignConfig.uploadUrl, fileImg);//上传图片
                }else{
                    pic = UploadFile.saveFile(fileImg, assignConfig.uploadpath);
                }

                pic = assignConfig.virtualUrl+pic; //路径+上传图片
                aboutUs.setPic(pic);//上传图片
            }catch (Exception e){
                e.printStackTrace();
                return "图片上传失败!";
            }
        }

        aboutUsMapper.saveAboutUs(aboutUs);
        return "添加成功!";
    }

    @Override
    public String updateAboutUs(MultipartFile fileImg, AboutUs aboutUs) {
        String pic = "";
        if(fileImg!=null && !fileImg.getOriginalFilename().equals("")){
            try{
                if(assignConfig.uploadtype.equals("1")){
                    pic = UploadFile.smbPut(assignConfig.uploadUrl, fileImg);//上传图片
                }else{
                    pic = UploadFile.saveFile(fileImg, assignConfig.uploadpath);
                }

                pic = assignConfig.virtualUrl+pic; //路径+上传图片
                aboutUs.setPic(pic);//上传图片
            }catch (Exception e){
                e.printStackTrace();
                return "图片上传失败!";
            }
        }
        aboutUsMapper.updateAboutUs(aboutUs);
        return "更新成功";
    }

    @Override
    public String  updateAbout(AboutUs aboutUs) {
        aboutUsMapper.updateAboutUs(aboutUs);
        return "更新成功";
    }

    @Override
    public AboutUs dispAboutus() {
        AboutUs aboutUs = aboutUsMapper.dispAboutus();
      //  System.out.println("----------------->"+aboutUs);
        return  aboutUs;
    }

    @Override
    public AboutUs dispAboutusById(int id) {
        return aboutUsMapper.dispAboutusById(id);
    }
}
