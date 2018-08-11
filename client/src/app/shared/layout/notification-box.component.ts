import { Component, OnInit } from '@angular/core';
import { Notification, SharedService, ProfilesService } from '../../core';

@Component({
  selector: 'notification-box',
  templateUrl: './notification-box.component.html',
  styleUrls: ['./notification-box.component.css']
})
export class NotificationBoxComponent implements OnInit {

  isAccept = false;

  notifications: Notification[] = new Array();

  constructor(
    private sharedService: SharedService,
    private profilesService: ProfilesService
  ) { }

  ngOnInit() {
    this.sharedService.getNotifications().subscribe(
      data => {
        console.log(data);
        this.notifications = data;
      }
    )
  }

  accept(userId: number){
    console.log(userId)
    this.profilesService.acceptFriend(userId).subscribe(
      data => {
        // console.log(data == 1);
        // this.isAccept = (data == 1);
        this.isAccept = true;
      }
    )
  }

}
