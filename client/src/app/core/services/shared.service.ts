import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Notification } from '../models/notification.model'
import { Message } from '../models/message.model';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private notificationSubject = new BehaviorSubject<Notification[]>({} as Notification[]);
  public notifications = this.notificationSubject.asObservable();

  private unreadNotificationsSubject = new BehaviorSubject<Notification[]>({} as Notification[]);
  public unreadNotifications = this.unreadNotificationsSubject.asObservable();

  private messagesSubject = new BehaviorSubject<Message[]>(new Array());
  public messages = this.messagesSubject.asObservable();

  constructor(
  ) {
  }

  pushNotifications(_notifications: Notification[]) {
    if (Array.isArray(_notifications)){
      this.unreadNotificationsSubject.next(_notifications.filter(noti => !noti.read))
    } else {
      this.unreadNotificationsSubject.next(_notifications)
    }
    this.notificationSubject.next(_notifications);
  }

  pushNotification(_notification: Notification){
    this.unreadNotificationsSubject.value.unshift(_notification);
    this.notificationSubject.value.unshift(_notification);
  }

  getNotifications(): Observable<Notification[]> {
    return this.notifications;
  }

  pushMessages(_messages: Message[]) {
    this.messagesSubject.next(_messages);
  }

  pushMessage(_message: Message){
    this.messagesSubject.value.push(_message);
  }

  getMessages(): Observable<Message[]> {
    return this.messages;
  }


}
