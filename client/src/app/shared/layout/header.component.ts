import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';

import { User, UserService, JwtService } from '../../core';

@Component({
  selector: 'app-layout-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  constructor(
    private jwtService: JwtService,
    private userService: UserService
  ) { }
  currentUser: User;

  ngOnInit() {
    this.userService.currentUser.subscribe(
      (userData) => {
        this.currentUser = userData;
        if (typeof this.currentUser.username == 'undefined') {
          const token = this.jwtService.getToken();
          if (token){
            this.userService.getUserByToken(token).subscribe(
              data => {
                this.currentUser = this.userService.getCurrentUser();
              }
            )
          }
          
        }
      }
    );
  }
}
