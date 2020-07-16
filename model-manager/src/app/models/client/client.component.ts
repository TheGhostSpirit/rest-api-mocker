import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import format from 'json-format';

import { environment } from 'src/environments/environment';

import { ClientService } from '../../core/services/client/client.service';

@Component({
  selector: 'app-model-client',
  templateUrl: './client.component.html',
  styleUrls: ['./client.component.less']
})
export class ModelClientComponent implements OnInit {

  mockerUrl = environment.mocker.url;
  apiData = '';
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

  constructor(private client: ClientService) { }

  ngOnInit() {
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
      ).subscribe(res => this.apiData = format(res, { type: 'space', size: 2 }));
    }
  }

}
