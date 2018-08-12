import { ActivatedRoute } from '@angular/router';
import { SharedService } from './../core/services/shared.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MessengerService } from '../core/services/messenger.service';
import { Message } from '../core/models/message.model';
import { UserService, User } from '../core';
import $ from 'jquery';

@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit, OnDestroy {

  messages: Message[] = new Array();
  currentProfileId: number;
  currentUser: User;

  constructor(
    private messenger: MessengerService,
    private sharedService: SharedService,
    private route: ActivatedRoute,
    private userService: UserService,
  ) { }

  ngOnInit() {
    this.currentProfileId = this.route.snapshot.params['id'];
    this.sharedService.messages.subscribe(
      data => {
        if (Array.isArray(data)){
          this.messages = data;
        }
      }
    )
    console.log(this.currentProfileId);
    this.userService.currentUser.subscribe(
      data => {
        console.log(this.currentProfileId);
        this.currentUser = data;
        this.messenger.subscribeMessenger(this.currentUser, this.currentProfileId);
      }
    )
  }
  ngOnDestroy(){
    this.messenger.unSubscribeMessenger();
  }

  sendMessage(content: string){
    this.messenger.sendMessage(this.currentProfileId, content);
    $('#input').val('');    
  }
}
