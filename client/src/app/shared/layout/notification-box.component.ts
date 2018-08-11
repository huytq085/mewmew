import { Component, OnInit } from '@angular/core';
import { Notification, SharedService } from '../../core';

@Component({
  selector: 'notification-box',
  templateUrl: './notification-box.component.html',
  styleUrls: ['./notification-box.component.css']
})
export class NotificationBoxComponent implements OnInit {

  notifications: Notification[] = new Array();

  constructor(
    private sharedService: SharedService
  ) { }

  ngOnInit() {
    this.sharedService.getNotifications().subscribe(
      data => {
        console.log(data);
        this.notifications = data;
      }
    )
  }

  accept(){
    let notis = {
      id: 1,
      userId: 1,
      content: "Nhi muon ket ban kia",
      dateAdded: null,
      type: "FR_REQ"
    }
    this.sharedService.pushNotification(notis);
  }

}
