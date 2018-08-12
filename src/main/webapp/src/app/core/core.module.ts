import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaviComponent } from './navi/navi.component';
import { SharedModule } from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    SharedModule
  ],
  declarations: [NaviComponent],
  exports: [NaviComponent]
})
export class CoreModule { }
