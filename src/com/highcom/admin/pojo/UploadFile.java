package com.highcom.admin.pojo;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;


public class UploadFile {
    /***
     * MultipartFile保存文件
     * @return
     */
    public static String saveFile(MultipartFile file, String path) {
        String fileName="";
        String fileNameNew="";
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                fileNameNew = String.valueOf(UUID.randomUUID());
                String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
                fileName = fileNameNew + "." + suffix;//文件名+后缀名
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                String savePath = path + fileName;
                // 转存文件
                file.transferTo(new File(savePath));
                return fileName;
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        return fileName;
    }

    /**
     * 从共享目录下载文件
     * @author wangxiaolong
     */
    @SuppressWarnings("unused")
    public static void smbGet(String remoteUrl,String localDir) {
        InputStream in = null;
        OutputStream out = null;
        try {
            SmbFile remoteFile = new SmbFile(remoteUrl);
            if(remoteFile==null){
                System.out.println("共享文件不存在");
                return;
            }
            String fileName = remoteFile.getName();
            File localFile = new File(localDir+File.separator+fileName);
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));
            out = new BufferedOutputStream(new FileOutputStream(localFile));
            byte[] buffer = new byte[1024];
            while(in.read(buffer)!=-1){
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 向共享目录上传文件
     * remoteUrl url地址  smb://xxx:xxx@192.168.1.99/testUpload/  //xxx:xxx是共享机器的用户名密码
     * localFilePath 文件名
     * @author wangxiaolong
     */
    public static String smbPut(String remoteUrl, MultipartFile file) {
        String fileName="";
        if(file.getOriginalFilename()=="") {
            return fileName;
        }
        String fileNameNew="";
        InputStream in = null;
        OutputStream out = null;
        try {
            fileNameNew = String.valueOf(UUID.randomUUID());
            String suffix=file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
            fileName = fileNameNew + "." + suffix;//文件名+后缀名
            SmbFile remoteFile = new SmbFile(remoteUrl+fileName);
            in = new BufferedInputStream(file.getInputStream());
            out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
            byte[] buffer = new byte[1024];
            while(in.read(buffer)!=-1){
                out.write(buffer);
                buffer = new byte[1024];
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return fileName;
    }
    /**
     * MultipartFile转换为file
     * @author wangxiaolong
     */
    public static void inputStreamToFile(InputStream ins,File file) {
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = ins.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            throw new RuntimeException("调用inputStreamToFile异常" +e.getMessage());
        }finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (ins != null) {
                    ins.close();
                }
            } catch (Exception e) {
                throw new RuntimeException("调用inputStreamToFile异常" +e.getMessage());
            }
        }
    }

    /**
     * 访问共享目录
     * @author wangxiaolong
     */
    public static void main(String[] args) throws Exception {
        //smb://xxx:xxx@192.168.2.188/testIndex/
        //xxx:xxx是共享机器的用户名密码
        String url="smb://u_upload:123456@192.168.1.99/testUpload/";
        /*
        SmbFile file = new SmbFile(url);
        if(file.exists()){
            SmbFile[] files = file.listFiles();
            for(SmbFile f : files){
                System.out.println(f.getName());
            }
        }*/

        //smbGet(url, "F:/sql.txt");
        //smbPut(url, "F:/sql.txt");

    }
    
    public static String upload(MultipartFile imageFile,String parentPath,String imageName) throws Exception{
    	String imageUrl=null;
    	File f=new File(parentPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		String suffix=imageFile.getOriginalFilename().substring(imageFile.getOriginalFilename().lastIndexOf(".")+1);
		imageUrl=parentPath+imageName+"."+suffix;
		File targetFile=new File(imageUrl);
		imageFile.transferTo(targetFile);
		return imageName+"."+suffix;
    }
}
