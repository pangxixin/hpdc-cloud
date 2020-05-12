package com.hpdc.iface.controller;

import com.hpdc.iface.service.IfaceService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/iface")
public class IfaceController {

    @Autowired
    private IfaceService ifaceService;

    // 获取联邦SKU数据
    @GetMapping(value = "/zhlb/skuData")
    public Result getZhlbSkuData() {
        return new Result(true, StatusCode.OK, "查询成功", ifaceService.getZhlbSkuData());
    }

    // 获取联邦客户数据
    @GetMapping(value = "/zhlb/khData")
    public Result getZhlbKhData() {
        return new Result(true, StatusCode.OK, "查询成功", ifaceService.getZhlbKhData());
    }

    // 获取联邦客户数据
    @GetMapping(value = "/zhlb/rkData")
    public Result getZhlbRkData() {
        return new Result(true, StatusCode.OK, "查询成功", ifaceService.getZhlbRkData());
    }

    // 获取联邦客户数据
    @GetMapping(value = "/zhlb/ckData")
    public Result getZhlbCkData() {
        return new Result(true, StatusCode.OK, "查询成功", ifaceService.getZhlbCkData());
    }

    // 将出库数据插入到数据库中
    @PostMapping(value = "/zhlb/ckData")
    public Result importZhlbCkData() {
        ifaceService.importZhlbCkdToDB();
        return new Result(true, StatusCode.OK, "出库单插入成功");
    }

    // 将入库数据插入到数据库中
    @PostMapping(value = "/zhlb/rkData")
    public Result importZhlbRkData() {
        ifaceService.importZhlbRkdToDB();
        return new Result(true, StatusCode.OK, "入库单插入成功");
    }
}
