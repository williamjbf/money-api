package com.github.williamjbf.moneyapi.service;

import com.github.williamjbf.moneyapi.model.Category;
import com.github.williamjbf.moneyapi.repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ResponseEntity<Category> update(Long id, Category category){
        Optional<Category> savedCategory = categoryRepository.findById(id);
        if(savedCategory.isEmpty()){
            throw new EmptyResultDataAccessException(1);
        }

        BeanUtils.copyProperties(category,savedCategory.get(),"id");
        categoryRepository.save(savedCategory.get());
        return ResponseEntity.ok(savedCategory.get());

    }
}
