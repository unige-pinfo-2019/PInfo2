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
  @Input() category:string;
  @Input() image:string;




  constructor(private postService: PostsService) {

   }

  ngOnInit() {
    //this.postService.getPosts();
  }


  getPrice(){

    return this.price;
  }
  getLikes(){

    //return this.postService.getPostById(this.id).likes;
  }
  onLiker(){

//  this.postService.like(this.id);

  }
  onDisliker(){

  // this.postService.dislike(this.id);
  }
  onDelete(){
    this.postService.deletePosts(this.id);
  }
/*  getColor(){
    if(this.appareilStatus=='allumé'){
      return 'green';
    }else if(this.appareilStatus=='éteint'){
      return 'red';
    }else if (this.appareilStatus=='brillantes'){
      return 'yellow';
    }
  }*/
/*  onAllumerOne(id:number){
    this.appareilService.switchOnOne(id-1);
  }
  onEteindreOne(id:number){
    this.appareilService.switchOffOne(id-1);
  }*/
}
