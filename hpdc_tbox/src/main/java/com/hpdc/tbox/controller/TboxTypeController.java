package com.hpdc.tbox.controller;

import com.hpdc.tbox.pojo.TboxType;
import com.hpdc.tbox.service.TboxTypeService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RefreshScope
@RequestMapping("/tbox/tboxtype")
public class TboxTypeController {
    @Autowired
    private TboxTypeService tboxTypeService;

    // 获取周转箱类型
    @GetMapping("/list")
    public Result typeList (){
        return new Result(true, StatusCode.OK, "获取周转箱类型成功", tboxTypeService.selectTboxtypeList());
    }

    // 新增周转箱类型
    @PostMapping()
    public Result add(@RequestBody TboxType tboxType) {
        tboxTypeService.add(tboxType);
        return new Result(true, StatusCode.OK, "增加周转箱类型成功");
    }

}
