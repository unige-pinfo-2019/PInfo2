import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { PostComponent } from './post/post.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit{
  ngOnInit() {
    
  }
  
  posts: any[];
  search: string;
  add:    string;
  constructor(private httpClient:HttpClient,
              private post:PostComponent){

  }
  onAdd(){
  //this.post.addToServer();

  }
  onGet(){
    //this.post.getFromServer();
    /*Url to be replaced*/
/*
    this.http.get('https://agility-acf42.firebaseio.com/').subscribe(response=>{
      this.posts=response.json();

    });
    console.log('trying to search: '+this.search);

  }*/
  }
}
