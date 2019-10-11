import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { AuthenticationService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  @ViewChild('logon', {static: true}) logon: ElementRef;
  @ViewChild('password', {static: true}) password: ElementRef;

  constructor(protected authService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
  }

  onClickMe() {
    let user = this.logon.nativeElement.value;
    let password = this.password.nativeElement.value;
    this.authService.login(user, password);
    //this.router.navigate(['home']);
    //window.location.href = ""
  }

}
