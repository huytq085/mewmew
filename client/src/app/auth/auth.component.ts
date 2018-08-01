import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Errors } from '../core';
import { UserService } from '../core';
import { HttpHeaderResponse } from '@angular/common/http';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  errorMap: {} = {
    401: 'Email or password is invalid.'
  }

  isSubmitting: boolean = false;
  title: string = '';
  authType: string = '';
  authForm: FormGroup;
  errors: string = '';
  constructor(
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.authForm = this.fb.group({
      'email': ['', Validators.required],
      'password': ['', Validators.required],
      'avatar': [null, Validators.required]
    })
  }

  ngOnInit() {
    this.route.url.subscribe(data => {
      // Get the last piece of the URL (it's either 'login' or 'register')
      
      this.authType = data[data.length - 1].path;
      // Set a title for the page accordingly
      this.title = (this.authType === 'login') ? 'Sign In' : 'Sign Up';
      // add form control for username if this is the register page
      if (this.authType === 'register') {
        this.authForm.addControl('username', new FormControl('', Validators.required));
        this.authForm.addControl('fullName', new FormControl('', Validators.required));
        this.authForm.addControl('gender', new FormControl('', Validators.required));
        this.authForm.addControl('description', new FormControl('', Validators.required));
      }
    });
  }

  submitForm() {
    this.isSubmitting = true;
    let credentials = this.authForm.value;
    this.userService.attemptAuth(this.authType, credentials)
      .subscribe(
        data => {
          this.router.navigateByUrl('/')
        },
        (err: HttpHeaderResponse) => {
          this.errors = this.errorMap[err.status];
          this.isSubmitting = false;
        }
      );
  }


}
