import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-view',
  templateUrl: './user-view.component.html',
  styleUrls: ['./user-view.component.scss']
})

export class UserViewComponent implements OnInit {
  posts: any[];
  postSubscription: Subscription;
  
  constructor(private postService: PostsService) {

  }

  ngOnInit() {
    this.postSubscription= this.postService.postsSubject.subscribe(
      (posts: any[]) => {
        this.posts= posts;
      }
    );
    this.postService.emitPostSubject();
  }
}
