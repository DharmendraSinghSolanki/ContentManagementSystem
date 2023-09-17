import { Component, OnInit } from '@angular/core';
import { Content } from 'src/models/content';
import { ContentService } from '../services/content.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  constructor(private contentService:ContentService ) { }

  contents:any = [];

  ngOnInit(): void {
    this.contentService.getContent().subscribe({
      next: data => {
        this.contents = data;
      }
    })
  }

}
