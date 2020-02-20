package com.congdat.notaryweb.service.impl;

import com.congdat.notaryweb.model.Category;
import com.congdat.notaryweb.repository.CategoryRepository;
import com.congdat.notaryweb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category, String userId) {
        category.setCreatedBy(userId);
        category.setCreatedDate(new Date(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, String id) {
        return null;
    }

    @Override
    public Category delete(String id) {
        return null;
    }
}
