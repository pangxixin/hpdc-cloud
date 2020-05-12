package com.hpdc.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("hpdc-user")
public interface UserClient {
    @RequestMapping(value = "/user/{userid}/{friendid}/{count}", method = RequestMethod.PUT)
    void updateFanscountAndFollowcount(@PathVariable String userid, @PathVariable String friendid, @PathVariable int count);
}
