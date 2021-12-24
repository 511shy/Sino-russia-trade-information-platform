package com.highcom.admin.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BaseController {
    //起始页码
    static int pageNum = 1;
    //每页条数
    static int pageSize = 10;
    //分页数量
    static int pageCount = 3;

    //富文本上传图片访问路径 下列公用变量已移至 jdbc.properties中
	//public static final String HOSTURL = "http://localhost:8080";
	//public static final String REALPATH= "e:/upload/";
	 
    public String currTime(){
        //当前时间
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String current = sdf.format(date);
        return current;
    }
}
