import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionsService } from 'src/app/services/questions.service';
import Swal from 'sweetalert2';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-view-quiz-questions',
  templateUrl: './view-quiz-questions.component.html',
  styleUrls: ['./view-quiz-questions.component.css']
})
export class ViewQuizQuestionsComponent implements OnInit {

  qId;
  qTitle;
  questions = [ 

  ];

  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionsService,
    private _snack:MatSnackBar
  ) { }

  ngOnInit(): void {
    this.qId = this._route.snapshot.params.qid;
    this.qTitle = this._route.snapshot.params.title;
    this._question.getQuestionsOfQuiz(this.qId).subscribe((data:any)=>{
      this.questions = data;
      console.log(this.questions);
    },
    (error)=>{
      console.log(error);
      alert("Error in loading questions of quiz");
    })
  }

  //delete question
  deleteQuestion(qid){
  
       Swal.fire({
        icon:'info',
        showCancelButton:true,
        confirmButtonText:'Delete',
        title:'Are you sure?, Want to delete this question?',
       }).then((result)=>{
         
        if(result.isConfirmed){
          this._question.deleteQuestion(qid).subscribe(
            (data:any)=>{
            this._snack.open('Question Deleted','',{
              duration:3000,
            });
             this.questions = this.questions.filter((q)=>q.quesid != qid)
            },
            (error)=>{
              this._snack.open('Eror in deleting question','',{
                duration:3000,
              })
            }
          )
        }
       })
  }
}
