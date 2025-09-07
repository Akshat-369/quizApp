package com.quiz.Repository;

import com.quiz.model.quiz.Question;
import com.quiz.model.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Set<Question> findByQuiz(Quiz quiz);
}
