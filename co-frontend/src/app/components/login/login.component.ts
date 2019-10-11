import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  constructor(protected authService: AuthenticationService,
    private router: Router) { }

  ngOnInit() {
  }

  onClickMe() {
    this.authService.dummyLogin();
    this.router.navigate(['home']);
    window.location.href = ""
  }

}
