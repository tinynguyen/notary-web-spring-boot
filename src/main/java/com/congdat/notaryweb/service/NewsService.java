package com.congdat.notaryweb.service;

import com.congdat.notaryweb.model.News;

import java.security.Principal;
import java.util.List;

public interface NewsService {

    List<News> findAll();

    List<News> findAllByCategory(String categoryId);

    News findById(String id);

    News save(News news, String userId);

    News update(News news, String userId);

    void delete(String id);

}
