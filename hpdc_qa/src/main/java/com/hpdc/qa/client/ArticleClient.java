package com.hpdc.qa.client;

import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "hpdc-article")
public interface ArticleClient {
    @RequestMapping(value = "/article/{articleId}", method = RequestMethod.GET)
    Result findById(@PathVariable("articleId") String articleId);
}
