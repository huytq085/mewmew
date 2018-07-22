import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User, UserService, Profile } from '../core';
import { concatMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: Profile;
  currentUser: User;
  isUser: boolean;
  username: string;
  description: string;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService
  ) { }


  ngOnInit() {
    this.route.url.subscribe(
      data => {
        this.username = data[data.length - 1].path;
      }
    )
    this.userService.getUser(this.username).subscribe(
      (data: User) => {
        console.log('getUser() in profile cpn')
        this.description = data.description;
      }
    )
    this.userService.currentUser.pipe(tap(
      (userData: User) => {
        console.log('Get current user from profile cpn')
        this.currentUser = userData;
        console.log(this.currentUser)
        this.isUser = (this.currentUser.username === this.username);
        console.log(this.isUser)
      }
    ));
  }

  onToggleFollowing(following: boolean) {
    this.profile.following = following;
  }

}
