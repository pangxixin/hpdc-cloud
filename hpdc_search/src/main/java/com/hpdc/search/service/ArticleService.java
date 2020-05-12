package com.hpdc.search.service;

import com.hpdc.search.dao.ArticleDao;
import com.hpdc.search.pojo.Article;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;

    public void save(Article article) {
        articleDao.save(article);
    }

    public List<Article> findAll(){
        return (List<Article>) articleDao.findAll();
    }

    public Page<Article> findByKey(String key, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        return articleDao.findByTitleOrContentLike(key, key, pageable);
    }
}
