import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import{PostsService} from '../services/posts.service';
import {Router} from '@angular/router';
import { PostComponent } from '../post/post.component';
@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {
  postForm : FormGroup;
  selecetdFile : File;
  imageId:number;

  constructor(private formBuilder: FormBuilder,
              private postsService:PostsService,
              private router: Router) { }

  ngOnInit() {
    this.initForm();
  }
  initForm(){


    this.postForm = this.formBuilder.group({
        title: ['',Validators.required],
        description: ['',Validators.required],
        price:['',Validators.required],
        category: ['',],
        photos: this.formBuilder.array([])
    });
    }
    onSubmitForm() {
        const formValue = this.postForm.value;
        this.imageId= this.postsService.imageId;
        console.log('onSumit imageid'+ this.imageId);
        console.log('onSumit imageid on postService '+ this.postsService.imageId);

        this.postsService.addPost(formValue['title'],
                                  formValue['description'],
                                  formValue['price'],
                                  0,
                                  [this.imageId],0
                                  );
   

        this.router.navigate(['/posts']);
    }
    getPhotos(): FormArray {
        return this.postForm.get('photos') as FormArray;
    }

    async onUploadFiles(event){
      console.log("onUploadFiles");
      this.selecetdFile = event.target.files[0];
      await this.postsService.fileToServer(this.selecetdFile);
      console.log('postsService imagaeId '+ this.postsService.imageId);
      
    

    }

}
