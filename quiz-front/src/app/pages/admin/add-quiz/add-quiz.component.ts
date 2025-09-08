import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CategoryService } from 'src/app/services/category.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  categories=[]
  quizData = {
    title:'',
    description:'',
    maxMarks:'',
    numberOfQuestions:'',
    active:true,
    category: {
      cid:''
    }
  };
  constructor(private _cat:CategoryService,private _snack:MatSnackBar,private _quiz:QuizService) { }

  ngOnInit(): void {
    this._cat.categories().subscribe(
      (data:any)=>{
        //categories load
        this.categories=data;
        console.log(this.categories);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!','Error in loading data from server','error');
      }
    );
  }
//add quiz
  addQuiz(){
    if(this.quizData.title.trim()=='' || this.quizData.title==null){
     this._snack.open("Title Required !!",'',{
       duration:3000,
     });
     return;
    }
    if(this.quizData.description.trim()=='' || this.quizData.description==null){
      this._snack.open("Description Required !!",'',{
        duration:3000,
      });
      return;
     }
     if(this.quizData.maxMarks.trim()=='' || this.quizData.maxMarks==null){
      this._snack.open("Max Marks Required !!",'',{
        duration:3000,
      });
      return;
     }
     if(this.quizData.numberOfQuestions.trim()=='' || this.quizData.numberOfQuestions==null){
      this._snack.open("Number of Questions Required !!",'',{
        duration:3000,
      });
      return;
     }
     if(this.quizData.category.cid=='' || this.quizData.category.cid==null){
      this._snack.open("Category Required !!",'',{
        duration:3000,
      });
      return;
     }
    
    console.log(this.quizData);
    //validation
    
    //all done here we will submit the quiz to server
    this._quiz.addQuiz(this.quizData).subscribe(
      (data:any)=>{
        Swal.fire('Success !!','Quiz is added. You can add questions now.','success');
        this.quizData = {
          title:'',
          description:'',
          maxMarks:'',
          numberOfQuestions:'',
          active:true,
          category: {
            cid:''
          }
        };
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!','Server error !!','error');
      }
    );
}
}