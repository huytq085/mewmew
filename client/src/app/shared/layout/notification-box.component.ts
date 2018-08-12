import { NotificationService } from './../../core/services/notification.service';
import { Component, OnInit } from '@angular/core';
import { Notification, SharedService, ProfilesService } from '../../core';
import { filter, map, concatMap, toArray } from 'rxjs/operators';
import { pipe, Observable } from 'rxjs';

@Component({
  selector: 'notification-box',
  templateUrl: './notification-box.component.html',
  styleUrls: ['./notification-box.component.css']
})
export class NotificationBoxComponent implements OnInit {

  isAccept = false;
  unreadNotifications: Notification[] = new Array();

  notifications: Notification[] = new Array();

  constructor(
    private sharedService: SharedService,
    private profilesService: ProfilesService,
    private notify: NotificationService
  ) { }

  ngOnInit() {


    // this.notifications.unshift({} as Notification);
    this.sharedService.notifications.subscribe(
      (data: Notification[]) => {
        if (Array.isArray(data)) {
          this.notifications = data;
        }
      }
    )

    this.sharedService.unreadNotifications.subscribe(
      (data: Notification[]) => {
        if (Array.isArray(data)) {
          this.unreadNotifications = data;
        }
      }
    )
  }

  accept(userId: number) {
    console.log(userId)
    this.profilesService.acceptFriend(userId).subscribe(
      data => {
        // console.log(data == 1);
        // this.isAccept = (data == 1);
        this.isAccept = true;
      }
    )
  }
  read(noti: Notification) {
    let index = this.unreadNotifications.indexOf(noti);
    if (index != -1) {
      noti.read = true;
      this.unreadNotifications.splice(index, 1);
    }

    this.notify.markRead(noti);
  }

}
