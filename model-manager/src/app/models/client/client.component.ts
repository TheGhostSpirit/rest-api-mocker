import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Model } from 'model-share/types/model';
import format from 'json-format';

import { environment } from 'src/environments/environment';

import { ClientService } from '../../core/services/client/client.service';
import { ModelsService } from '../../core/services/models/models.service';

@Component({
  selector: 'app-model-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.less']
})
export class ModelClientComponent implements OnInit {

  model: Model;
  mockerUrl = environment.mocker.url;
  apiData = '';
  apiStatus = '';
  hasBody = false;

  requestForm = new FormGroup({
    method: new FormControl('get', Validators.required),
    url: new FormControl('', [Validators.required,
    (f: FormControl) => {
      return f.value.toString().startsWith('/')
        ? null
        : { url: false };
    }]),
    body: new FormControl('', (f: FormControl) => {
      if (!this.hasBody) {
        return null;
      }
      try {
        JSON.parse(f.value.toString());
        return null;
      } catch {
        return { json: false };
      }
    })
  });

  constructor(
    private route: ActivatedRoute,
    private service: ModelsService,
    private client: ClientService
  ) { }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');

    this.service.findOne(id).subscribe(model => this.model = model);

    this.requestForm.controls['method'].valueChanges
      .subscribe(v => {
        this.hasBody = ['post', 'put', 'patch'].some(e => e === v);
        this.requestForm.controls['body'].updateValueAndValidity();
      });
  }

  sendRequest() {
    if (this.requestForm.valid) {
      this.client.send(
        this.requestForm.controls['method'].value,
        this.mockerUrl + this.requestForm.controls['url'].value,
        this.hasBody ? JSON.parse(this.requestForm.controls['body'].value) : undefined
      ).subscribe(({ data, status }) => {
        this.apiData = format(data, { type: 'space', size: 2 });
        this.apiStatus = status;
      });
    }
  }

  setRequest(route: Model['api']['routes'][0]) {
    this.requestForm.controls['method'].setValue(route.method);
    this.requestForm.controls['url'].setValue(route.path);
  }

}
