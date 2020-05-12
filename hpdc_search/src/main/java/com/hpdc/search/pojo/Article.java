package com.hpdc.search.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@Document(indexName = "hpdc_article", type = "article")
public class Article implements Serializable {
    @Id
    private String id;

    //是否索引 就表示该域是否需要被搜索
    //是否分词 就表示搜索的时候是整体匹配还是单词匹配
    //是否存储 就表示是否在页面上显示
    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;   //文章标题

    @Field(index = true, analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content; //文章内容

    private String state;   //审核状态

}
