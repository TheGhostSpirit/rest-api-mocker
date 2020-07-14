import { Component, OnInit } from '@angular/core';
import { Model } from 'model-share/types/model';

import { ModelsService } from '../../core/services/models/models.service';

@Component({
  selector: 'app-model-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.less']
})
export class ModelListComponent implements OnInit {

  displayedColumns = [
    'name',
    'description',
    'version',
    'license',
    'actions'
  ];

  models: Model[];

  constructor(private service: ModelsService) {}

  ngOnInit() {
    this.service.findAll().subscribe(res => this.models = res);
  }

}
