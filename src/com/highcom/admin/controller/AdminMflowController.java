<<<<<<< HEAD
package com.highcom.admin.controller;
import com.highcom.admin.pojo.Mflow;
import com.highcom.admin.pojo.Mtype;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.MflowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Api(tags = "后台国际物流管理")
@RestController
@RequestMapping("/mflow")
public class AdminMflowController extends BaseController {
    @Autowired
    private MflowService mflowService;

    @ApiOperation(value = "后台: 根据模块id显示国际物流信息")
    @RequestMapping(value = "mflowByModuleId", method = RequestMethod.GET)
    public Result<Mflow> mflowByModuleId(int moduleid){
        Mflow mflow = mflowService.mflowbyid(moduleid);

        Result<Mflow> result = new Result<>(200, "提交成功", mflow);
        return result;
    }

    @ApiOperation(value = "后台: 显示所有国际物流模块")
    @RequestMapping(value = "moduleShow", method = RequestMethod.GET)
    public Result<List<Mtype>> moduleShow(){
        List<Mtype> mtypes = mflowService.moduleShow();

        Result<List<Mtype>> result = new Result<>(200, "提交成功", mtypes);
        return result;
    }

    
    @ApiOperation(value = "后台: 添加国际物流")
    @RequestMapping(value = "savemflow", method = RequestMethod.POST)
    public Result savemflow(Mflow mflow){
        mflowService.mflowInsert(mflow);

        Result result = new Result(200, "添加成功");
        return result;
    }
    
    @ApiOperation(value = "后台: 更新际物流")
    @RequestMapping(value = "updateflow", method = RequestMethod.POST)
    public Result <String>updateflow(Mflow mflow){
    	mflowService.mflowUpdate(mflow);
    	return new Result<>(200, "更新成功","");
    }
    

    @ApiOperation(value = "后台: 预览_国际物流")
    @RequestMapping(value = "mflowPreview", method = RequestMethod.POST)
    public Result<Mflow> mflowPreview(Mflow mflow){
        //添加
        mflowService.mflowInsert(mflow);

        //预览
        int id = mflow.getId();
        mflow = mflowService.mflowForOne(id);

        Result<Mflow> result = new Result<>(200, "提交成功", mflow);
        return result;
    }
}
=======
package com.highcom.admin.controller;

import com.highcom.admin.pojo.Mflow;
import com.highcom.admin.pojo.Mtype;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.MflowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "后台国际物流管理")
@RestController
@RequestMapping("/mflow")
public class AdminMflowController extends BaseController {
    @Autowired
    private MflowService mflowService;

    @ApiOperation(value = "后台: 根据模块id显示国际物流信息")
    @RequestMapping(value = "mflowByModuleId", method = RequestMethod.GET)
    public Result<Mflow> mflowByModuleId(int moduleid){
        Mflow mflow = mflowService.mflowbyid(moduleid);

        Result<Mflow> result = new Result<>(200, "提交成功", mflow);
        return result;
    }

    @ApiOperation(value = "后台: 显示所有国际物流模块")
    @RequestMapping(value = "moduleShow", method = RequestMethod.GET)
    public Result<List<Mtype>> moduleShow(){
        List<Mtype> mtypes = mflowService.moduleShow();

        Result<List<Mtype>> result = new Result<>(200, "提交成功", mtypes);
        return result;
    }

    
    @ApiOperation(value = "后台: 添加国际物流")
    @RequestMapping(value = "savemflow", method = RequestMethod.POST)
    public Result savemflow(Mflow mflow){
        mflowService.mflowInsert(mflow);

        Result result = new Result(200, "添加成功");
        return result;
    }
    
    @ApiOperation(value = "后台: 更新际物流")
    @RequestMapping(value = "updateflow", method = RequestMethod.POST)
    public Result <String>updateflow(Mflow mflow){
    	mflowService.mflowUpdate(mflow);
    	return new Result<>(200, "更新成功","");
    }
    

    @ApiOperation(value = "后台: 预览_国际物流")
    @RequestMapping(value = "mflowPreview", method = RequestMethod.POST)
    public Result<Mflow> mflowPreview(Mflow mflow){
        //添加
        mflowService.mflowInsert(mflow);

        //预览
        int id = mflow.getId();
        mflow = mflowService.mflowForOne(id);

        Result<Mflow> result = new Result<>(200, "提交成功", mflow);
        return result;
    }
}
>>>>>>> d6bdc272d47a8d71a9052d3398fc4e01f3c7a0ab
