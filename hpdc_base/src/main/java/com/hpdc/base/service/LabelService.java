package com.hpdc.base.service;

import com.hpdc.base.dao.LabelDao;
import com.hpdc.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll() {
        return labelDao.findAll();
    }

    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    public void save(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    public void update(Label label) {
        labelDao.save(label);
    }

    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(getLabelSpecification(label));
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return labelDao.findAll(getLabelSpecification(label), pageable);
    }

    private Specification<Label> getLabelSpecification(Label label) {
        return (Specification<Label>) (root, query, cb) -> {
            List<Predicate> list = new ArrayList<>();
            if (label.getLabelname() != null && !"".equals(label.getLabelname())) {
                Predicate predicate = cb.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");// where labelName like %JAVA%
                list.add(predicate);
            }
            if (label.getState() != null && !"".equals(label.getState())) {
                Predicate predicate = cb.like(root.get("state").as(String.class), label.getState());// state=1
                list.add(predicate);
            }
            Predicate[] predicates = new Predicate[list.size()];
            list.toArray(predicates);
            return cb.and(predicates);
        };
    }

}
