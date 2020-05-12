package com.hpdc.tbox.service;

import com.hpdc.tbox.dao.TboxTypeDao;
import com.hpdc.tbox.pojo.TboxType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TboxTypeService {
    @Autowired
    private TboxTypeDao tboxTypeDao;

    // 获取周转箱类型列表
    public List<TboxType> selectTboxtypeList() {
        return tboxTypeDao.findAll();
    }

    // 增加周转箱类型
    public void add(TboxType tboxType) {
        tboxTypeDao.save(tboxType);
    }
}
