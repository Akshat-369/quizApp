package com.quiz.Repository;

import com.quiz.model.quiz.Category;
import com.quiz.model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    public List<Quiz> findBycategory(Category category);

    public List<Quiz> findByActive(boolean b);
    public  List<Quiz> findByCategoryAndActive(Category c,boolean b);
}
