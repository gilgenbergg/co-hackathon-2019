import { Component, OnInit } from '@angular/core';
import { LinksService } from '../../links.service';

@Component({
  selector: 'app-links',
  templateUrl: './links.component.html',
  styleUrls: ['./links.component.scss']
})
export class LinksComponent implements OnInit {

  constructor(private linksService: LinksService) { }

  my_links = []

  ngOnInit() {
    this.linksService.sendGetRequest().subscribe((data: any[])=>{
      console.log(data);
      this.my_links = data;
    })
  }

}
