import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
import { AddCategoryComponent } from '../pages/admin/add-category/add-category.component';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private _http:HttpClient) { }

  public quizzes(){
    return this._http.get(`${baseUrl}/quiz/`);
  }

  //add quiz
  public addQuiz(quiz){
    return this._http.post(`${baseUrl}/quiz/`,quiz);
  }
  //delete quiz
  public deleteQuiz(qid){
    return this._http.delete(`${baseUrl}/quiz/${qid}`);
  }

  //get single quiz
  public getQuiz(qid){
    return this._http.get(`${baseUrl}/quiz/${qid}`);
  }
  
  //update quiz
  public updateQuiz(quiz){  
    return this._http.put(`${baseUrl}/quiz/`,quiz);
  }

  //get Quizzes of category
  public getQuizzesOfCategory(cid){
    return this._http.get(`${baseUrl}/quiz/category/${cid}`);
  }
  //get active quizzes
   public getActiveQuizzes(){
    return this._http.get(`${baseUrl}/quiz/active`);
  }

  //get  active quizzes of category
   public getActiveQuizzesOfCategory(cid){
    return this._http.get(`${baseUrl}/quiz/category/active/${cid}`);
  }
}
