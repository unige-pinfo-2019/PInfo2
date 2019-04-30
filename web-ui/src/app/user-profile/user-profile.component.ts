import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {
  posts: any[];
  postSubscription: Subscription;

  constructor(private postService:PostsService) { }

  ngOnInit() {
    //this.postService.getPosts();
    this.postSubscription= this.postService.postsSubject.subscribe(
      (posts: any[]) => {
        this.posts= posts;
        //console.log(this.posts);
      }
    );
    this.postService.emitPostSubject();
  }

}
