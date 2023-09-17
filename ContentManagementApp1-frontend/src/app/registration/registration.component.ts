import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ContentService } from '../services/content.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit{
  constructor(private fb:FormBuilder,private _snackBar:MatSnackBar,private userService:ContentService,private route:Router){}
  ngOnInit(): void {
  }

  data:any;

  registrationForm = this.fb.group({
    fullName: ['',Validators.required],
    email: ['',[Validators.required,Validators.email]],
    password: ['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    confirmPassword: ['',[Validators.required,Validators.pattern(/^(?=.*\d)(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z]).{8,}$/)]],
    phoneNumber: ['',[Validators.required,Validators.pattern(/^[789]\d{9,9}$/)]],
    address: ['',Validators.required]
  },{validators: [this.mustMatchValidator]});

  get fullName(){ return this.registrationForm.get("fullName")}
  get email() { return this.registrationForm.get("email") }
  get password() { return this.registrationForm.get("password") }
  get confirmPassword() { return this.registrationForm.get("confirmPassword") }
  get phoneNumber() { return this.registrationForm.get("phoneNumber") }
  get address(){return this.registrationForm.get("address")}

  mustMatchValidator(fg: AbstractControl) {
    const passwordValue = fg.get("password")?.value;
    const confirmPasswordValue = fg.get("confirmPassword")?.value;
    if (!passwordValue || !confirmPasswordValue) {
      return null;
    }
    if (passwordValue != confirmPasswordValue) {
        return { mustMatch: false }
    }
    return null;
  }
  
  onSubmit(){

    // console.log(this.registrationForm.value);
    // this.userService.addUser(this.registrationForm.value).subscribe(
    //   response => {
    //     this.data = response;
    //     console.log(response); 
    //   this._snackBar.open('You have Successfully Registred!!', 'Success', {​
    //   duration: 5000,​
    //    panelClass: ['mat-toolbar', 'mat-primary']​
    //  }) 
    //  this.registrationForm.reset();})

    this.userService.addUser(this.registrationForm.value).subscribe(
      response => {
        this.data = response;
        console.log(response);
 
        this._snackBar.open('Congrats, you registered successfully!!', 'Success', {
          duration: 5000,
          panelClass: ['mat-toolbar', 'mat-primary']
        })

        this.registrationForm.reset();
        this.route.navigateByUrl("")


      }
    )

  }
}


