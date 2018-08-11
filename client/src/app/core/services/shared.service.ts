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
    private apiService: ApiService
  ) {
    this.notificationSubject.next([
      {
        id: 1,
        username: "huyhuy",
        content: "Tran Tan muon ket ban kia",
        dateAdded: null,
        type: "FR_REQ",
        sender: null
      },
      {
        id: 2,
        username: "toantoan",
        content: "Toan muon ket ban kia",
        dateAdded: null,
        type: "FR_REQ",
        sender: null

      },
    ])
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
