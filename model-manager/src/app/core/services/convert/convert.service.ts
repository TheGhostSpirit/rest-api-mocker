import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class ConvertService {

  constructor(private http: HttpClient) { }

  jsonToYaml(obj: any) {
    return this.http.post<string>('/convert/yaml', obj);
  }

  yamlToJson(obj: string): Observable<any> {
    return this.http.post<any>('/convert/json', obj, {
      headers: {
        'Content-Type': 'text/yaml'
      }
    });
  }

}
