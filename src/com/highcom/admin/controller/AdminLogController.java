package com.highcom.admin.controller;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.highcom.admin.pojo.LogInfo;
import com.highcom.admin.pojo.Result;
import com.highcom.admin.service.LogService;

@Controller @RequestMapping("/admin")
public class AdminLogController {
	@Resource
	private LogService logService;
	
	@ResponseBody
	@RequestMapping(value = "/logList", method = RequestMethod.GET)
	public Result<PageInfo<LogInfo>> findLogList(String moduleName, String adminName, String searchbegin, String searchend,
			Integer page,Integer limit)
	 {
		LogInfo log = new LogInfo();
		log.setModule(moduleName);
		log.setAdminName(adminName);
		log.setSearchbegin(searchbegin);

		log.setSearchend(searchend);

		PageHelper.clearPage();
		PageHelper.startPage(page, limit);
		List<LogInfo> list = logService.findLogList(log);
		PageInfo<LogInfo> pageInfo = new PageInfo<>(list);

		return new Result<PageInfo<LogInfo>>(200, "", pageInfo);
	}

}
