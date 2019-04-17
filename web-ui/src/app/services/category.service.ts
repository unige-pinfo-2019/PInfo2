import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

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



   postsSubject = new Subject<any[]>();




}
