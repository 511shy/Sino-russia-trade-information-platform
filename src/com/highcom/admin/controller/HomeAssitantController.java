package com.highcom.admin.controller;
import com.highcom.admin.pojo.Assistant;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.AssistantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api(tags = "前台外贸助手")
@RestController
@RequestMapping("/home")
public class HomeAssitantController extends BaseController {
    @Autowired
    private AssistantService assistantService;

    @ApiOperation(value="显示外贸助手列表")
    @ResponseBody
    @RequestMapping(value="/showAssitants", method= RequestMethod.POST)
    public Result<List<Assistant>> showAssitants(){
        List<Assistant> assistants = assistantService.showAllAssitant();

        Result<List<Assistant>> result = new Result<>(200, "发送成功", assistants);
        return result;
    }



}
