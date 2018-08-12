import { ProfilesService } from './profiles.service';
import { HttpClientModule, HttpClient, HttpParams } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { ReplaySubject } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiService } from './api.service';
import { User } from '../models';
import { distinctUntilChanged } from 'rxjs/operators';
import { JwtService } from './jwt.service';
import { NotificationService } from './notification.service';


@Injectable()
export class UserService implements OnInit {

  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  private USER_URL = '/users/';
  private ID_USER_URL = '/users/id/';

  constructor(
    private apiService: ApiService,
    private jwtService: JwtService,
    private notify: NotificationService
  ) { }

  ngOnInit(): void {
  }

  setAuth(user: User, token: string) {
    this.jwtService.saveToken(token);
    // Set current user data into observable
    this.currentUserSubject.next(user);
    // Set isAuthenticated to true
    this.isAuthenticatedSubject.next(true);
    this.notify.subscribeNotify(user);
    
  }

  purgeAuth() {
    // Remove JWT from localstorage
    this.jwtService.destroyToken();
    // Set current user to an empty object
    this.currentUserSubject.next({} as User);
    // Set auth status to false
    this.isAuthenticatedSubject.next(false);
    this.notify.unSubscribeNotify();
  }

  // Update the user on the server (email, pass, etc)
  update(user): Observable<User> {
    return this.apiService
      .put('/users', user)
      .pipe(map(data => {
        // Update the currentUser observable
        console.log(data)
        this.currentUserSubject.next(data);
        return data;
      }));
  }

  attemptAuth(type, credentials): Observable<User> {
    let route = (type === 'login') ? '/login' : '/register';
    return this.apiService.post(route, credentials)
      .pipe(
        map(
          res => {
            if (res && res.token) {
              let user: User = res.user;
              // store username and jwt token in local storage to keep user logged in between page refreshes
              // localStorage.setItem('currentUser', JSON.stringify({ user, token: res.token }));
              this.setAuth(res.user, res.token);
            }
            return res.user;
          }
        )
      );
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  getUser(username: string, isFollowedBy?: number): Observable<User> {
    return this.apiService.get(this.USER_URL + username, new HttpParams({
      fromObject: {
        isFollowedBy: String(isFollowedBy)
      }
    }));
  }

  getUserById(id: number, isFollowedBy?: number): Observable<User> {
    return this.apiService.get(this.ID_USER_URL + id, new HttpParams({
      fromObject: {
        isFollowedBy: String(isFollowedBy)
      }
    }));
  }

  getUserByToken(token: String): Observable<User> {
    const url = '/auth';
    return this.apiService.post(url, { token })
      .pipe(
        map(
          res => {
            if (res && res.token) {
              let user: User = res.user;
              // store username and jwt token in local storage to keep user logged in between page refreshes
              // localStorage.setItem('currentUser', JSON.stringify({ user, token: res.token }));
              this.setAuth(res.user, res.token);
            }
            return res;
          }
        )
      )
  }

  search(q: string): Observable<User[]> {
    return this.apiService.get('/users', new HttpParams({
      fromObject: {
        q
      }
    }))
  }
}