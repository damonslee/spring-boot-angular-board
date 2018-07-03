import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/of';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userId:String;
  password:String;
  errorMessage:String;
  @ViewChild(NgForm) loginForm: NgForm;
  constructor(private http: HttpClient) { }
  login() {
    this.http.post('http://localhost:8080/login', {
      userId: this.userId,
      password: this.password
    }).do(console.log)
    .subscribe(v => console.log(v), e => {
      console.error(e);
      this.errorMessage = e.error.msg;
      return Observable.of({});
    });
  }
  ngOnInit() {
  }
}
