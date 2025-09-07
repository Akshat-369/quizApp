package com.quiz.Service;

import com.quiz.model.quiz.Question;
import com.quiz.model.quiz.Quiz;

import java.util.Set;

public interface QuestionSerivce {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionsOfQuiz(Quiz quiz);

    public void delete(Long quesId);
}
