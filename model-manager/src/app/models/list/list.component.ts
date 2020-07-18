import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { Model } from 'model-share/types/model';
import { filter, switchMap } from 'rxjs/operators';

import { ModelsService } from '../../core/services/models/models.service';
import { ModelImportComponent } from '../import/import.component';

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

  constructor(
    private service: ModelsService,
    private dialog: MatDialog,
    private router: Router
  ) { }

  ngOnInit() {
    this.refresh();
  }

  refresh() {
    this.service.findAll().subscribe(res => this.models = res);
  }

  deploy(model: Model) {
    this.deployed = model;
    return this.service.deploy(model._id)
      .subscribe(() => this.router.navigateByUrl(`/models/${model._id}/client`));
  }

  edit(model: Model) {

  }

  import() {
    this.dialog.open(ModelImportComponent)
      .afterClosed()
      .pipe(
        filter(f => !!f),
        switchMap(f => this.service.import(f)),
        switchMap(() => this.service.findAll())
      )
      .subscribe(res => this.models = res);
  }

  delete(model: Model) {
    return this.service.delete(model._id)
      .subscribe(() => this.refresh());
  }

}
