package com.highcom.admin.controller;
import com.highcom.admin.pojo.AboutUs;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AboutUsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(tags = "后台关于我们管理")
@RestController
@RequestMapping("/aboutus")
public class AdminAboutUsController extends BaseController {
    @Autowired
    private AboutUsService aboutUsService;

    @ApiOperation(value="保存关于我们")
    @ResponseBody
    @RequestMapping(value="/saveaboutus", method= RequestMethod.POST)
    public Result saveaboutus(MultipartFile fileImg, AboutUs aboutUs){
        //System.out.println(aboutUs);
        String msg = aboutUsService.saveAboutUs(fileImg, aboutUs);

        Result result = new Result(200, msg);
        return result;
    }

    @ApiOperation(value="更新关于我们")
    @ResponseBody
    @RequestMapping(value="/updateaboutus", method= RequestMethod.POST)
    public Result updateaboutus(MultipartFile fileImg, AboutUs aboutUs){
        String msg = null;
        //如果没有图片上传
        if(fileImg == null || fileImg.isEmpty()){
            aboutUs.setPic(aboutUs.getHavepic());
            msg = aboutUsService.updateAbout(aboutUs);
        }else {
            msg = aboutUsService.updateAboutUs(fileImg, aboutUs);
        }

        Result result = new Result(200, msg);
        return result;
    }

    @ApiOperation(value="显示关于我们")
    @ResponseBody
    @RequestMapping(value="/dispaboutus", method= RequestMethod.GET)
    public Result<AboutUs> dispaboutus(){
        AboutUs aboutUs = aboutUsService.dispAboutus();

        Result<AboutUs> result = new Result<>(200, "操作成功", aboutUs);
        return result;
    }


    @ApiOperation(value = "后台: 预览_关于我们")
    @RequestMapping(value = "aboutPreview", method = RequestMethod.POST)
    public Result<AboutUs> aboutPreview(MultipartFile fileImg, AboutUs aboutUs){
        //添加
        String msg = aboutUsService.saveAboutUs(fileImg, aboutUs);

        //预览
        int id = aboutUs.getId();
        aboutUs = aboutUsService.dispAboutusById(id);

        Result<AboutUs> result = new Result<>(200, msg, aboutUs);
        return result;
    }
}
