package com.unibuc.ex1curs11.repository;

import com.unibuc.ex1curs11.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
