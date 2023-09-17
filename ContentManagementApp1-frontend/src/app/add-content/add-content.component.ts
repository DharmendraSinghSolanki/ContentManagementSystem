import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Content } from 'src/models/content';
import { ContentService } from '../services/content.service';

@Component({
  selector: 'app-add-content',
  templateUrl: './add-content.component.html',
  styleUrls: ['./add-content.component.css']
})
export class AddContentComponent implements OnInit {

  constructor(private contentService:ContentService,private snackbar:MatSnackBar,private route:Router) { }

  addContent:Content = {};

  ngOnInit(): void {
  }


  //   console.log(noteForm.content)
  //   console.log(noteForm)
  //   console.log(noteForm.value)
  //   this.noteservices.postNote(noteForm).subscribe( card => {
  //     alert("New card Added");
  //     this.cardAdd = {}
  //   })
  //  }
 

  onSubmit(contentForm:any){
      let content:Content = {
        postId: contentForm.postId,
        title: contentForm.title,
        content: contentForm.title,
        postedOn: contentForm.title,
        postedBy: contentForm.title
      }
  console.log(contentForm.value);
  console.log(content);
  console.log(this.addContent)
      this.contentService.postContent(this.addContent).subscribe(
        data => {
          console.log(data);
        }
      )
      this.snackbar.open('Note Added successfully', 'success', {​
             duration: 5000,​
             panelClass: ['mat-toolbar', 'mat-primary']​
             })

         this.route.navigateByUrl("dashBoard")
   }
}
