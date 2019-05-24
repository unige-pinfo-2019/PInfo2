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
  categoryList=[[]];
  subCategoryList:any[];
  listSubscription:Subscription;
  subListSubscription:Subscription;
  clicked =true;
  constructor(private categoryService: CategoryService, private postService: PostsService) {

  }

  ngOnInit() {
    this.categoryService.getListParentCategory();

    this.listSubscription= this.categoryService.categorySubject.subscribe(
      (catList:any[]) => {
        this.categoryList=catList;
      }
    );

    this.subListSubscription=this.categoryService.subCategorySubject.subscribe(
      (subCat:any[])=>{
        this.subCategoryList=subCat;
      }
    );
  }

  onSelectCategory(id:number){

    this.categoryService.getListChildCategory(id);
    console.log(this.subCategoryList);
    console.log("You selected the "+id+" category!");
    
  }
}
