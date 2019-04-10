import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
//import { Http } from '@angular/http';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
//import {Http} from '@angular/http';
@Component({
selector: 'app-post-view',
templateUrl: './post-view.component.html',
styleUrls: ['./post-view.component.scss']
})
export class PostViewComponent implements OnInit {

  posts: any[];
  postSubscription: Subscription;
  //index:number;
//  id: number;




  constructor(private httpClient: HttpClient,
    private postsService: PostsService,
    ){


    }

    addToServer(){

      //this.postsService.postService();


    }
    getFromServer(){
      this.postsService.getPosts();
    }
    deleteFromServer(){
      //this.postsService.deletePosts();
    }



    ngOnInit() {
      this.postsService.getPosts();
      this.postSubscription= this.postsService.postsSubject.subscribe(
        (posts: any[]) => {
          this.posts= posts;
        }
      );
      this.postsService.emitPostSubject();
    }

  }
