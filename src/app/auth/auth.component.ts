import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Errors } from '../core';
import { UserService } from '../core';


@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {

  isSubmitting: boolean = false;
  title: string = '';
  authType: string = '';
  authForm: FormGroup;
  errors: Errors = new Errors();

  constructor(
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.authForm = this.fb.group({
      'email': ['', Validators.required],
      'password': ['', Validators.required]
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
      }
    });
  }

  submitForm() {
    this.errors = new Errors();
    this.isSubmitting = true;
    let credentials = this.authForm.value;

    this.userService.attemptAuth(this.authType, credentials)
      .subscribe(
        data => {
          this.router.navigateByUrl('/')
        },
        err => {
          this.errors = err;
          this.isSubmitting = false;
        }
      );
    console.log(credentials);
  }

}
