package com.congdat.notaryweb.repository.impl;

import com.congdat.notaryweb.repository.custom.NewsRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRepositoryImpl implements NewsRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public NewsRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
