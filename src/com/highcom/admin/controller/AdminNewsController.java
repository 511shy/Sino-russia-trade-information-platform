package com.highcom.admin.controller;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.News;
import com.highcom.admin.pojo.NewsType;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AssignConfig;
import com.highcom.admin.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Api(tags = "后台新闻管理")
@RestController
@RequestMapping("/news")
public class AdminNewsController extends BaseController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private AssignConfig assignConfig;

    /**
     * 显示新闻列表
     * @param pn
     * @param model
     * @return
     */
    @ApiOperation(value="后台_新闻列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pn",value="页码",required=true,paramType="query"),
            @ApiImplicitParam(name="title",value="复合条件查询: title 按新闻标题查询",required=false,dataType="String",paramType="query"),
            @ApiImplicitParam(name="publish",value="复合条件查询: publish 按发布时间查询",required=false,dataType="String",paramType="query")
    })
    @ResponseBody
    @RequestMapping(value="/showallnews", method= RequestMethod.GET)
    public Result<PageInfo<News>> shownews(@RequestParam(value="pn", defaultValue="1") int pn, String title, String publish){
        pageNum = pn;
        //分页配置：
        PageHelper.startPage(pageNum, 15);

        //传入复合查询参数
        News news = new News();
        news.setTitle(title);
        news.setPublish(publish);

        List<News> shownews = newsService.allNews(news);
        PageInfo pageInfo = new PageInfo(shownews, pageCount);

        Result<PageInfo<News>> result = new Result<PageInfo<News>>(200,"操作成功",pageInfo);

        return result;
    }


    /**
     * 保存新闻
     * @param news
     * @param model
     * @return
     */
    @ApiOperation(value="后台: 添加新闻")
	/*@ApiImplicitParams({
		 @ApiImplicitParam(name="id",value="主键",required=true,dataType="Integer",paramType="query"),
	     @ApiImplicitParam(name="title",value="新闻标题",required=true,paramType="query"),
	     @ApiImplicitParam(name="category",value="新闻类别",required=true,paramType="query"),
	     @ApiImplicitParam(name="msgpic",value="资讯图片",required=true,paramType="query"),
	     @ApiImplicitParam(name="intro",value="新闻摘要",required=true,paramType="query"),
	     @ApiImplicitParam(name="content",value="新闻内容",required=true,paramType="query"),
	     @ApiImplicitParam(name="publish",value="发布时间",required=true,paramType="query"),
	     @ApiImplicitParam(name="updatetime",value="更新时间",required=true,paramType="query"),
	     @ApiImplicitParam(name="disp",value="是否显示 1：显示 ,0: 不显示",required=true,dataType="Integer",paramType="query")
	     @ApiImplicitParam(name="writer",value="发布者",required=true,paramType="query")
	})*/
    @RequestMapping(value = "/savenews",  method = RequestMethod.POST)
    public Result savenews(MultipartFile fileImg, News news){
        //添加时间
        news.setPublish(currTime());
        String msg = newsService.insertNews(fileImg, news);

        Result result = new Result(200,msg);

        return result;
    }

    /**
     * 保存修改后的新闻
     * @param news
     * @return
     */
    @ApiOperation(value="后台: 修改新闻")
    @RequestMapping(value = "/updatenews",  method = RequestMethod.POST)
    public Result updatenews(MultipartFile fileImg, News news){
        String msg = null;
        news.setPublish(currTime());

        //如果没有图片上传
        if(fileImg == null || fileImg.isEmpty()){
            news.setMsgpic(news.getIsPic());
            msg = newsService.updateNewsNoPic(news);
        }else {
            msg = newsService.updateNews(fileImg, news);
        }

        Result result = new Result(200,msg);
        return result;
    }

    @ApiOperation(value="后台: 删除新闻")
    @RequestMapping(value = "/delnews",  method = RequestMethod.GET)
    public Result delnews(int id){
        newsService.delNews(id);

        Result result = new Result(200,"提交成功", "");

        return result;
    }

    @ApiOperation(value="后台: 显示资讯所属栏目")
    @RequestMapping(value = "/dispNewsType",  method = RequestMethod.GET)
    public Result<List<NewsType>> dispNewsType(){
        List<NewsType> newsTypes = newsService.findNewsType();

        Result<List<NewsType>> result = new Result<>(200,"提交成功", newsTypes);

        return result;
    }

    @ApiOperation(value="后台: 通过ID查询新闻")
    @RequestMapping(value = "/findNewsById",  method = RequestMethod.GET)
    public Result<News> findNewsById(int id){
        News news = newsService.findNewsById(id);

        Result<News> result = new Result<>(200,"提交成功", news);

        return result;
    }

    @ApiOperation(value="后台: 预览_新闻")
    @RequestMapping(value = "/newPreview",  method = RequestMethod.POST)
    public Result<News> newPreview(MultipartFile fileImg, News news){
        //添加新闻
        news.setPublish(currTime());
        String msg = newsService.insertNews(fileImg, news);

        //预览
        int id = news.getId();
        news = newsService.findNewsById(id);

        Result<News> result = new Result<>(200, msg, news);

        return result;
    }



}
