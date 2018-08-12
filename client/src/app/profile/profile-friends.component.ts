import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ProfilesService, Profile } from '../core';

@Component({
  selector: 'app-profile-friends',
  templateUrl: './profile-friends.component.html',
  styleUrls: ['./profile-friends.component.css']
})
export class ProfileFriendsComponent implements OnInit {

  profiles: Profile[] = new Array();

  constructor(
    private route: ActivatedRoute,
    private profilesService: ProfilesService
  ) { }

  ngOnInit() {
    this.profilesService.getFriends(this.route.parent.snapshot.params['username']).subscribe(
      data => {
        this.profiles = data;
      }
    )
  }

}
