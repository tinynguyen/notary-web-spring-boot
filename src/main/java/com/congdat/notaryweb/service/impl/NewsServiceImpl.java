package com.congdat.notaryweb.service.impl;

import com.congdat.notaryweb.model.News;
import com.congdat.notaryweb.repository.NewsRepository;
import com.congdat.notaryweb.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;
    
    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public List<News> findAllByCategory(String categoryId) {
        return newsRepository.findAllByCategory(categoryId);
    }

    @Override
    public News findById(String id) {
        News foundNews = newsRepository.findById(id).orElse(null);
        return foundNews;
    }

    @Override
    public News save(News news, String userId) {
        news.setCreatedBy(userId);
        news.setCreatedDate(new Date(System.currentTimeMillis()));
        return newsRepository.save(news);
    }

    @Override
    public News update(News news, String userId) {
        news.setModifiedBy(userId);
        news.setModifiedDate(new Date(System.currentTimeMillis()));
        return newsRepository.save(news);
    }

    @Override
    public void delete(String id) {
        News foundNews = newsRepository.findById(id).orElse(null);
        newsRepository.delete(foundNews);
    }
}
