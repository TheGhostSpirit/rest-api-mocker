import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }

  send(method: string, url: string, body?: string): Observable<any> {
    return this.http[method](url, body).pipe(
      catchError(err => of(err.error))
    );
  }

}
