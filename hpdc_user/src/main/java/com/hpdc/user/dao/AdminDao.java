package com.hpdc.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hpdc.user.pojo.Admin;

/**
 * 数据访问接口
 *
 */
public interface AdminDao extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {
    Admin findByLoginname(String loginname);
}
