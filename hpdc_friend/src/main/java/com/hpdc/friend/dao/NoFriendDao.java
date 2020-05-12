package com.hpdc.friend.dao;

import com.hpdc.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface NoFriendDao extends JpaRepository<NoFriend, String> {

    NoFriend findByUseridAndFriendid(String userid, String friendid);

}
