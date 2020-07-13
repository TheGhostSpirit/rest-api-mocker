import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ModelListComponent } from './components/list/list.component';

const routes: Routes = [
  { path: '', component: ModelListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelsRoutingModule { }
