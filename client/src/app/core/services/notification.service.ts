import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { UserService } from '.';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;

  constructor() {
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

  notifyFriendRequest() {
    // Set avatar for testing
    let body = "huyhuy";
    // this.stompClient.send("/chat", {}, JSON.stringify(body));
    this.stompClient.send("/api/friend_request", {}, body);


  }
  subscribeNotify(username: string) {
    console.log(1);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/user/${username}/queue/notify`, (res) => {
        if (res.body) {
          // let body = JSON.parse(res.body);
          console.log(res)
        }
      });
    });
  }

}
