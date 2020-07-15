import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AuthGuard } from './core/services/auth-guard/auth.guard';

const routes: Routes = [
  {
    path: 'models',
    loadChildren: () => import('./models/models.module').then(m => m.ModelsModule),
    canActivate: [AuthGuard],
  },
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
