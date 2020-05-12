package com.hpdc.tbox.controller;

import com.hpdc.tbox.pojo.TboxInv;
import com.hpdc.tbox.service.TboxInvService;
import com.hpdc.tbox.service.TboxTypeService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/tbox/tboxinv")
public class TboxInvController {
    @Autowired
    private TboxInvService tboxInvService;

    // 获取周转箱明细
    @RequestMapping("/list")
    public Result tboxInvList (){
        return new Result(true, StatusCode.OK, "获取周转箱列表成功", tboxInvService.selectTboxinvList());
    }

    // 新增周转箱
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody TboxInv tboxInv) {
        tboxInvService.add(tboxInv);
        return new Result(true, StatusCode.OK, "增加成功");
    }
}
