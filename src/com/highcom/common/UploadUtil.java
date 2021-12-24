package com.highcom.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

/*
 * 文件上传工具类。支持单文件，多文件上传
 * 
 */

/*
 * 文件上传至web部署包下upload文件夹。保存文件名为 uuid生成字符串_原始文件名
 * 返回值：返回map  key：index  value：文件名
 */
public class UploadUtil {
	public static Map<Integer, String> imgsUpload(HttpServletRequest request, MultipartFile[] upload) {
		Map<Integer, String> map = new HashMap<>();
		String path = request.getServletContext().getRealPath("/");

		File file = new File(path + "upload\\");

		// 在web部署包下创建upload文件夹
		if (!file.exists()) {
			file.mkdirs();
		}
		if (upload != null && upload.length > 0) {
			for (int i = 0; i < upload.length; i++) {
				if (upload[i].isEmpty()) {
					continue;
				}

				String filename = upload[i].getOriginalFilename();
				String uuid = UUID.randomUUID().toString().toUpperCase();
				filename = uuid + "_" + filename;

				map.put(i,  filename);
				try {
					upload[i].transferTo(new File(file, filename));
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}

		return map;
	}

	/*
	 * 文件上传至硬盘指定路径下。保存的文件名为 uuid生成字符串_原始文件名 返回值：返回map key：index value： 保存的文件名
	 */
	public static Map<Integer, String> imgsUpload(String path, MultipartFile[] upload) {
		Map<Integer, String> map = new HashMap<>();
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		if (upload != null && upload.length > 0) {
			for (int i = 0; i < upload.length; i++) {
				if (upload[i].isEmpty()) {
					continue;
				}

				String filename = upload[i].getOriginalFilename();
				String uuid = UUID.randomUUID().toString().toUpperCase();
				filename = uuid + "_" + filename;

				map.put(i, filename);
				try {
					upload[i].transferTo(new File(file, filename));
				} catch (Exception e) {

					e.printStackTrace();
				}

			}
		}

		return map;
	}

}
