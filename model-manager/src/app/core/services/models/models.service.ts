import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Model } from 'model-share/types/model';

@Injectable()
export class ModelsService {

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Model[]>('/models');
  }

  findOne(id: string) {
    return this.http.get<Model>(`/models/${id}`);
  }

  delete(id: string) {
    return this.http.delete<{}>(`/models/${id}`);
  }

  deploy(id: string) {
    return this.http.post<{ status: boolean }>(
      `/models/${id}/deploy`,
      JSON.stringify({})
    );
  }

}
