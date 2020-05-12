package com.hpdc.qa.client.impl;

import com.hpdc.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

@Component //此注解表示把这个实现类对象交给spring容器
public class BaseClientImpl implements BaseClient {
    @Override
    public Result findById(String labelId) {
        return new Result(false, StatusCode.ERROR, "触发熔断器");
    }
}
