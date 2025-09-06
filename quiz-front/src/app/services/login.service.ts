import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }


  //Generate Token

  public generateToken(loginData: any)
  {
    return this.http.post(`${baseUrl}/generate-token`,loginData);
  }

  //Login user: set token on local storagre
  public loginUser(token)
  {
    localStorage.setItem("token",token);
    return true;
  }

  //isLogin: user is Logged in or not
  public isLoggerdIn()
  {
    let tokenStr=localStorage.getItem("token")
    if(tokenStr==undefined || tokenStr==''||tokenStr==null)
    {
      return false;
    }else{
      return true;
    }
  }

  // remove token from local storage
  public logout()
  {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //get token from local storage
  public getToken()
  {
    return localStorage.getItem('token');
  }

  //set user detail
  public setUser(user)
  {
    localStorage.setItem('user', JSON.stringify(user));
  }

  //get User details
  public getUser()
  {
    let userStr=localStorage.getItem("user");
    if(userStr!=null)
    {
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }

  //get user role (admin or user)
  public getUserRole()
  {
    let user=this.getUser();
    return user.authorities[0].authority;
  }
}
