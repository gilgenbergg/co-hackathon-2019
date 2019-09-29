import { Component, OnInit, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

const errText = 'There was an error, while loading the text.';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

@Injectable()
export class AppComponent implements OnInit {

  text = 'loading...';

  constructor( private http: HttpClient) {
      
  }
  ngOnInit(): void {
      
   
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
  }
  
}
