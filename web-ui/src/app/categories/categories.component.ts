import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Subscription } from 'rxjs';
import { PostsService } from '../services/posts.service';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})

export class CategoriesComponent implements OnInit {
  categoryList:any[];
  listSubscription:Subscription;

  constructor(private categoryService: CategoryService, private postService: PostsService) {

  }

  ngOnInit() {
    this.categoryService.getListCategory();
    this.listSubscription= this.categoryService.categorySubject.subscribe(
      (catList:any[]) => {
        this.categoryList=catList;
      }
    );
  }

  onSelectCategory(catName:string){
    this.postService.searchCategory(this.categoryService.getCategoryId(catName));
    console.log("You selected the "+catName+" category!");
  }
}
