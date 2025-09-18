import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CricketerCreateComponent } from './ipl/components/cricketercreate/cricketercreate.component';

const routes: Routes = [
  {
    path: 'auth',
    loadChildren: () => import('./auth/auth.module').then((m) => m.AuthModule),
  },
  {
    path: 'ipl',
    loadChildren: () => import('./ipl/ipl.module').then((m) => m.IplModule),
  },
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/auth',  // Redirect to 'auth' route by default
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
