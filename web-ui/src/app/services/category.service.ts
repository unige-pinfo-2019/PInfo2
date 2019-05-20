import { Injectable } from '@angular/core';
import { Subject, Subscription } from 'rxjs';


import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AddCategoryComponent } from '../add-category/add-category.component';

@Injectable()
export class CategoryService{
  loaded=false;
  categorySubject = new Subject<any[]>();
  private categoryList:any[];

  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',
  
  
    }),
  };
  
  constructor(private httpClient: HttpClient,
              ){

  }
  getListCategory(){
    this.loaded = false;
    console.log('Loading categories..');
    this.httpClient.get<any[]>('http://localhost:12080/category')
    .subscribe(
      (response) => {
        this.categoryList=response;
        this.emitCategorySubject();
        
        
      },
      (error)=>{
        console.log('Couldnt load categoryList'+ error);
      }
      
    )
    
    console.log("load category finished!");
    //this.emitCategorySubject();
  }
  emitCategorySubject() {
    this.categorySubject.next(this.categoryList.slice());
  }
  addCategory(name:string,parentId:number){
    console.log('Adding Category...');
    const cat = {name:'',
                parentId:0}

    cat.name=name;
    cat.parentId= parentId;
    this.categoryList.push(cat);
    this.httpClient.post('http://localhost:12080/category',cat,this.httpOptions).subscribe(
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
    /*this.categoryList.splice(this.categoryList.find(
      (c)=>{
        return c.id===id;
      }
    ),1);
  }*/
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
