import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs';
import { BehaviorSubject } from 'rxjs';
import { ReplaySubject } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { ApiService } from './api.service';
import { User } from '../models';
import { distinctUntilChanged } from 'rxjs/operators';
import { JwtService } from './jwt.service';


@Injectable()
export class UserService {
  private currentUserSubject = new BehaviorSubject<User>({} as User);
  public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

  private isAuthenticatedSubject = new BehaviorSubject<boolean>(false);
  public isAuthenticated = this.isAuthenticatedSubject.asObservable();

  constructor(
    private apiService: ApiService,
    private http: Http,
    private jwtService: JwtService
  ) { }

  setAuth(user: User, token: string) {
    this.jwtService.saveToken(token);
    // Set current user data into observable
    this.currentUserSubject.next(user);
    // Set isAuthenticated to true
    this.isAuthenticatedSubject.next(true);
  }

  attemptAuth(type, credentials): Observable<User> {
    let route = (type === 'login') ? '/login' : '';
    return this.apiService.post(route, credentials)
      .pipe(
        map(
          res => {
            console.log(res);
            if (res && res.token) {
              let user: User = res.user;
              // store username and jwt token in local storage to keep user logged in between page refreshes
              // localStorage.setItem('currentUser', JSON.stringify({ user, token: res.token }));
              this.setAuth(res.user, res.token);
            }
            return res;
          }
        )
      );
  }

  getCurrentUser(): User {
    return this.currentUserSubject.value;
  }

  

}