package com.github.williamjbf.moneyapi.repository;

import com.github.williamjbf.moneyapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
