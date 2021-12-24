package com.highcom.admin.dao;

import com.highcom.admin.pojo.Mflow;
import com.highcom.admin.pojo.Mtype;

import java.util.List;

public interface MflowMapper {
    //添加国际物流信息
    void mflowInsert(Mflow mflow);

    //更新物流
    void mflowUpdate(Mflow mflow);

    //显示所有模块名称
    List<Mtype> moduleShow();

    //根据模块id查询物流信息
    Mflow mflowById(int id);

    //通过id查询物流信息
    Mflow mflowForOne(int id);
}
