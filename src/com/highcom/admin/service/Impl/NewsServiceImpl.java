package com.highcom.admin.service.Impl;

import com.highcom.admin.dao.NewsMapper;
import com.highcom.admin.pojo.AboutUs;
import com.highcom.admin.pojo.News;
import com.highcom.admin.pojo.NewsType;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.NewsService;
import com.highcom.admin.pojo.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service("NewsService")
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private AssignConfig assignConfig;

    @Override
    public List<News> allNews(News news) {
        return newsMapper.findNewsByComplex(news);
    }

    @Override
    public News findNewsById(int id) {
        return newsMapper.findNewsById(id);
    }

    @Override
    public List<NewsType> findNewsType() {
        return newsMapper.findNewsType();
    }

    @Override
    public String insertNews(MultipartFile fileImg, News news) {
        String msgpic = "";
        if(fileImg!=null && !fileImg.getOriginalFilename().equals("")){
            try{
                if(assignConfig.uploadtype.equals("1")){
                    msgpic = UploadFile.smbPut(assignConfig.uploadUrl, fileImg);//上传图片
                }else{
                    msgpic = UploadFile.saveFile(fileImg, assignConfig.uploadpath);
                }

                msgpic = assignConfig.virtualUrl+msgpic; //路径+上传图片
                news.setMsgpic(msgpic);//上传图片
            }catch (Exception e){
                e.printStackTrace();
                return "图片上传失败!";
            }
        }

        newsMapper.insertNews(news);
        return "添加成功!";
    }

    @Override
    public String updateNews(MultipartFile fileImg, News news) {
        String msgpic = null;

        if(fileImg!=null && !fileImg.getOriginalFilename().equals("")){
            try{
                if(assignConfig.uploadtype.equals("1")){
                    msgpic = UploadFile.smbPut(assignConfig.uploadUrl, fileImg);//上传图片
                }else{
                    msgpic = UploadFile.saveFile(fileImg, assignConfig.uploadpath);
                }

                msgpic = assignConfig.virtualUrl+msgpic; //路径+上传图片
                news.setMsgpic(msgpic);//上传图片
            }catch (Exception e){
                e.printStackTrace();
                return "图片上传失败!";
            }
        }

        newsMapper.updateNews(news);
        return "更新成功!";
    }

    @Override
    public String updateNewsNoPic(News news) {
        newsMapper.updateNews(news);
        return "更新成功";
    }

    @Override
    public void delNews(int id) {
        newsMapper.delNews(id);
    }

    @Override
    public List<News> findNewsByType(int category) {
        return newsMapper.findNewsByType(category);
    }
}
