package com.quiz.Controller;

import com.quiz.Service.QuestionSerivce;
import com.quiz.Service.QuizService;
import com.quiz.model.quiz.Question;
import com.quiz.model.quiz.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionSerivce questionSerivce;

    @Autowired
    private QuizService quizService;

    //add question
    @PostMapping("/")
    public ResponseEntity<?> add(@RequestBody Question question){
        return ResponseEntity.ok(this.questionSerivce.addQuestion(question));
    }

    //update question
    @PutMapping("/")
    public ResponseEntity<?> update(@RequestBody Question question){
        return  ResponseEntity.ok(this.questionSerivce.updateQuestion(question));
    }

    //get all question of any quiz
    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qid") Long qid){

//        Quiz quiz = new Quiz();
//        quiz.setQid(qid);
//        Set<Question> questionsOfQuiz = this.questionSerivce.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionsOfQuiz);

        Quiz quiz = this.quizService.getQuiz(qid);
        Set<Question> questions = quiz.getQuestions();

        List list = new ArrayList<>(questions);
        if (list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
             list = list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }

    //get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable("quesId") Long quesId){
        return this.questionSerivce.getQuestion(quesId);
    }

    //delete
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId ){
        this.questionSerivce.delete(quesId);
    }
}
