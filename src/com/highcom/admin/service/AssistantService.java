package com.highcom.admin.service;

import com.highcom.admin.pojo.Assistant;

import java.util.List;

public interface AssistantService {
    //显示外贸助手列表
    List<Assistant> showAllAssitant();

    //修改某条 外贸助手
    void updateAssitant(Assistant assistant);

    //添加外贸助手
    void AddAssitant(Assistant assistant);
}
