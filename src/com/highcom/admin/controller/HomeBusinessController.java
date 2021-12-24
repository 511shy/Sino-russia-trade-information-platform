package com.highcom.admin.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.highcom.admin.pojo.*;
import com.highcom.admin.service.HomeBusinessService;
import io.swagger.annotations.*;

@Api(tags = "前台行业分类")
@RestController
@RequestMapping("/home")
public class HomeBusinessController {

	@Autowired
    private HomeBusinessService homeBusinessService;

    /**
     * 查询行业信息
     */
    @ApiOperation(value="查询行业信息")
    @RequestMapping(value="queryBusiness", method= RequestMethod.GET)
    public Result<List<Business>> queryBusiness(){
    	return new Result<List<Business>>(200,"success",homeBusinessService.queryBusiness());
    }
	
}
