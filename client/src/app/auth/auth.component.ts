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
    401: 'Sai email hoặc mật khẩu',
    409: 'Email hoặc tên tài khoản bị trùng'
  }

  avatarTemp: string;

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
      'email': new FormControl('', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')
      ])),
      'password': ['', Validators.compose([
        Validators.maxLength(25),
        Validators.minLength(6),
        Validators.required
      ])],
      'avatar': [null]
    })
  }

  ngOnInit() {
    this.route.url.subscribe(data => {
      // Get the last piece of the URL (it's either 'login' or 'register')
      this.authType = data[data.length - 1].path;
      // Set cho biến title
      this.title = (this.authType === 'login') ? 'ĐĂNG NHẬP' : 'ĐĂNG KÝ';
      // Thêm các form control
      if (this.authType === 'register') {
        this.authForm.addControl('username', new FormControl('', Validators.compose([
          Validators.maxLength(25),
          Validators.minLength(5),
          Validators.required
        ])));
        this.authForm.addControl('fullName', new FormControl('', Validators.compose([
          Validators.maxLength(25),
          Validators.minLength(5),
          Validators.required
        ])));
        this.authForm.addControl('gender', new FormControl('', Validators.required));
        this.authForm.addControl('description', new FormControl(''));
      }
    });
  }

  submitForm() {
    this.isSubmitting = true;
    let credentials = this.authForm.value;
    this.userService.attemptAuth(this.authType, credentials)
      .subscribe(
        data => {
          console.log(data);
          if (this.avatarTemp) {
            data.avatar = this.avatarTemp;
          }
          this.router.navigateByUrl('/')
        },
        (err: HttpHeaderResponse) => {
          console.log()
          this.errors = this.errorMap[err.status];
          this.isSubmitting = false;
        }
      );
  }
  changeAvatar(e) {
    this.avatarTemp = e.base64;
    this.authForm.controls['avatar'].setValue(e.raw);
  }


}
