package com.highcom.admin.controller;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.MflowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "前台国际物流")
@RestController
@RequestMapping("/home")
public class HomeMflowController extends BaseController {
    @Autowired
    private MflowService mflowService;

    @ApiOperation(value = "前台: 根据模块id显示国际物流信息")
    @RequestMapping(value = "mflowById", method = RequestMethod.GET)
    public Result<Mflow> mflowById(int moduleid){
        Mflow mflow = mflowService.mflowbyid(moduleid);

        Result<Mflow> result = new Result<>(200, "提交成功", mflow);
        return result;
    }

    @ApiOperation(value = "前台: 显示所有国际物流模块")
    @RequestMapping(value = "moduleShow", method = RequestMethod.GET)
    public Result<List<Mtype>> moduleShow(){
        List<Mtype> mtypes = mflowService.moduleShow();

        Result<List<Mtype>> result = new Result<>(200, "提交成功", mtypes);
        return result;
    }
}
