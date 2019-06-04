import { Component, OnInit } from '@angular/core';
import { Ad, CategoryService } from '../core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ad-item',
  templateUrl: './ad.component.html',
  styleUrls: ['./ad.component.scss']
})
export class AdComponent implements OnInit {
  ad: Ad;
  categoryName: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private categoryService: CategoryService
  ) {}

  ngOnInit() {
    // Retreive the prefetched article
    this.route.data.subscribe(
      data => {
        this.ad = data.ad;
      }
    );

    // Retreive the category
    this.categoryService.get(this.ad.categoryId).subscribe(
      data => {
        this.categoryName = data.name;
      }
    )
  }
}

