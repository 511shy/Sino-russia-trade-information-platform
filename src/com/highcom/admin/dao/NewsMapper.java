package com.highcom.admin.dao;


import com.highcom.admin.pojo.News;
import com.highcom.admin.pojo.NewsType;

import java.util.List;

public interface NewsMapper {
    //后台
    //按类型显示所有新闻
    List<News> allNews();

    //通过ID查询新闻
    News findNewsById(int id);

    //检索区搜索新闻(use)
    List<News> findNewsByComplex(News news);

    //查询新闻类别
    List<NewsType> findNewsType();

    //添加新闻
    void insertNews(News news);

    //更新新闻
    void updateNews(News news);

    //删除新闻
    void delNews(int id);

    //前端
    //按列表显示新闻列表
    List<News> findNewsByType(int category);
}
