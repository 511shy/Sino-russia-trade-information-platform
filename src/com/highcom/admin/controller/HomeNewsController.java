package com.highcom.admin.controller;
import com.github.pagehelper.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.NewsService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Api(tags = "前台新闻页")
@RestController
@RequestMapping("/home")
public class HomeNewsController extends BaseController {
    @Autowired
    private NewsService newsService;

    /**
     *
     * 按类别查询新闻列表
     * @param pn  当前页码
     * @param id  新闻类别id
     * @param model
     * @return
     */
    @ApiOperation(value="新闻列表信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="pn",value="页码",required=true,paramType="query"),
            @ApiImplicitParam(name="category",value="内容类型 1:国内动态 ,2:国际动态,3:国际贸易",dataType="Integer",paramType="query")
    })
    @ResponseBody
    @RequestMapping(value="news", method= RequestMethod.GET)
    public Result<PageInfo<News>> news(@ApiParam(name = "pn",defaultValue = "1") int pn, int category){
        pageNum = pn;

        //分页配置：
        PageHelper.startPage(pageNum, pageSize);
        List<News> shownews = newsService.findNewsByType(category);
        PageInfo<News> pageInfo = new PageInfo<>(shownews, pageCount);

        Result<PageInfo<News>> result = new Result<>(200,"操作成功",pageInfo);

        return result;
    }

    /**
     *
     * 新闻内容页
     * @return
     */
    @ApiOperation(value="通过ID查询新闻")
    @ResponseBody
    @RequestMapping(value="/showNewsById", method= RequestMethod.GET)
    public Result<News> showNewsById(int id){
        News news = newsService.findNewsById(id);

        Result<News> result = new Result(200,"操作成功",news);
        return  result;
    }
}
