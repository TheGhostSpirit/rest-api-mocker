import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from '../shared/shared.module';
import { ModelsRoutingModule } from './models-routing.module';

import { ModelListComponent } from './list/list.component';
import { ModelImportComponent } from './import/import.component';
import { ModelClientComponent } from './client/client.component';

@NgModule({
  declarations: [
    ModelListComponent,
    ModelImportComponent,
    ModelClientComponent
  ],
  imports: [
    CommonModule,
    SharedModule,
    ModelsRoutingModule
  ]
})
export class ModelsModule { }
