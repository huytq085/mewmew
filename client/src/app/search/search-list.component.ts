import { Component, OnInit, Input } from '@angular/core';
import { User, Profile } from '../core';

@Component({
  selector: 'app-search-list',
  templateUrl: './search-list.component.html',
  styleUrls: ['./search-list.component.css']
})
export class SearchListComponent implements OnInit {

  @Input() profile: Profile;

  constructor() { }

  ngOnInit() {
  }

  onToggleFollowing(following: boolean) {
    // this.profile.following = following;
  }

}
