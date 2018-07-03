import { NgModule } from '@angular/core';

import { NaviComponent } from './navi/navi.component';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule } from '@angular/forms';


@NgModule({
  imports: [
    CommonModule,
    SharedModule,
    FormsModule
  ],
  declarations: [NaviComponent, LoginComponent],
  exports: [NaviComponent, LoginComponent]
})
export class CoreModule { }
