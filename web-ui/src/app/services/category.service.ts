import { Injectable } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { AddCategoryComponent } from '../add-category/add-category.component';
import { environment } from '../../environments/environment';

@Injectable()
export class CategoryService{
  
  categorySubject = new Subject<any[]>();
  subCategorySubject = new Subject<any[]>();

  private categoryList:any[];
  private subCategoryList:any[];

  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
    }),
  };

  constructor(private httpClient: HttpClient) {

  }

  getListCategory(){
    console.log("regular category search");
    this.httpClient.get<any[]>(environment.category_url)
    .subscribe(
      (response) => {
        this.categoryList=response;
        this.emitCategorySubject();
      },
      (error)=>{
        console.log('Couldnt load categoryList'+ error);
      }
    )
    
  }
  getListParentCategory(){
    console.log('searching parentid = null');
    //todo: make a search byCategory
    this.httpClient.get<any[]>(environment.category_url)
    .subscribe(
      (response)=>{
      this.categoryList=response.filter(
        cat=>{
          return cat.parentId === null;
        }
      );
      console.log("categoryParent success!");
      this.emitCategorySubject()
      },(error)=>{
        console.log(error);
      }
    );

  }
  getListChildCategory(parentId:number){
    this.httpClient.get<any[]>(environment.category_url+parentId+"/children")
    .subscribe(

      (response)=>{
        this.subCategoryList=response;
        this.emitSubCategorySubject();

      console.log("categoryChild success!");
      this.emitSubCategorySubject();
      },(error)=>{
        console.log(error);
      }
    );
    
  }
  emitCategorySubject() {
    this.categorySubject.next(this.categoryList.slice());
    
  }
  emitSubCategorySubject() {
    this.subCategorySubject.next(this.subCategoryList.slice());
  }
  addCategory(name:string,parentId:number){
    console.log('Adding Category...');
    const cat = {name:'',
                parentId:0}
    cat.name=name;
    cat.parentId= parentId;
    this.categoryList.push(cat);
    this.httpClient.post(environment.category_url,cat,this.httpOptions).subscribe(
      ()=>{
        console.log('Enregistrement category réussi! ');
      },(error) => {
        console.log('Erreur dans category ! '+ error);
      }
    )
  }
  deleteCategory(id:number){
    console.log("Deleting category..");
    this.httpClient
    .delete('http://localhost:12080/'+id)
    .subscribe(
      ()=>{
        console.log('Category deleted');
      },
      (error)=>{
        console.log(error);
      }
    )
    this.categoryList.pop();
    this.emitCategorySubject();
  }
  getCategoryId(name:string):number{
    return this.categoryList.find(
      (c)=>{
        return c.name ===name;
      }).id;

  }
  getCategoryName(id:number):string{
    return this.categoryList.find(
      (c)=>{
        return c.id === id;
      }
    ).name;
  }
}
