import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { CategoryService } from '../services/category.service';
import { Router } from '@angular/router';
import { Subscription, Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.scss']
})

export class AddCategoryComponent implements OnInit , OnDestroy{
  postForm : FormGroup;
  deleteForm: FormGroup;
  categoryList: any[];
  listSubscription: Subscription;

  constructor(private formBuilder: FormBuilder, private categoryService: CategoryService, private router: Router) {

  }

  ngOnInit() {
    this.initForm();
    this.categoryService.emitCategorySubject();
    this.categoryService.getListCategory();
    this.listSubscription = this.categoryService.categorySubject.subscribe(
      (catList:any[]) => {
        this.categoryList=catList;
      }
    )
    this.categoryService.emitCategorySubject();
  }

  ngOnDestroy(){
    this.listSubscription.unsubscribe();
  }

  initForm() {
    this.postForm = this.formBuilder.group({
      name: ['',Validators.required],
      parentId:[0,],
      });
      this.deleteForm = this.formBuilder.group({});
      this.categoryService.emitCategorySubject();
  }

  onSubmitForm() {
    const formValue = this.postForm.value;
    this.categoryService.emitCategorySubject();
    this.categoryService.addCategory(formValue['name'], formValue['parentId'],);
  }

  onDeleteCategory(){
    const catToDelete = (<HTMLInputElement>document.getElementById('category')).value;
    const catId = this.categoryList.find(
      (c)=>{
        return c.name ===catToDelete;
      }).id;
      console.log("Deleting "+ catToDelete + ", of id: "+ catId);
    this.categoryService.deleteCategory(catId);
    this.categoryService.emitCategorySubject();
  }
}
