import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Route, RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { CoreModule } from './core/core.module';
import { BoardModule } from './board/board.module';
import { HomeModule } from './home/home.module';
import { HomeComponent } from "./home/home/home.component";
import { SharedModule } from "./shared/shared.module";

const routes:Routes = [
  {
    path: '', component: HomeComponent
  }
];


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    CoreModule,
    BoardModule,
    HomeModule,
    SharedModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
