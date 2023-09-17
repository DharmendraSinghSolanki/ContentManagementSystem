import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators} from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ContentService } from '../services/content.service';
import jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb:FormBuilder,private _snackBar:MatSnackBar,private userService:ContentService,private router:Router) { }

  ngOnInit(): void {
  }
  loginForm = this.fb.group({
    email: ['',Validators.required],
    password: ['',Validators.required]
   });

   get email() { return this.loginForm.get("email") }
   get password() { return this.loginForm.get("password") }

   decodeData:any;
   decodeEmail:any;
   data:any;

   onSubmit(){
    this.userService.loginCheck(this.loginForm.value).subscribe(
      response => {
        console.log(response);
        this.data=response;
        
    console.log(this.data.token);
    this.decodeData=jwt_decode(this.data.token);
    console.log(this.decodeData)
    this.decodeEmail=this.decodeData.sub;
    console.log(this.decodeEmail);
        localStorage.setItem('emailId',this.decodeEmail);
        localStorage.setItem('jwt',this.data.token);
       alert('Login success');
      }
    )
    
    this._snackBar.open('Congrats, you have successfully the LogedIn!!', 'success', {​
      duration: 5000,​
       panelClass: ['mat-toolbar', 'mat-primary']​
     }) 
     this.router.navigateByUrl("dashBoard")
  }
}