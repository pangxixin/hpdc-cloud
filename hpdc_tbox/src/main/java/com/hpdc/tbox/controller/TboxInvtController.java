package com.hpdc.tbox.controller;

import com.hpdc.tbox.pojo.TboxInvt;
import com.hpdc.tbox.service.TboxInvtService;
import com.hpdc.tbox.service.TboxTypeService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/tbox/tboxinvt")
public class TboxInvtController {
    @Autowired
    private TboxInvtService tboxInvtService;

    // 获取周转箱事务
    @RequestMapping("/list")
    public Result tboxInvtList (){
        return new Result(true, StatusCode.OK, "获取周转箱事务成功", tboxInvtService.selectTboxinvtList());
    }

    // 新增周转箱事务
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody TboxInvt tboxInvt) {
        tboxInvtService.add(tboxInvt);
        return new Result(true, StatusCode.OK, "增加操作事务成功");
    }

}
