import { ApiService } from './api.service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Notification } from '../models/notification.model'

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  private notificationSubject = new BehaviorSubject<Notification[]>({} as Notification[]);
  public notifications = this.notificationSubject.asObservable();

  private unreadNotificationsSubject = new BehaviorSubject<Notification[]>({} as Notification[]);
  public unreadNotifications = this.unreadNotificationsSubject.asObservable();

  constructor(
  ) {
  }

  pushNotifications(_notifications: Notification[]) {
    this.unreadNotificationsSubject.next(_notifications.filter(noti => !noti.read))
    this.notificationSubject.next(_notifications);
  }

  pushNotification(_notification: Notification){
    this.unreadNotificationsSubject.value.unshift(_notification);
    this.notificationSubject.value.unshift(_notification);
  }

  getNotifications(): Observable<Notification[]> {
    return this.notifications;
  }
}
