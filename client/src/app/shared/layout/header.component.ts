import { Component, OnInit } from '@angular/core';

import { User, UserService, JwtService } from '../../core';

@Component({
  selector: 'app-layout-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  constructor(
    private jwtService: JwtService,
    private userService: UserService
  ) {}

  currentUser: User;

  ngOnInit() {
    const token = this.jwtService.getToken();
    if (token){
      this.userService.getUserByToken(token).subscribe(
        data => {
          this.currentUser = this.userService.getCurrentUser();          
        }
      )
    }
    // this.userService.currentUser.subscribe(
    //   (userData) => {
    //     console.log('header')
    //     console.log(userData)
    //     this.currentUser = userData;
    //   }
    // );
  }
}
