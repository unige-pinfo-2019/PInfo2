import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { PostItemComponent } from './post-item/post-item.component';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {

  posts: any[];
  search: string;
  add: string;

  ngOnInit() {

  }

  constructor(private httpClient: HttpClient, private post: PostItemComponent) {

  }

  // Useless ?
  onAdd(){

  }

  // Useless ?
  onGet(){

  }
}
