import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Http } from '@angular/http';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
import { FormGroup } from '@angular/forms';
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

  //index:number;
//  id: number;
httpOptions = {
  headers: new HttpHeaders({
      'Content-Type': 'application/json',


  }),
};





  constructor(private httpClient: HttpClient,
    private postsService: PostsService,
    ){


    }

    addToServer(){
      this.postsService.addPost("Mon post","voici ma description de mon post",40);



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
    }



    ngOnInit() {
      //this.postsService.getPosts();
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
