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

@Api(tags = "前台关于我们")
@RestController
@RequestMapping("/home")
public class HomeAboutUsController extends BaseController {
    @Autowired
    private AboutUsService aboutUsService;

    @ApiOperation(value="显示关于我们")
    @ResponseBody
    @RequestMapping(value="/dispaboutus", method= RequestMethod.GET)
    public Result<AboutUs> dispaboutus(){
        AboutUs aboutUs = aboutUsService.dispAboutus();

        Result<AboutUs> result = new Result<>(200, "操作成功", aboutUs);
        return result;
    }
}
