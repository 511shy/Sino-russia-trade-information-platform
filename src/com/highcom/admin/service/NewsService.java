package com.highcom.admin.service;


import com.highcom.admin.pojo.News;
import com.highcom.admin.pojo.NewsType;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NewsService {
    //后台
    //显示新闻列表
    List<News> allNews(News news);

    //通过ID查询新闻
    News findNewsById(int id);

    //查询新闻类别
    List<NewsType> findNewsType();

    //添加新闻
    String insertNews(MultipartFile fileImg, News news);

    //更新新闻
    String updateNews(MultipartFile fileImg, News news);
    //更新新闻(不更新图片)
    String  updateNewsNoPic(News news);

    //删除新闻
    void delNews(int id);

    //前端
    //按列表显示新闻列表
    List<News> findNewsByType(int category);

}
