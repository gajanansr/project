import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";

import { IplRoutingModule } from "./ipl-routing.module";
import { ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HttpClientModule,
  ],
  exports: [
    
  ]
})
export class IplModule {}
