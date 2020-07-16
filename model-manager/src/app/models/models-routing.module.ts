import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ModelListComponent } from './list/list.component';
import { ModelClientComponent } from './client/client.component';

const routes: Routes = [
  { path: 'client', component: ModelClientComponent },
  { path: '', component: ModelListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelsRoutingModule { }
