import { Component, Input } from '@angular/core';

import { Ad, AdQuery, AdService } from 'src/app/core/';

import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-ad-list',
  templateUrl: './ad-list.component.html',
  styleUrls: ['./ad-list.component.scss']
})
export class AdListComponent {

  constructor(
    private adService: AdService
  ) { }

  imagesUrl = `${environment.api_url}/image/`;

  @Input() limit: number;
  @Input()
  set config(config: AdQuery) {
    if (config) {
      this.query = config;
      this.currentPage = 1;
      this.runQuery();
    }
  }

  query: AdQuery;
  results: Ad[];
  loading = false;
  currentPage = 1;
  totalPages: Array<number> = [1];

  setPageTo(pageNumber) {
    this.currentPage = pageNumber;
    this.runQuery();
  }

  runQuery() {
    this.loading = true;
    this.results = [];

    this.adService.query(this.query)
      .subscribe(data => {
        this.loading = false;
        this.results = data;
      });
  }

}
