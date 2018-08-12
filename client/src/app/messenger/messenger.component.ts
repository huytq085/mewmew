import { ActivatedRoute, Router } from '@angular/router';
import { SharedService } from './../core/services/shared.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { MessengerService } from '../core/services/messenger.service';
import { Message } from '../core/models/message.model';
import { UserService, User, Profile, ProfilesService } from '../core';
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
  activatedProfile: Profile;

  profiles: Profile[] = new Array();

  constructor(
    private messenger: MessengerService,
    private sharedService: SharedService,
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService,
    private profilesService: ProfilesService
  ) { 
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
  }

  ngOnInit() {
    this.currentProfileId = this.route.snapshot.params['id'];
    this.sharedService.messages.subscribe(
      data => {
        if (Array.isArray(data)) {
          this.messages = data;
        }
      }
    )
    console.log(this.currentProfileId);
    this.userService.currentUser.subscribe(
      data => {
        console.log(this.currentProfileId);
        this.currentUser = data;
        if (data.id) {
          this.messenger.getListConversation(data).subscribe(
            data => {
              for (const key in data) {
                if (data.hasOwnProperty(key)) {
                  const element = data[key];
                  this.profiles.push(this.profilesService.user2Profile(element))
                }
              }
            }
          )
        }
        // FIXME: messenger could not automatically run constructor
        this.messenger.initializeWebSocketConnection();
        this.messenger.subscribeMessenger(this.currentUser, this.currentProfileId);
      }
    )

  }
  ngOnDestroy() {
    this.messenger.unSubscribeMessenger();
  }

  sendMessage(content: string) {
    this.messenger.sendMessage(this.currentProfileId, content);
    $('#input').val('');
  }

  reload(){

    this.messenger.getMessages(this.currentUser.id, this.currentProfileId);
  }

  setActivatedProfile(_profile: Profile){
    this.activatedProfile = _profile;
  }
}
