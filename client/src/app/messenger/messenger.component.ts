import { ActivatedRoute } from '@angular/router';
import { SharedService } from './../core/services/shared.service';
import { Component, OnInit } from '@angular/core';
import { MessengerService } from '../core/services/messenger.service';
import { Message } from '../core/models/message.model';
import { UserService, User } from '../core';
import $ from 'jquery';

@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit {

  messages: Message[] = new Array();
  recipientId: number;
  currentUser: User;

  constructor(
    private messenger: MessengerService,
    private sharedService: SharedService,
    private route: ActivatedRoute,
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.recipientId = this.route.snapshot.params['id'];
    this.sharedService.messages.subscribe(
      data => {
        if (Array.isArray(data)){
          this.messages = data;
        }
      }
    )
    this.userService.currentUser.subscribe(
      data => {
        this.currentUser = data;
      }
    )
  }

  sendMessage(content: string){
    this.messenger.sendMessage(this.recipientId, content);
    $('#input').val('');    
  }
}
