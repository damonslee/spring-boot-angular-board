import {Component, OnInit, Inject, ViewEncapsulation} from '@angular/core';
import * as Editor from 'tui-editor';
import {DOCUMENT} from "@angular/common";

@Component({
  selector: 'app-board-write',
  templateUrl: './board-write.component.html',
  styleUrls: [
    '../../../../node_modules/tui-editor/dist/tui-editor-contents.css',
    '../../../../node_modules/tui-editor/dist/tui-editor.css',
    '../../../../node_modules/codemirror/lib/codemirror.css',
    '../../../../node_modules/highlight.js/styles/github.css',
    './board-write.component.css'
  ],
  encapsulation: ViewEncapsulation.None,
})

export class BoardWriteComponent implements OnInit {


  constructor(@Inject(DOCUMENT) private document: Document) {
  }

  ngOnInit() {
    let editor = new Editor({
      el: this.document.querySelector('#editSection'),
      initialEditType: 'markdown',
      previewStyle: 'vertical',
      hooks: {
        'addImageBlobHook': function(blob, callback) {
          //이미지 블롭을 이용해 서버 연동 후 콜백실행
          //callback('이미지URL');
        }
      },
      height: '600px'
    });
  }

}
