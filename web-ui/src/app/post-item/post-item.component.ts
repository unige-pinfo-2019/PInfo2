import { Component, OnInit,Input } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.scss']
})

export class PostItemComponent implements OnInit {
  @Input() id: number;
  @Input() title: string ;
  @Input() description: string ;
  @Input() date: string ;
  @Input() price:number;
  @Input() categoryId:number;
  @Input() imageIds=[];
  @Input() thumbnailUrl:string;

  constructor(private postService: PostsService) {

  }

  ngOnInit() {
    this.thumbnailUrl = environment.images_url + this.imageIds[0];
    //console.log("thumbnail"+ this.thumbnailUrl);
  }

  getPrice(){
    return this.price;
  }

  onDelete(){
    this.postService.deletePosts(this.id);
  }
}
