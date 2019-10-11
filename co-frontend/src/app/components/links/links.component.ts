import { Component, OnInit } from '@angular/core';
import { LinksService } from '../../links.service';

@Component({
  selector: 'app-links',
  templateUrl: './links.component.html',
  styleUrls: ['./links.component.scss']
})
export class LinksComponent implements OnInit {

  constructor(private linksService: LinksService) { }

  keys = []
  data = []

  ngOnInit() {
    this.linksService.sendGetRequest().subscribe((data: any[])=>{
      console.log(data);
      this.data = data;
      for (let key of Object.keys(data)) {
        this.keys.push(key)
      }
    })
  }

}
