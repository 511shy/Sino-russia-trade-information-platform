package com.highcom.admin.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 配置文件变量
 * fyg: 为了便于使用,这里全部设置成了静态变量,由于是静态变量,让spring容器注入的时候要提供set方法(不能是static的),并且 @value 注解要放在set方法上
 */
@Component
public final class AssignConfig {
    public static String uploadUrl;//配置远程上传文件地址
    public static String uploadtype;//走本地上传还是共享文件夹上传 0本地上传 1远程磁盘共享文件夹上传
    public static String uploadpath;//配置本地上传附件路径
    public static String virtualUrl;//虚拟路径
    public static String hosturl;//主机地址

    @Value("${uploadUrl}")
	public void setUploadUrl(String uploadUrl) {
    	AssignConfig.uploadUrl = uploadUrl;
	}

    @Value("${uploadtype}")
	public void setUploadtype(String uploadtype) {
    	AssignConfig.uploadtype = uploadtype;
	}

    @Value("${uploadpath}")
	public void setUploadpath(String uploadpath) {
    	AssignConfig.uploadpath = uploadpath;
	}

    @Value("${virtualUrl}")
	public void setVirtualUrl(String virtualUrl) {
    	AssignConfig.virtualUrl = virtualUrl;
	}

    @Value("${hosturl}")
	public void setHosturl(String hosturl) {
		AssignConfig.hosturl = hosturl;
	}
    
}
