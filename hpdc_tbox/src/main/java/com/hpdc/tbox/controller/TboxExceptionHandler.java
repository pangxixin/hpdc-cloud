package com.hpdc.tbox.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TboxExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return new Result(false, StatusCode.ERROR, "发生了错误！请联系信息部");
    }

}
