import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Subscription } from 'rxjs';
import { PostsService } from '../services/posts.service';

@Component({
  selector: 'app-category-menu',
  templateUrl: './category-menu.component.html',
  styleUrls: ['./category-menu.component.scss']
})
export class CategoryMenuComponent implements OnInit {
  categoryList:any[];
  listSubscription:Subscription;

  constructor(private categoryService: CategoryService,
              private postService:PostsService) { }

  ngOnInit() {
    this.categoryService.getListCategory();
    //this.categoryService.emitCategorySubject();
    this.listSubscription= this.categoryService.categorySubject.subscribe(
      (catList:any[]) => {
        this.categoryList=catList;
      }
    );
    //this.categoryService.emitCategorySubject();

    //this.category = this.categoryService.categoryList;

  }
  onSelectCategory(catName:string){
    this.postService.searchCategory(this.categoryService.getCategoryId(catName));
    console.log("You selected the "+catName+" category!");
  }

}
