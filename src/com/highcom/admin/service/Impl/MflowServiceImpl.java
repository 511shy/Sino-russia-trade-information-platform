package com.highcom.admin.service.Impl;

import com.highcom.admin.dao.MflowMapper;
import com.highcom.admin.pojo.Mflow;
import com.highcom.admin.pojo.Mtype;
import com.highcom.admin.service.MflowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("MflowService")
public class MflowServiceImpl implements MflowService {
    @Autowired
    private MflowMapper mflowMapper;

    @Override
    public void mflowInsert(Mflow mflow) {
        mflowMapper.mflowInsert(mflow);
    }

    @Override
    public List<Mtype> moduleShow() {
        return mflowMapper.moduleShow();
    }

    @Override
    public Mflow mflowbyid(int moduleid) {
        return mflowMapper.mflowById(moduleid);
    }

    @Override
    public Mflow mflowForOne(int id) {
        return mflowMapper.mflowForOne(id);
    }

	@Override
	public void mflowUpdate(Mflow mflow) {
		 mflowMapper.mflowUpdate(mflow);
	}
}
