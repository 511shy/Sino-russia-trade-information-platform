package com.highcom.admin.service;

import com.highcom.admin.pojo.Mflow;
import com.highcom.admin.pojo.Mtype;

import java.util.List;

public interface MflowService {
    //添加国际物流
    void mflowInsert(Mflow mflow);

    //显示所有模块名称
    List<Mtype> moduleShow();

    //根据id查询物流信息
    Mflow mflowbyid(int moduleid);

    //通过id查询物流信息
    Mflow mflowForOne(int id);

    //更新国际物流信息
	void mflowUpdate(Mflow mflow);
}
