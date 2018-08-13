import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';

import swal from 'sweetalert';

import { User, UserService } from '../core';

@Component({
  selector: 'app-settings-page',
  templateUrl: './settings.component.html'
})
export class SettingsComponent implements OnInit {
  user: User = {} as User;
  settingsForm: FormGroup;
  errors: Object = {};
  isSubmitting = false;
  form: FormGroup;
  avatarTemp: string;
  constructor(
    private router: Router,
    private userService: UserService,
    private fb: FormBuilder
  ) {
    // create form group using the form builder
    this.settingsForm = this.fb.group({
      image: '',
      username: '',
      description: '',
      email: new FormControl('', Validators.compose([
        Validators.required,
        Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$')
      ])),
      password: ['', Validators.required],
      fullName: new FormControl('', Validators.compose([
        Validators.maxLength(25),
        Validators.minLength(5),
        Validators.required
      ])),
      gender: new FormControl('', Validators.required),
      avatar: [null]
    });
  }

  ngOnInit() {
    Object.assign(this.user, this.userService.getCurrentUser());
    // Fill the form
    this.settingsForm.patchValue(this.user);
  }

  logout() {
    this.userService.purgeAuth();
    this.router.navigateByUrl('/');
  }

  abc(e) {
  }

  submitForm() {
    this.isSubmitting = true;

    // update the model
    this.updateUser(this.settingsForm.value);

    this.userService
      .update(this.user)
      .subscribe(
        data => {
          if (data.status) {
            if (this.avatarTemp) {
              data.avatar = this.avatarTemp;
            }

            swal({
              title: "Cập nhật thành công!",
              icon: "success",
            });
            this.isSubmitting = false
          } else {
            this.isSubmitting = false;
            swal({
              title: "Có lỗi. Email trùng",
              icon: "error",
            });
          }
          // this.router.navigateByUrl('/profile/' + updatedUser.username)
        },
        err => {
          this.errors = err;
          this.isSubmitting = false;
        }
      );
  }

  updateUser(values: Object) {
    Object.assign(this.user, values);
  }

  changeAvatar(e) {
    this.avatarTemp = e.base64;
    this.settingsForm.controls['avatar'].setValue(e.raw);
  }

}
