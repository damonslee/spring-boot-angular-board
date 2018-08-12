import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaviComponent } from './navi/navi.component';
import { ClarityModule } from '@clr/angular';
import { SharedModule } from "../shared/shared.module";

@NgModule({
  imports: [
    CommonModule,
    ClarityModule,
    SharedModule
  ],
  declarations: [NaviComponent],
  exports: [NaviComponent, ClarityModule]
})
export class CoreModule { }
