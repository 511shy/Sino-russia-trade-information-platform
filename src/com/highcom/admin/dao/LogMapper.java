package com.highcom.admin.dao;
import java.util.List;
import com.highcom.admin.pojo.LogInfo;

/**
 * 系统日志操作
 */
public interface LogMapper {
	void addLog(LogInfo logInfo);

	List<LogInfo> findLogList(LogInfo log);
}
