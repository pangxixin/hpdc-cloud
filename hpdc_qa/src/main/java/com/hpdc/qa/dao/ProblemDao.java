package com.hpdc.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hpdc.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 数据访问接口
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {

    @Query(nativeQuery = true, value = "SELECT * FROM hpdc_qa.tb_problem, hpdc_qa.tb_pl WHERE id = problemid AND labelid = ? Order By replytime DESC")
    Page<Problem> newlist(String labelid, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM hpdc_qa.tb_problem, hpdc_qa.tb_pl WHERE id = problemid AND labelid = ? Order By reply DESC")
    Page<Problem> hotlist(String labelid, Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM hpdc_qa.tb_problem, hpdc_qa.tb_pl WHERE id = problemid AND labelid = ? AND reply = 0 Order By createtime DESC")
    Page<Problem> waitlist(String labelid, Pageable pageable);
}
