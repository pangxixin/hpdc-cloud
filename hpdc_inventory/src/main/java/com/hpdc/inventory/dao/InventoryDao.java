package com.hpdc.inventory.dao;

import com.hpdc.inventory.pojo.HpdcInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryDao extends JpaRepository<HpdcInventory, String> {

    List<HpdcInventory> findAllByDescLike(String desc);

}