import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ModelListComponent } from './list/list.component';
import { ModelClientComponent } from './client/client.component';
import { ModelEditorComponent } from './editor/editor.component';

const routes: Routes = [
  { path: ':id/editor', component: ModelEditorComponent },
  { path: ':id/client', component: ModelClientComponent },
  { path: '', component: ModelListComponent }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModelsRoutingModule { }
