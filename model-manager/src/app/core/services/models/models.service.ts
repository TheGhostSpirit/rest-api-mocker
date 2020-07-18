import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Model } from 'model-share/types/model';
import { from } from 'rxjs';
import { switchMap } from 'rxjs/operators';

@Injectable()
export class ModelsService {

  constructor(private http: HttpClient) { }

  findAll() {
    return this.http.get<Model[]>('/models');
  }

  findOne(id: string) {
    return this.http.get<Model>(`/models/${id}`);
  }

  import(file: File) {
    return from(file.text())
      .pipe(
        switchMap(text =>
          this.http.post<Object>('/convert/json', text,
            { headers: { 'Content-Type': 'text/yaml' } }
          )
        ),
        switchMap(
          json => this.http.post('/models', json)
        )
      );
  }

  update(id: string, model: Model) {
    return this.http.put(`/models/${id}`, model);
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
