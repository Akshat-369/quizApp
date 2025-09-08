import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';
import { CategoryService } from 'src/app/services/category.service';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-quiz',
  templateUrl: './update-quiz.component.html',
  styleUrls: ['./update-quiz.component.css'],
})
export class UpdateQuizComponent implements OnInit {
  constructor(
    private _route: ActivatedRoute,
    private _quiz: QuizService,
    private _cat: CategoryService,
    private _router:Router
  ) {}

  qid = 0;
  quiz;
  categories;

  ngOnInit(): void {
    this.qid = this._route.snapshot.params.qid;
    //alert(this.qid);
    this._quiz.getQuiz(this.qid).subscribe(
      (data: any) => {
        this.quiz = data;
        console.log(this.quiz);
      },
      (error) => {
        console.log(error);
        alert('Error in loading quiz data');
      }
    );

    this._cat.categories().subscribe(
      (data: any) => {
        this.categories = data;
        console.log(this.categories);
      },
      (error) => {
        console.log(error);
        alert('Error in loading categories');
      }
    );
  }
  
//update form submit
public updateData(){
    this._quiz.updateQuiz(this.quiz).subscribe(
      (data)=>{
        Swal.fire('Success !!','Quiz is updated','success').then((e)=>{
          this._router.navigate(['/admin/quizzes']);
      });
    },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!','Error in updating quiz','error');
      }
    );
  }
}
