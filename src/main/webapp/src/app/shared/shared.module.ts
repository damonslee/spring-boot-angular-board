import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClarityModule } from '@clr/angular';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ClarityModule,
    RouterModule
  ],
  declarations: [],
  exports: [ClarityModule, FormsModule, RouterModule, HttpClientModule]
})
export class SharedModule { }
