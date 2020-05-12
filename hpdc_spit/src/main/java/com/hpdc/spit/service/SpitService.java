package com.hpdc.spit.service;

import com.hpdc.spit.dao.SpitDao;
import com.hpdc.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpitService {

    @Autowired
    private SpitDao spitDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    public void save(Spit spit) {
        spit.set_id(idWorker.nextId() + "");//分布id
        spit.setPublishtime(new Date());//发布日期
        spit.setShare(0);//分享数
        spit.setVisits(0);//访问数
        spit.setThumbup(0);//吐槽数
        spit.setComment(0);//回复数
        spit.setState("1");//状态

        //如果当前添加的吐槽有父节点，则父节点的吐槽回复数要+1
        //新增吐槽时先判断是否有父节点。
        if (spit.getParentid() != null && !"".equals(spit.getParentid())) {
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update = new Update();
            update.inc("comment", 1);
            mongoTemplate.updateFirst(query, update, "spit");
        }

        spitDao.save(spit);
    }

    public void update(Spit spit) {
        spitDao.save(spit);
    }

    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    public Page<Spit> findByParentId(String parentId, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(parentId, pageable);
    }

    public void thumbup(String spitId) {
        //方法1 效率低
//        Spit spit = spitDao.findById(spitId).get();
//        spit.setThumbup((spit.getThumbup() == null ? 0 : spit.getThumbup()) + 1);
//        spitDao.save(spit);
        //方法2 使用原生mongo命令来自增 db.spit.update({"_id", "1"},{$inc:{thumbup:NumberInt(1)}})
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        Update update = new Update();
        update.inc("thumbup", 1);
        mongoTemplate.updateFirst(query, update, "spit");
    }
}
