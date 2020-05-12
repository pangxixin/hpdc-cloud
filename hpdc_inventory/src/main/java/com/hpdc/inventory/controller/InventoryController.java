package com.hpdc.inventory.controller;


import com.hpdc.inventory.service.InventoryService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(value = "/search/{desc}")
    public Result findAllByDescLike(@PathVariable String desc) {
        return new Result(true, StatusCode.OK, "查询成功", inventoryService.findAllByDescLike("%" + desc + "%"));
    }

}
