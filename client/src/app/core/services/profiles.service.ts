import { UserService } from './user.service';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

import { ApiService } from './api.service';
import { Profile, User } from '../models';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProfilesService implements OnInit{
  
  currentUser: User;

  constructor (
    private apiService: ApiService,
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.currentUser = this.userService.getCurrentUser();
  }

  get(username: string): Observable<Profile> {
    return this.apiService.get('/users/' + username)
      .pipe(map((data: {profile: Profile}) => data.profile));
  }

  follow(userId: number): Observable<Profile> {
    console.log('current user');
    console.log(this.userService.currentUser);
    return this.apiService.post('/users/' + userId + '/follow', this.userService.getCurrentUser());
  }

  unfollow(userId: number): Observable<Profile> {
    return this.apiService.post('/users/' + userId + '/unfollow', this.userService.getCurrentUser());
  }

  // isFollowing(userId: number): Observable<boolean>{
  //   let result;
  //   return this.apiService.get('/users/' + userId + '/isfollowing/' + this.userService.getCurrentUser().id);
  // }

  user2Profile(user: User): Profile{
    let profile: Profile = {} as Profile;
    if (typeof user != 'undefined'){
      profile.username = user.username;
      profile.description = user.description;
      profile.avatar = user.avatar;
      profile.id = user.id;
      profile.following = user.following;
      profile.fullName = user.fullName;
    }
    return profile;
  }

}
