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
    this.userService.getUser(this.username, this.currentUser.id).subscribe(
      (data: User) => {
        this.user = data;
        this.description = data.description;
        this.isUser = (this.currentUser.username === this.username);
        this.profile = this.profileService.user2Profile(this.user);
      }
    )
  }

  onToggleFollowing(following: boolean) {
    this.profile.following = following;
  }

  onToggleAdding(friendStatus: number) {
    this.profile.friendStatus = friendStatus;
  }

}
