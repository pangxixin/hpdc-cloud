package com.hpdc.friend.dao;

import com.hpdc.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface FriendDao extends JpaRepository<Friend, String> {

    Friend findByUseridAndFriendid(String userid, String friendid);

    @Modifying
    @Query(value = "UPDATE hpdc_friend.tb_friend SET islike=? WHERE userid=? AND friendid=?", nativeQuery = true)
    void updateIslike(String islike, String userid, String friendid);


    @Modifying
    @Query(value = "DELETE FROM hpdc_friend.tb_friend WHERE userid=? AND friendid=?", nativeQuery = true)
    void deleteFriend(String userid, String friendid);
}
