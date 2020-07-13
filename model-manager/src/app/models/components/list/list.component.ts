import { Component } from '@angular/core';

@Component({
  selector: 'app-model-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.less']
})
export class ModelListComponent {
  models: string[] = ['Rest API Mocker', 'Example API', 'Burger API'];

  show(modelName: string) {
    console.log(modelName);
  }
}
