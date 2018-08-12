import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BoardRoutingModule } from './board-routing.module';
import { BoardListComponent } from './board-list/board-list.component';
import { BoardWriteComponent } from './board-write/board-write.component';

@NgModule({
  imports: [
    CommonModule,
    BoardRoutingModule
  ],
  declarations: [BoardListComponent, BoardWriteComponent],
  exports: [BoardListComponent, BoardWriteComponent]
})
export class BoardModule { }
