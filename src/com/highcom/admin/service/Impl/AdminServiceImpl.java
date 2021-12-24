package com.highcom.admin.service.Impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.highcom.admin.dao.AdminMapper;
import com.highcom.admin.pojo.HomeInfo;
import com.highcom.admin.pojo.Order;
import com.highcom.admin.pojo.UploadFile;
import com.highcom.admin.service.AdminService;
import com.highcom.admin.service.AssignConfig;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
    private AssignConfig assignConfig;
	
	@Autowired
    private AdminMapper adminMapper;

	
	@Override
	public boolean updateHomeInfo(MultipartFile fileImg, HomeInfo homeInfo) {
		String imageUrl=null;
		if(fileImg!=null && !fileImg.getOriginalFilename().equals("")) {
			try {
				String url="";
				if(assignConfig.uploadtype.equals("1")){
					url=assignConfig.uploadUrl;
					imageUrl=UploadFile.upload(fileImg, url, new Date().getTime()+"h");
				}else{
					url=assignConfig.uploadpath;
		        	imageUrl=UploadFile.upload(fileImg, url, new Date().getTime()+"h");
				}
				//删除原图片
	        	File file=new File(url+homeInfo.getImageUrl().split("/")[1]);
				if(file.exists()) {
					file.delete();
				}
				imageUrl=assignConfig.virtualUrl+imageUrl;
				homeInfo.setImageUrl(imageUrl);
			}catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
		}
		if(adminMapper.updateHomeInfo(homeInfo)>0) {
			return true;
		}
		return false;
	}
	
	public List<HomeInfo> queryHomeInfo() {
		return adminMapper.queryHomeInfo();
	}

	@Override
	public List<HomeInfo> queryHomeInfoByContentType(Integer contentType) {
		return adminMapper.queryHomeInfoByContentType(contentType);
	}

	@Override
	public List<Order> queryOrderByCondition(Integer count,Integer flag) {
		if(flag==1) {
			return adminMapper.queryOrderByCondition1(count);
		}else if(flag==2) {
			return adminMapper.queryOrderByCondition2(count);
		}
		return null;
	}

	@Override
	public List<HomeInfo> queryPagefooter() {
		return adminMapper.queryPagefooter();
	}


}
