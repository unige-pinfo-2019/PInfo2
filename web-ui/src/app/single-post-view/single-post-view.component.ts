import { Component, OnInit } from '@angular/core';
import { PostsService } from '../services/posts.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-single-post-view',
  templateUrl: './single-post-view.component.html',
  styleUrls: ['./single-post-view.component.scss']
})
export class SinglePostViewComponent implements OnInit {
  title: string = '';
  description: string = '';
  price:number;
  date:Date;
  categoryId=0;
  imageIds=0;
  imageUrl='';

  constructor(private postService:PostsService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id'];
    this.postService.getPostById(+id);
    console.log('onInit singlePost is : ' + this.postService.singlePost);
    console.log(this.postService.posts);
    let post = this.postService.posts;
    //console.log("postTitle: "+ post);
    //console.log(post[id].title);
    //console.log('id: '+ id);
    
    this.title = post[id].title;
    this.description = post[id].description;
    this.price = post[id].price;
    this.date= post[id].date;
    
    //for(let i=0;i<post[id].imageIds-1;i++){
      
      this.imageIds= post[id].imageIds[0];
      this.imageUrl='http://pinfo2.unige.ch:14080/image/'+this.imageIds.toString();
      console.log("src= "+ this.imageUrl );
     // console.log("imagesIDs: "+ this.imageIds);
      //console.log("imageid[i]"+ this.imageIds[i] );
    //}
    //console.log('imageIds: '+ this.imageIds);
    
  }

}
