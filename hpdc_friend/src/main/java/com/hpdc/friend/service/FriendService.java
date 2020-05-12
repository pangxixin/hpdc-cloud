package com.hpdc.friend.service;

import com.hpdc.friend.dao.FriendDao;
import com.hpdc.friend.dao.NoFriendDao;
import com.hpdc.friend.pojo.Friend;
import com.hpdc.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;
    @Autowired
    private NoFriendDao noFriendDao;

    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid四否有数据，有就是重复添加好友，返回0.
        Friend friend = friendDao.findByUseridAndFriendid(userid, friendid);
        if (friend != null) {
            return 0;
        }
        //直接添加好友，让好友表中的userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据
        if (friendDao.findByUseridAndFriendid(friendid, userid) != null) {
            //如果有，把双方的状态都改为1
            friendDao.updateIslike("1", userid, friendid);
            friendDao.updateIslike("1", friendid, userid);
        }
        return 1;
    }

    public int addNoFriend(String userid, String friendid){
        //先判断是否已经是好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend!=null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    public void deleteFriend(String userid, String friendid) {
        //1、删除好友表中userid到friendid这条数据
        friendDao.deleteFriend(userid, friendid);
        //2、更新friendid到userid的islike字段为0
        friendDao.updateIslike("0", friendid, userid);
        //3、向非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}