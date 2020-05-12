package com.hpdc.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hpdc.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 *
 * @author Administrator
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    List<Recruit> findTop6ByStateOrderByCreatetimeDesc(String state);

    List<Recruit> findTop6ByStateIsNotOrderByCreatetimeDesc(String state);
}
