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

  constructor(
  ) {
  }

  pushNotifications(_notifications: Notification[]) {
    this.notificationSubject.next(_notifications);
  }

  pushNotification(_notification: Notification){
    console.log(_notification)
    this.notificationSubject.value.unshift(_notification);
  }

  getNotifications(): Observable<Notification[]> {
    return this.notifications;
  }
}
