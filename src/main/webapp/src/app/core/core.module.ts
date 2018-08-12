import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NaviComponent } from './navi/navi.component';
import { ClarityModule } from '@clr/angular';

@NgModule({
  imports: [
    CommonModule,
    ClarityModule
  ],
  declarations: [NaviComponent],
  exports: [NaviComponent, ClarityModule]
})
export class CoreModule { }
