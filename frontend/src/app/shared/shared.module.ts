import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { AuthModule } from '../auth/auth.module';
import { NavBarComponent } from './navbar/navbar.component';

@NgModule({
  declarations: [NavBarComponent],
  imports: [
    CommonModule,
    RouterModule,
    AuthModule
  ],
  exports: [NavBarComponent]
})
export class SharedModule {}
