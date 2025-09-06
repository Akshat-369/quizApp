import { validateHorizontalPosition } from '@angular/cdk/overlay';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService, private snack:MatSnackBar) { }

  public user =
  {
    username: '',
    password: '',
    firstname: '',
    lastname: '',
    email: '',
    phone: '',
  }

  ngOnInit(): void {}

  formSubmit()
  {
    console.log(this.user);
    if(this.user.username=='' || this.user.username == null)
    {
      this.snack.open('Username is required' , '' ,
        {
          duration:3000,
        })

      return;
    }

    //addUser: userservice
    this.userService.addUser(this.user).subscribe(
      (data)=>{
        console.log(data);
        // alert('success');
        Swal.fire('Success', 'User is registered')
      },
      (error)=>{
        console.log(error);
        // alert('Something went wrong');
        this.snack.open('User already exixt!!','',
          {
            duration:3000
          })
      }
    )

  }


}
