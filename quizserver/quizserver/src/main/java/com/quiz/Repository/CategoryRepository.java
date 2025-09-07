package com.quiz.Repository;

import com.quiz.model.quiz.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
