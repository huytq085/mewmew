import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User, UserService, Profile, ProfilesService } from '../core';
import { concatMap, tap } from 'rxjs/operators';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  profile: Profile;
  user: User;
  isUser: boolean;
  username: string;
  description: string;
  currentUser: User;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private profileService: ProfilesService
  ) { }


  ngOnInit() {
    this.route.url.subscribe(
      data => {
        this.username = data[data.length - 1].path;
      }
    )
    this.currentUser = this.userService.getCurrentUser();
    this.userService.getUser(this.username).subscribe(
      (data: User) => {
        console.log('getUser() in profile cpn')
        this.user = data;
        this.description = data.description;
        this.isUser = (this.currentUser.username === this.username);
        console.log('isUser: ' + this.isUser)
        this.profile = this.user2Profile(this.user);
        // Check user is following
        this.profileService.isFollowing(this.profile.id)
          .subscribe(
            res => {
              this.profile.following = res;
            }
          )
        // Set following to false for testing
        console.log(this.profile);
      }
    )
  }

  onToggleFollowing(following: boolean) {
    console.log('follow cpn emit to onToggle in profile cpn')
    this.profile.following = following;
  }

  user2Profile(user: User): Profile{
    let profile: Profile = {} as Profile;
    if (typeof user != 'undefined'){
      profile.username = user.username;
      profile.bio = user.description;
      profile.image = user.image;
      profile.id = user.id;
    }
    return profile;
  }

}
