import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { Content } from 'src/models/content';
import { ContentService } from '../services/content.service';

@Component({
  selector: 'app-edit-content',
  templateUrl: './edit-content.component.html',
  styleUrls: ['./edit-content.component.css']
})
export class EditContentComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute,private contentService:ContentService,private route:Router) { }

  content:Content = {};

  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(params => {
      let id = params.get("id") ?? 1;
      this.contentService.getSpecificContent(id).subscribe(data => {
        console.log(data);
        this.content = data;
      })
    });
  }

  editNote() {
    this.contentService.editPost(this.content).subscribe(data => {
      this.content = data;
      this.route.navigateByUrl("dashBoard")
    })
  }

  deleteNote(){
    this.contentService.deletePost(this.content?.postId).subscribe(data =>{
    });
    this.route.navigateByUrl("dashBoard")
  }


}
