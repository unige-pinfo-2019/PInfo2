import { Component, OnInit,Input } from '@angular/core';
import { PostsService } from '../services/posts.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit {
  @Input() id: number;
  @Input() title: string ;
  @Input() description: string ;
  @Input() date: string ;
  @Input() price:number;
  @Input() categoryId:number;
  @Input() imageIds:number[];
  @Input() thumbnailUrl:string;




  constructor(private postService: PostsService) {

   }

  ngOnInit() {
    //this.postService.getPosts();
    this.thumbnailUrl='http://pinfo2.unige.ch:14080/image/'+this.imageIds.toString();
    console.log("category: "+ this.categoryId);
    console.log('imageId:'+ this.thumbnailUrl);
  }


  getPrice(){

    return this.price;
  }

  onDelete(){
    this.postService.deletePosts(this.id);
  }


}
