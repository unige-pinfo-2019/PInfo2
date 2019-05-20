import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import{PostsService} from '../services/posts.service';
import {Router} from '@angular/router';
import { PostComponent } from '../post/post.component';
import { CategoryService } from '../services/category.service';
import { Subscription } from 'rxjs';
@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.scss']
})
export class NewPostComponent implements OnInit {
  postForm : FormGroup;
  selecetdFile : File;
  imageId=[];
  categoryList:any[];
  categorySubscription:Subscription;

  constructor(private formBuilder: FormBuilder,
              private postsService:PostsService,
              private router: Router,
              private categoryService:CategoryService) { }

  ngOnInit() {
    this.initForm();
    this.categoryService.getListCategory();
    this.categorySubscription = this.categoryService.categorySubject.subscribe(
      (cat:any[])=>{
        this.categoryList=cat;
      }
    )
    //this.categoryList= this.categoryService.categoryList;
  }
  initForm(){


    this.postForm = this.formBuilder.group({
        title: ['',Validators.required],
        description: ['',Validators.required],
        price:['',Validators.required],
        category: ['',Validators.required],
        photos: this.formBuilder.array([])
    });
    }
    onSubmitForm() {
        const formValue = this.postForm.value;
        this.imageId= this.postsService.imageId;
        const catId = this.categoryService.getCategoryId(formValue['category'])

        this.postsService.addPost(formValue['title'],
                                  formValue['description'],
                                  formValue['price'],
                                  catId,
                                  this.imageId,0
                                  );
        console.log("cat.id"+ this.categoryService.getCategoryId(formValue['category']));
   

        this.router.navigate(['/posts']);
    }
    getPhotos(): FormArray {
        return this.postForm.get('photos') as FormArray;
    }

    async onUploadFiles(event){
      console.log("onUploadFiles");
      for(let i=0;i<event.target.files.length;i++){
        console.log("number of files recieved:"+ i)
        this.selecetdFile = event.target.files[i];
        await this.postsService.fileToServer(this.selecetdFile);
      }
      
      console.log('postsService imagaeId '+ this.postsService.imageId);
      
    

    }

}
