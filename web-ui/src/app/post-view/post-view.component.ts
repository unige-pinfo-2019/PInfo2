import { Component, OnInit } from '@angular/core';
//require('/post-view.component.js');
import {synapseThrow } from 'src/assets/post-view.component.js';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
import { CategoryService } from '../services/category.service';

//import {Http} from '@angular/http';
@Component({
selector: 'app-post-view',
templateUrl: './post-view.component.html',
styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {

  posts: any[];
  postSubscription: Subscription;
  searchValue;

  constructor(
    private postsService: PostsService,
    private categoryService:CategoryService
    ){

    }

    addToServer(){
     // this.postsService.addPost("Mon post","voici ma description de mon post",40);



    }
    getFromServer(){
      this.postsService.getPosts();
    }
    deleteFromServer(){
      //this.postsService.deletePosts();
    }
    onSearch(){
      this.searchValue= document.getElementById("srch-term");
      this.postsService.searchPost( this.searchValue.value.toString());

      //testing adding category with this button
      this.categoryService.addCategory('Books',0);
    }



    ngOnInit() {
      synapseThrow();
      this.postsService.getPosts();
      this.postSubscription= this.postsService.postsSubject.subscribe(
        (posts: any[]) => {
          this.posts= posts;
          //console.log(this.posts);
        }
      );
      this.postsService.emitPostSubject();
     // this.addToServer();

    }

  }


