import { UserService } from './../core/services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { ArticleListConfig, Profile, User } from '../core';

@Component({
  selector: 'app-profile-articles',
  templateUrl: './profile-articles.component.html'
})
export class ProfileArticlesComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) { 
    // Reload when switching user profile
    // override the route reuse strategy
    this.router.routeReuseStrategy.shouldReuseRoute = function(){
      return false;
   }

   this.router.events.subscribe((evt) => {
      if (evt instanceof NavigationEnd) {
         // trick the Router into believing it's last link wasn't previously loaded
         this.router.navigated = false;
         // if you need to scroll back to top, here is the right place
         window.scrollTo(0, 0);
      }
  });
  }

  user: User;
  articlesConfig: ArticleListConfig = {
    type: 'all',
    filters: {}
  };

  ngOnInit() {
    this.user = this.userService.getCurrentUser();
    this.articlesConfig = {
      type: 'all',
      filters: {}
    }; // Only method I found to refresh article load on swap
    console.log(this.route.params)
    this.articlesConfig.filters.author = this.route.snapshot.params['username']
  }

}
