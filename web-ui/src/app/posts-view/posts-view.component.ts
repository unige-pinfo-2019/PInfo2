import { Component, OnInit } from '@angular/core';
import { synapseThrow } from 'src/assets/post-view.component.js';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-posts-view',
  templateUrl: './posts-view.component.html',
  styleUrls: ['./posts-view.component.scss']
})

export class PostsViewComponent implements OnInit {

  posts: any[];
  postSubscription: Subscription;
  searchValue;

  constructor(private postsService: PostsService, private categoryService: CategoryService){

  }


  getFromServer(){
    this.postsService.getPosts();
  }



  onSearch(){
    this.searchValue = document.getElementById("srch-term");
    this.postsService.searchPost( this.searchValue.value.toString());
    //this.categoryService.addCategory('Books',0);
  }

  ngOnInit() {
    synapseThrow();
    this.postsService.getPosts();
    this.postSubscription = this.postsService.postsSubject.subscribe(
      (posts: any[]) => {
        this.posts= posts;
      }
    );
    
    this.postsService.emitPostSubject();
  }
}
