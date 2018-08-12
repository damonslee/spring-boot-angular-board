import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BoardListComponent } from './board-list/board-list.component';
import { BoardWriteComponent } from './board-write/board-write.component';

const routes: Routes = [
  {
    path: 'boards',
    component: BoardListComponent
  },
  {
    path: 'boards-write',
    component: BoardWriteComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BoardRoutingModule { }
