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


        this.postsService.addPost(formValue['title'],
                                  formValue['description'],
                                  formValue['price']);
        //console.log('description: '+ formValue['description']);
        /*const newUser = new PostComponent(
          formValue['firstName'],
          formValue['lastName'],
          formValue['drinkPreference'],
          formValue['photos'] ? formValue['photos'] : []
        );*/
        //this.postsService.addPost(newUser);

        this.router.navigate(['/posts']);
    }
    getPhotos(): FormArray {
        return this.postForm.get('photos') as FormArray;
    }
    onAddHobby() {
        const newHobbyControl = this.formBuilder.control(null, Validators.required);
        this.getPhotos().push(newHobbyControl);
    }
    onUploadFiles(event){
      console.log("onUploadFiles");
      this.selecetdFile = event.target.files[0];
      this.postsService.fileToServer(this.selecetdFile);

    }

}
