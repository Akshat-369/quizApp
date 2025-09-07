package com.quiz.Service;

import com.quiz.model.quiz.Category;
import com.quiz.model.quiz.Quiz;

import java.util.Set;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);


}
