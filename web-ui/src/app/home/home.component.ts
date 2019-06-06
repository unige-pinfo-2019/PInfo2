import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';

import { AdQuery, Category, CategoryService } from '../core';

@Component({
  selector: 'app-home-page',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  constructor(
    private categoryService: CategoryService
  ) { }

  categoryMap = new Map<string, Category[]>();
  subCategoryList: Category[];
  searchField: FormControl = new FormControl();
  listConfig: AdQuery = {
    query: '',
    filters: {}
  }
  selectedCategory = "Categories";

  ngOnInit() {
    this.categoryService.getAll().subscribe(
      data => {
        let parents = data.filter(elem => elem.parentId === null);
        for(let parent of parents) {
          this.categoryMap.set(parent.name, data.filter(elem => elem.parentId == parent.id));
        }
      }
    );
  }

  onSearch() {
    this.setListTo(this.searchField.value);
  }

  setListTo(query: string = '', filters: Object = {}) {
    this.listConfig = {query: query, filters: filters};
  }

  onClickCategory(category: Category){
    this.selectedCategory = category.name;
    this.setListTo(this.searchField.value, {categoryId: category.id});
  }


}
