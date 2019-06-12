import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { AdQuery, User } from '../core';

@Component({
    selector: 'app-profile-ads',
    templateUrl: './profile-ads.component.html'
})
export class ProfileAdsComponent implements OnInit {
    constructor(
        private route: ActivatedRoute,
        private router: Router
    ) { }

    profile: User;
    adsConfig: AdQuery = {
        query: '',
        filters: {}
    };

    ngOnInit() {
        this.route.parent.data.subscribe(
            (data: { profile: User }) => {
                this.profile = data.profile;
                this.adsConfig = {
                    query: '',
                    filters: {}
                };
                this.adsConfig.filters.userId = this.profile.id;
            }
        );
    }

}