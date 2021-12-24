package com.highcom.admin.controller;
import com.highcom.admin.pojo.Assistant;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AssistantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "后台外贸助手管理")
@RestController
@RequestMapping("/assistant")
public class AdminAssitantController extends BaseController {
    @Autowired
    private AssistantService assistantService;

    @ApiOperation(value="后台: 显示外贸助手列表")
    @RequestMapping(value = "/assistantlist",  method = RequestMethod.POST)
    public Result<List<Assistant>> showAllAssistant(){
        List<Assistant> assistants = assistantService.showAllAssitant();

        Result<List<Assistant>> result = new Result<>(200, "提交成功", assistants);
        return result;
    }

    @ApiOperation(value="后台: 更新外贸助手")
    @ResponseBody
    @RequestMapping(value = "/updateassistant",  method = RequestMethod.POST)
    public Result updateAssitant(Assistant assistant){
        assistantService.updateAssitant(assistant);

        Result result = new Result(200, "更新成功");
        return result;
    }

    @ApiOperation(value = "后台: 添加外贸助手")
    @ResponseBody
    @RequestMapping(value = "addassitant", method = RequestMethod.POST)
    public Result addAssitant(Assistant assistant){
        assistantService.AddAssitant(assistant);

        Result result = new Result(200, "添加成功");
        return result;
    }
}
