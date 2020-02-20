package com.congdat.notaryweb.repository;

import com.congdat.notaryweb.model.Category;
import com.congdat.notaryweb.model.News;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {

}
