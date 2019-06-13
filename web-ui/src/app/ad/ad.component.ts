import { Component, OnInit } from '@angular/core';
import { Ad, CategoryService, User, UserService } from '../core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from '../../environments/environment';

@Component({
  selector: 'app-ad-item',
  templateUrl: './ad.component.html',
  styleUrls: ['./ad.component.scss']
})
export class AdComponent implements OnInit {
  ad: Ad;
  categoryName: string;
  author: User;
  imagesUrl = `${environment.api_url}/image/`;

  constructor(
    private route: ActivatedRoute,
    private categoryService: CategoryService,
    private userService: UserService
  ) {}

  ngOnInit() {
    // Retreive the prefetched article
    this.route.data.subscribe(
      data => {
        this.ad = data.ad;
        // Retreive the author
        this.userService.isAuthenticated.subscribe(
          val => {
            if (val) {
              this.userService.getUserById(this.ad.userId).subscribe(
                data => this.author = data
              );
            }
          }
        );
      }
    );

    // Retreive the category
    this.categoryService.get(this.ad.categoryId).subscribe(
      data => {
        this.categoryName = data.name;
      }
    );

  }
}

