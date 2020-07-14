import { Component, OnInit } from '@angular/core';

import { ModelsService } from '../../core/services/models/models.service';

@Component({
  selector: 'app-model-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.less']
})
export class ModelListComponent implements OnInit {

  displayedColumns = [
    'position',
    'name',
    'actions'
  ];

  dataSource = [
    { name: 'toto' },
    { name: 'titi' },
    { name: 'tata' }
  ];

  constructor(private service: ModelsService) {}

  ngOnInit() {
    this.service.getAll().subscribe(res => console.log(res));
  }

}
