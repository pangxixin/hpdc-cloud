package com.hpdc.tbox.service;

import com.hpdc.tbox.dao.TboxInvDao;
import com.hpdc.tbox.pojo.TboxInv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TboxInvService {
    @Autowired
    private TboxInvDao tboxInvDao;

    public List<TboxInv> selectTboxinvList() {
        return tboxInvDao.findAll();
    }

    public void add(TboxInv tboxInv) {
        tboxInvDao.save(tboxInv);
    }
}
