import { Component, OnInit, ElementRef, ViewChild } from '@angular/core';
import { AuthenticationService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  
  @ViewChild('logon', {static: true}) logon: ElementRef;
  @ViewChild('password', {static: true}) password: ElementRef;
  @ViewChild('password2', {static: true}) password_confirm: ElementRef;
  @ViewChild('email', {static: true}) email: ElementRef;

  constructor(protected authService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
  }

  onClickMe() {
    if (this.password.nativeElement.value != this.password_confirm.nativeElement.value) {
      alert ("passwords do not match");
      return
    }
    this.authService.register(this.logon.nativeElement.value,
      this.password.nativeElement.value,
      this.email.nativeElement.value)
  }

}
