import { Component } from '@angular/core';

@Component({
  selector: 'app-model-list',
  templateUrl: './model-list.component.html',
  styleUrls: ['./model-list.component.less']
})
export class ModelListComponent {
  models: string[] = ['Rest API Mocker', 'Example API', 'Burger API'];

  show(modelName: string) {
    console.log(modelName);
  }
}
