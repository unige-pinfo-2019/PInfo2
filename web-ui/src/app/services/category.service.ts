import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class CategoryService{
  categoryList=[
    {
    name:'Livres',
  },{
    name:'Vélos'
  },{
    name:'Notes de cours'
  },{
    name:'Ordinateurs'
  }]; 

  constructor(private httpClient: HttpClient){

  }
  getListCategory(){
    console.log('Loading categories..');
    this.httpClient.get<any[]>('http://localhost:12080/category')
    .subscribe(
      (response) => {
        this.categoryList=response;
      },
      (error)=>{
        console.log('Couldnt load categoryList'+ error);
      }
    )
  }




   categorySubject = new Subject<any[]>();




}
