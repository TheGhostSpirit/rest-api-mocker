import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../shared/shared.module';
import { ModelsRoutingModule } from './models-routing.module';

import { ModelListComponent } from './components/list/list.component';

@NgModule({
  declarations: [
    ModelListComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ModelsRoutingModule
  ]
})
export class ModelsModule { }
