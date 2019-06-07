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

  formatedCategories : any[];
  searchField: FormControl = new FormControl();
  listConfig: AdQuery = {
    query: '',
    filters: {}
  }

  ngOnInit() {
    this.categoryService.getFormatedCategories().subscribe(
      data => this.formatedCategories = data
    );
  }

  onSearch() {
    this.setListTo(this.searchField.value);
  }

  setListTo(query: string = '', filters: Object = {}) {
    this.listConfig = {query: query ? query : '', filters: filters};
  }

  onClickCategory(event){
    if (event)
      this.setListTo(this.searchField.value, { categoryId: event });
    else
      this.setListTo(this.searchField.value);
  }


}
