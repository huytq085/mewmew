import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment';
import { Headers, Http, Response, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(
    private http: Http
  ) { }

  private setHeaders(): Headers {
    let headersConfig = {
      'Content-Type': 'application/json',
      'Accept': 'application/json'
    };

    return new Headers(headersConfig);
  }

  private handleError(error: any) {
    return throwError(error);
  }

  post(path: string, body: Object = {}): Observable<any> {
    return this.http.post(`${environment.api_url}${path}`, JSON.stringify(body), { headers: this.setHeaders() })
      .pipe(
        catchError(this.handleError),
        map((res: Response) => res.json())
      )
  }

}