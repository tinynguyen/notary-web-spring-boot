package com.congdat.notaryweb.repository;

import com.congdat.notaryweb.model.News;
import com.congdat.notaryweb.repository.custom.NewsRepositoryCustom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends MongoRepository<News, String>, NewsRepositoryCustom {

    Optional<News> findById(String id);

    @Query(value = "{'category.id' : ?0}")
    List<News> findAllByCategory(String categoryId);
}
