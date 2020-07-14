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
  deployed = null;

  constructor(private service: ModelsService) {}

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.service.findAll().subscribe(res => this.models = res);
  }

  deploy(model: Model) {
    this.deployed = model;
    return this.service.deploy(model._id).subscribe();
  }

  edit(model: Model) {

  }

  delete(model: Model) {
    return this.service.delete(model._id)
      .subscribe(() => this.refresh());
  }

}
