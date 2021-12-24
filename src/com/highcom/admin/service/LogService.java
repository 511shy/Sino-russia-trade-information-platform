package com.highcom.admin.service;
import java.util.List;
import com.highcom.admin.pojo.LogInfo;

/**
 * 日志信息
 */
public interface LogService {
	/**
	 * 记录日志
	 * @param logInfo 日志信息
	 */
	 void addLog(LogInfo logInfo);

	 /**
	  * 多条件查询日志列表
	  * @param log  查询条件
	  * @return 日志列表
	  */
	List<LogInfo> findLogList(LogInfo log);

}
