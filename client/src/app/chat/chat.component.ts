import { Component, OnInit, OnDestroy } from '@angular/core';
import * as Stomp from '@stomp/stompjs';
import * as SockJS from 'sockjs-client';
import $ from 'jquery';
import { UserService } from '../core';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit, OnDestroy {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;

  constructor(private userService: UserService) {

  }
  ngOnInit() {
    this.initializeWebSocketConnection();
  }
  ngOnDestroy(): void {
    this.stompClient.disconnect();
  }

  initializeWebSocketConnection() {
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function (frame) {
      that.stompClient.subscribe("/topic/chat", (res) => {
        if (res.body) {
          let body = JSON.parse(res.body);
          console.log(body)
          $(".chat").append(`
              <li class="left clearfix">
              <span class="chat-img pull-left">
                <img src="${body.user.avatar}" alt="User Avatar" class="img-circle chat-avatar" />
              </span>
              <div class="chat-body clearfix">
                <div class="header">
                  <strong class="primary-font">${body.user.username}</strong>
                  <small class="pull-right text-muted">
                    <span class="glyphicon glyphicon-time"></span>12 phút trước</small>
                </div>
                <p>
                  ${body.message}
                </p>
              </div>
            </li>
          `)
        }
      });
    });
  }

  sendMessage(message) {
    let user = this.userService.getCurrentUser()
    // Set avatar for testing
    user.avatar = 'http://localhost:8080/assets/img/default_avatar.png';
    let body = {
      message,
      user
    }
    // this.stompClient.send("/chat", {}, JSON.stringify(body));
    this.stompClient.send("/api/chat", {}, JSON.stringify(body));
    $('#input').val('');
  }



}
