import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ClarityModule } from "@clr/angular";
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  imports: [
    CommonModule,
    ClarityModule,
    HttpClientModule
  ],
  declarations: [],
  exports: [
    RouterModule,
    ClarityModule
  ]
})
export class SharedModule { }
