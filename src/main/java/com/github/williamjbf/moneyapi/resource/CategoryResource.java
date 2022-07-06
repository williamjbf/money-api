package com.github.williamjbf.moneyapi.resource;

import com.github.williamjbf.moneyapi.event.ResourceCreatedEvent;
import com.github.williamjbf.moneyapi.model.Category;
import com.github.williamjbf.moneyapi.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    private final CategoryRepository categoryRepository;

    private final ApplicationEventPublisher publisher;

    public CategoryResource(CategoryRepository categoryRepository, ApplicationEventPublisher publisher) {
        this.categoryRepository = categoryRepository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Category> list(){
        return categoryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Category> create(@Valid @RequestBody Category category, HttpServletResponse response){
        Category savedCategory = categoryRepository.save(category);

        publisher.publishEvent(new ResourceCreatedEvent(this, response, savedCategory.getId()));

        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id){
        Optional<Category> category = categoryRepository.findById(id);
        return category.isPresent() ? ResponseEntity.ok(category.get()) : ResponseEntity.notFound().build();
    }
}
