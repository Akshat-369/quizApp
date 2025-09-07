package com.quiz.Controller;

import com.quiz.Repository.QuizRepository;
import com.quiz.Service.QuizService;
import com.quiz.model.quiz.Category;
import com.quiz.model.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add Quiz
    @PostMapping("/")
    public ResponseEntity<?> addQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Quiz quiz){
        return  ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get Quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }

    //get single Quiz
    @GetMapping("/{qid}")
    public Quiz getquiz(@PathVariable("qid") Long qid ){
        return this.quizService.getQuiz(qid);
    }

    //delete the quiz
    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid") Long qid){
        this.quizService.deleteQuiz(qid);
    }




}
