package com.hpdc.inventory.service;

import com.hpdc.inventory.dao.InventoryDao;
import com.hpdc.inventory.pojo.HpdcInventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    private InventoryDao inventoryDao;

    /**
     * 根据desc查询库存
     */
    public List<HpdcInventory> findAllByDescLike(String desc) {
        return inventoryDao.findAllByDescLike(desc);
    }
}
