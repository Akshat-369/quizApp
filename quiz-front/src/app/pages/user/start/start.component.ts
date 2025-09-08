import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
})
export class StartComponent implements OnInit {
  qid;
  questions;

  isSubmit=false;

  timer:any;

  marksGot = 0;
  correctAnswers = 0;
  attempted = 0;

  constructor(
    private locationSt: LocationStrategy,
    private _route: ActivatedRoute,
    private _question: QuestionsService
  ) {}

  ngOnInit(): void {
    this.preventBackButton();
    this.qid = this._route.snapshot.params.qid;
    this.loadQuestions();
  }
  loadQuestions() {
    this._question.getQuestionsOfQuizForTest(this.qid).subscribe(
      (data: any) => {
        this.questions = data;

        this.timer= this.questions.length * 2 * 60;
  
          console.log(this.questions);
          this.startTimer();
      },
      (error) => {
        console.log(error);
        Swal.fire('Error', 'Error in loading question of quiz', 'error');
      }
    );
  }

  submitQuiz() {
    Swal.fire({
      title: 'Do you want to submit the quiz?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonText: 'Submit',
      icon:'info'
    }).then((e)=>{
      if(e.isConfirmed){

       this.evalQuiz();
          
      }
    });
    console.log(this.questions); // yaha par givenAnswer fill hoga
    // aage calculation bhi yahi se hoti hai
  }
  preventBackButton() {
    history.pushState(null, null, location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null, null, location.href);
    });
  }

  startTimer(){
    let t = window.setInterval(()=>{
      if(this.timer<=0){
        this.evalQuiz();
        clearInterval(t);
      }else{
        this.timer--;
      }
    }, 1000);
  }

  getFormattedTime(){

      let mm=Math.floor(this.timer/60);
      let ss = this.timer - mm *60;
      return `${mm} min : ${ss} sec`;
  }

  evalQuiz(){


    this._question.evalQuiz(this.questions).subscribe(
      (data:any)=>{
        console.log(data);
        this.isSubmit=true;
        this.marksGot=data.marksGot;
        this.correctAnswers=data.correctAnswers;
        this.attempted=data.attempted;

      },
      (error)=>{
        console.log(error);

      }
    )
  }

   //  this.isSubmit  = true;
     //   this.questions.forEach(q => {
       //   if(q.givenAnswer==q.answer){
         //   this.correctAnswers++;
           // let marksSingle=
        //    this.questions[0].quiz.maxMarks/this.questions.length;
         // this.marksGot+=marksSingle;
         // }

        //  if(q.givenAnswer.trim()!=''){
     //       this.attempted++;
        //  }
      //  });
       //       console.log("Correct Answers : "+ this.correctAnswers);
      //    console.log("marks got :"+ this.marksGot);
      //    console.log("attempter:"+ this.attempted);
//  }
}
