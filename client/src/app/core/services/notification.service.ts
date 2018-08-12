import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { UserService } from '.';
import { SharedService } from './shared.service';
import { Profile, User } from '../models';
import { Notification } from '../models/notification.model'

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;
  private currentUser: User;

  constructor(
    private sharedService: SharedService,
    private apiService: ApiService
  ) {
    this.initializeWebSocketConnection();
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      // that.stompClient.subscribe("/user/naila/queue/notify", (res) => {
      //     if (res.body) {
      //         let body = JSON.parse(res.body);
      //         console.log(body)
      //     }
      // });
    });
  }

  subscribeNotify(user: User) {
    this.currentUser = user;
    console.log(1);
    let that = this;
    that.apiService.get('/notification/' + user.id).subscribe(
      data => {
        that.sharedService.pushNotifications(data);
      }
    )
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/user/${user.id}/queue/notify`, (res) => {
        if (res.body) {
          console.log(res.body);
          let notification = JSON.parse(res.body);
          that.sharedService.pushNotification(notification);
        }
      });
    });
  }

  notifyFriendRequest(_recipient_id: number) {
    // TODO: Remove this live
    // this.currentUser.avatar = 'http://localhost:8080/assets/img/default_avatar.png';
    let notification: Notification = {
      recipientId: _recipient_id,
      content: '',
      type: "FR_REQ",
      sender: this.currentUser
    }
    // this.stompClient.send("/chat", {}, JSON.stringify(body));
    this.stompClient.send("/api/friend_request", {}, JSON.stringify(notification));
  }

  

  notifyFriendAccept(_recipient_id: number) {
    // TODO: Remove this live
    // this.currentUser.avatar = 'http://localhost:8080/assets/img/default_avatar.png';
    let notification: Notification = {
      recipientId: _recipient_id,
      content: '',
      type: "FR_ACT",
      sender: this.currentUser
    }
    // this.stompClient.send("/chat", {}, JSON.stringify(body));
    this.stompClient.send("/api/friend_accept", {}, JSON.stringify(notification));
  }
  

  unSubscribeNotify(){
    this.stompClient.disconnect();
    console.log('unsub');
    
    this.sharedService.pushNotifications({} as Notification[]);
  }

  markRead(noti: Notification){
    return this.apiService.put('/notification/markread/', noti).toPromise().then(
      data => {
        console.log(data)
      }
    );
  }

}
