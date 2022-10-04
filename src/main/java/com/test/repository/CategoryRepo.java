package com.test.repository;

import com.test.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
//   Optional<Category> findByCategory(String category);
}
