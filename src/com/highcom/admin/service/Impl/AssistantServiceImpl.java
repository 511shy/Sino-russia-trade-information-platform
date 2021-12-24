package com.highcom.admin.service.Impl;

import com.highcom.admin.dao.AssistantMapper;
import com.highcom.admin.pojo.Assistant;
import com.highcom.admin.service.AssistantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("AssistantService")
public class AssistantServiceImpl implements AssistantService {
    @Autowired
    private AssistantMapper assistantMapper;

    @Override
    public List<Assistant> showAllAssitant() {
        return assistantMapper.showAllAssitant();
    }

    @Override
    public void updateAssitant(Assistant assistant) {
        assistantMapper.updateAssitant(assistant);
    }

    @Override
    public void AddAssitant(Assistant assistant) {
        assistantMapper.AddAssitant(assistant);
    }
}
