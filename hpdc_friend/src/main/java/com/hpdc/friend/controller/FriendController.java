package com.hpdc.friend.controller;

import com.hpdc.friend.client.UserClient;
import com.hpdc.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RefreshScope
@RestController
@RequestMapping(value = "/friend")
public class FriendController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private FriendService friendService;
    @Autowired
    private UserClient userClient;

    @RequestMapping(value = "/like/{friendid}/{type}", method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String friendid, @PathVariable String type) {
        //1、先验证是否登录，拿到当前登录的用户ID
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (StringUtils.isEmpty(claims)) {
            return new Result(false, StatusCode.LOGINERROR, "权限不足, 请先登录!");
        }
        String userid = claims.getId();
        //2、判断添加好友还是非好友。
        if (type != null) {
            if (type.equals("1")) {
                //添加好友
                int flag = friendService.addFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加好友");
                }
                if (flag == 1) {
                    //调用user微服的更新粉丝数和关注数的方法
                    userClient.updateFanscountAndFollowcount(userid, friendid, 1);
                    return new Result(true, StatusCode.OK, "成功添加好友");
                }
            } else if (type.equals("2")) {
                //添加非好友
                int flag = friendService.addNoFriend(userid, friendid);
                if (flag == 0) {
                    return new Result(false, StatusCode.ERROR, "不能重复添加非好友");
                }
                if (flag == 1) {
                    return new Result(true, StatusCode.OK, "成功添加好友");
                }
            }
        }
        return new Result(false, StatusCode.ERROR, "类型参数异常");
    }

    @RequestMapping(value = "/{friendid}", method = RequestMethod.DELETE)
    public Result delFriend(@PathVariable String friendid){
        //1、先验证是否登录，拿到当前登录的用户ID
        Claims claims = (Claims) request.getAttribute("claims_user");
        if (StringUtils.isEmpty(claims)) {
            return new Result(false, StatusCode.LOGINERROR, "权限不足, 请先登录!");
        }
        //2、得到用户id
        String userid = claims.getId();
        //3、删除登录id的好友
        friendService.deleteFriend(userid, friendid);
        //4、用户粉丝数减1
        userClient.updateFanscountAndFollowcount(userid, friendid, -1);

        return new Result(true, StatusCode.OK, "删除成功");
    }

}
