import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  httpRequestFactory(method: string, url: string, body: any): Observable<any> {
    switch(method) {
      case 'get':
      case 'delete':
        return this.http[method](url, { observe: 'response' });
      case 'post':
      case 'put':
      case 'patch':
        return this.http[method](url, body, { observe: 'response' });
    }
  }

  send(method: string, url: string, body: any): Observable<any> {
    return this.httpRequestFactory(method, url, body).pipe(
      map(res => ({ data: res.body, status: res.status })),
      catchError(err => of({ data: err.error, status: err.status }))
    );
  }

}
