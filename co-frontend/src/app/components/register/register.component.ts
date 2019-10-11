import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../../services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

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
