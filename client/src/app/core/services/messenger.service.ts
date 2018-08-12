import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { UserService } from '.';
import { SharedService } from './shared.service';
import { Profile, User } from '../models';
import { Message } from '../models/message.model'

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

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
    // this.stompClient.connect({}, function (frame) {
    // });
  }

  subscribeMessenger(user: User) {
    this.currentUser = user;
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe(`/user/${user.id}/queue/messenger`, (res) => {
        if (res.body) {
          console.log('----------------------------------');
          console.log(res.body);
          console.log('----------------------------------');
          let message = JSON.parse(res.body);
          that.sharedService.pushMessage(message);
        }
      });
    });
  }

  sendMessage(_recipient_id: number, content: string) {
    let message: Message = {
      recipientId: _recipient_id,
      content: content,
      sender: this.currentUser
    }
    this.sharedService.pushMessage(message);
    // this.stompClient.send("/chat", {}, JSON.stringify(body));
    this.stompClient.send("/api/messenger_send", {}, JSON.stringify(message));
  }


  unSubscribeMessenger(){
    this.stompClient.disconnect();
    // this.sharedService.pushNotifications({} as Notification[]);
  }

  markRead(noti: Notification){
    // return this.apiService.put('/notification/markread/', noti).toPromise().then(
    //   data => {
    //     console.log(data)
    //   }
    // );
  }
}
