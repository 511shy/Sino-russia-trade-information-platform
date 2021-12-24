package com.highcom.admin.service.Impl;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.highcom.admin.dao.LogMapper;
import com.highcom.admin.pojo.LogInfo;
import com.highcom.admin.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Resource 
	private LogMapper logMapper;
	
	public void addLog(LogInfo logInfo) {
		logMapper.addLog(logInfo);
	}
	public List<LogInfo> findLogList(LogInfo log) {
		//日志记录是精确到秒的
		String searchend=log.getSearchend();
		if(!"".equals(searchend)){
			log.setSearchend(searchend+" 23:59:59");
		}
		
		List<LogInfo> logList= logMapper.findLogList(log);
		
		//处理掉日期默认 2021-03-01 08:50:56.0 格式 后面的 .0
		for(LogInfo info:logList) {
			info.setLogTime(info.getLogTime().replace(".0",""));
		}
		return logList; 
	}

}
