package com.congdat.notaryweb.service;

import com.congdat.notaryweb.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category save(Category category, String userId);

    Category update(Category category, String id);

    Category delete(String id);

}
