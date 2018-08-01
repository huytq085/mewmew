import { Component, OnInit } from '@angular/core';
import { UserService, User, Profile, ProfilesService } from '../core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  profiles: Profile[] = new Array();
  query: string;

  constructor(
    private userService: UserService,
    private profileService: ProfilesService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.query = params['q'];
      this.userService.search(this.query).subscribe(
        data => {
          console.log(this.profiles)
          for (const key in data) {
            if (data.hasOwnProperty(key)) {
              const element = data[key];
              this.profiles.push(this.profileService.user2Profile(element))
            }
          }
        }
      )
    });

  }

}
