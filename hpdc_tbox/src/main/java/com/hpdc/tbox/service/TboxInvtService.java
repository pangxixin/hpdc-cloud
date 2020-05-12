package com.hpdc.tbox.service;

import com.hpdc.tbox.dao.TboxInvtDao;
import com.hpdc.tbox.pojo.TboxInv;
import com.hpdc.tbox.pojo.TboxInvt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TboxInvtService {
    @Autowired
    private TboxInvtDao tboxInvtDao;

    public List<TboxInvt> selectTboxinvtList() {
        return tboxInvtDao.findAll();
    }

    public void add(TboxInvt tboxInvt) {
        tboxInvtDao.save(tboxInvt);
    }
}
