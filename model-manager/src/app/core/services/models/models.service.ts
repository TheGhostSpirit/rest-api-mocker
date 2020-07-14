import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Model } from 'model-share/types/model';

@Injectable()
export class ModelsService {

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Model[]>('/models');
  }

}
