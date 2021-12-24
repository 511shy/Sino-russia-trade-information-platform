package com.highcom.admin.controller;
import com.highcom.admin.service.AssignConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class AdminUploadImgController extends BaseController {
    @Autowired
    private AssignConfig assignConfig;

    //富文本文件上传
    @ResponseBody
    @RequestMapping(value = "/admin/uploadimg")
    public String uploadFile(MultipartHttpServletRequest request) throws IllegalStateException, IOException {
        String url;
        //imgFile  这个名称没有什么理由,就是个固定写法,在前台也找不到它的什么痕迹
        MultipartFile file = request.getFile("imgFile");

        String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);

        //目标文件=文件名+后缀名;
        String newName= UUID.randomUUID().toString() + "." + suffix;

        if(assignConfig.uploadtype.equals("1")){//物理地址
            url=assignConfig.uploadUrl;
        }else{
            url=assignConfig.uploadpath;
        }

        File descFile = new File(url+ newName);  //物理地址
        file.transferTo(descFile);
        
        String virtualPath=assignConfig.hosturl+request.getContextPath() +"/homeImages/";

        //这个图片地址,是要返给前台的 kindeditor 用的, 它要求以json格式返回
        //String uploadUrl = "http://localhost:8080/homeImages/";// 是虚拟路径
        String path = virtualPath+ newName; 

        return  "{\"error\" : 0,\"url\" : \""+path+"\"}";
    }

}
