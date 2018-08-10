import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Profile, ProfilesService, NotificationService, UserService } from '../../core';
import { concatMap ,  tap } from 'rxjs/operators';
import { of } from 'rxjs';

@Component({
  selector: 'app-addfriend-button',
  templateUrl: './addfriend-button.component.html',
  styleUrls: ['./addfriend-button.component.css']
})
export class AddfriendButtonComponent implements OnInit {
  ngOnInit(): void {
    console.log(this.profile);
    
  }

  constructor(
    private profilesService: ProfilesService,
    private router: Router,
    private userService: UserService,
    private notify: NotificationService
  ) { }

  @Input() profile: Profile;
  @Output() toggle = new EventEmitter<any>();
  isSubmitting = false;

  toggleAdding() {
    this.isSubmitting = true;
    // TODO: remove nested subscribes, use mergeMap

    this.userService.isAuthenticated.pipe(concatMap(
      (authenticated) => {
        // Not authenticated? Push to login screen
        if (!authenticated) {
          this.router.navigateByUrl('/login');
          return of(null);
        }

        if (this.profile.friendStatus == null) {
          return this.profilesService.addFriend(this.profile.id)
            .pipe(tap(
              data => {
                this.isSubmitting = false;
                this.toggle.emit(0);
                this.notify.notifyFriendRequest();
              },
              err => this.isSubmitting = false
            ));

          // Otherwise, unfollow this profile
        } else {
          return this.profilesService.unFriend(this.profile.id)
            .pipe(tap(
              data => {
                this.isSubmitting = false;
                this.toggle.emit(null);
              },
              err => this.isSubmitting = false
            ));
        }
      }
    )).subscribe();
  }

}
