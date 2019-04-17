import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../services/category.service';

@Component({
  selector: 'app-category-menu',
  templateUrl: './category-menu.component.html',
  styleUrls: ['./category-menu.component.scss']
})
export class CategoryMenuComponent implements OnInit {
  category:any[];

  constructor(private categoryService: CategoryService) { }

  ngOnInit() {
    this.category = this.categoryService.categoryList;

  }
  onSelectCategory(catName:string){
    console.log("You selected the "+catName+" category!");
  }

}
