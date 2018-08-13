import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { ArticleListConfig, UserService } from '../core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(
    private router: Router,
    private userService: UserService
  ) {
    this.testDiff();
  }

  isAuthenticated: boolean;
  listConfig: ArticleListConfig = {
    type: 'all',
    filters: {}
  };
  ngOnInit() {
    this.testDiff();
    this.userService.isAuthenticated.subscribe(
      (authenticated) => {
        this.isAuthenticated = authenticated;
        // if (authenticated) {
        //   this.setListTo('feed');
        // } else {
        //   this.setListTo('all');
        // }

        this.setListTo('all');
      }
    );
  }

  setListTo(type: string = '', filters: Object = {}) {
    // Nếu chưa đăng nhập thì chuyển về trang đăng nhập
    if (type === 'feed' && !this.isAuthenticated) {
      this.router.navigateByUrl('/login');
      return;
    }

    this.listConfig = {type: type, filters: filters};
  }
  testDiff(){
  }
}
