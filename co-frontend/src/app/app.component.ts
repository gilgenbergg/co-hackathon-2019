import { Component, OnInit, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AuthenticationService } from './services';
import { Router } from '@angular/router';

const errText = 'There was an error, while loading the text.';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

@Injectable()
export class AppComponent implements OnInit {

  text = 'loading...';
  public currentUser: boolean;

  constructor( private http: HttpClient, private auth: AuthenticationService, private router: Router) {
      this.currentUser = auth.currentUserValue != undefined && auth.currentUserValue;
  }
  ngOnInit(): void {
      
  /*
  const response = this.http.get("/co-backend/example/welcome").subscribe(
            (res) => this.fetchData(res), 
            (err) => this.text = errText,
            () => console.log('complete')
    );
  }
  
  fetchData(res) {
      if(!res || !res.text) {
          this.text = errText;
      }
 
      this.text = res.text;
      */
  }

  logout(): void {
    this.auth.logout();
    this.router.navigate(['home']);
    window.location.reload();
  }
}
