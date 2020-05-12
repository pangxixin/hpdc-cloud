package com.hpdc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hpdc.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {
    User findByMobile(String mobile);

    @Modifying
    @Query(value = "UPDATE hpdc_user.tb_user SET fanscount=fanscount+? WHERE id=?", nativeQuery = true)
    void updateFanscount(int count, String friendid);

    @Modifying
    @Query(value = "UPDATE hpdc_user.tb_user SET followcount=followcount+? WHERE id=?", nativeQuery = true)
    void updateFollowcount(int count, String userid);

}
