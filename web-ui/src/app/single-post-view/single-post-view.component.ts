import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { PostsService } from '../services/posts.service';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-single-post-view',
  templateUrl: './single-post-view.component.html',
  styleUrls: ['./single-post-view.component.scss']
})
export class SinglePostViewComponent implements OnInit {
  
  categoryName="";
  imageIds=[];
  imageUrl=[];
  imageServer="";
  monPost;

  constructor(private postService:PostsService,
              private categoryService: CategoryService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    const id = this.route.snapshot.params['id']-1;
    this.monPost=this.postService.getPostById(+id+1);
    this.categoryName = this.categoryService.getCategoryName(this.monPost.categoryId);
    
    this.postService.emitPostSubject();
    console.log(this.monPost);
    //console.log(post[id].title);
    //console.log('id: '+ id);
    /*
    this.title = post[id].title;
    this.description = post[id].description;
    this.price = post[id].price;
    this.date= post[id].date;
    */
    for(let i=0;i<this.monPost.imageIds.length;i++){
      
      this.imageUrl.push(this.imageServer+this.monPost.imageIds[i]);
      //this.imageIds= post[id].imageIds[i];
      //this.imageUrl='http://localhost:14080/image/'+this.imageIds.toString();
      
     // console.log("src= "+ this.imageUrl );
     // console.log("imagesIDs: "+ this.imageIds);
      //console.log("imageid[i]"+ this.imageIds[i]);
   }
   //console.log(this.imageUrl);
    //console.log('imageIds: '+ this.imageIds);
    
  }

}
