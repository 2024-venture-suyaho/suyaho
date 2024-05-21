package com.venture.suyaho.deal.service;

import com.venture.suyaho.deal.entity.Category;
import com.venture.suyaho.deal.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    // 서비스 메서드 구현
}