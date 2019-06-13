import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { User, UserService, KeycloakService } from '../core';
import { concatMap, tap } from 'rxjs/operators';

@Component({
    selector: 'app-profile-page',
    templateUrl: './profile.component.html'
})
export class ProfileComponent implements OnInit {
    constructor(
        private route: ActivatedRoute,
        private userService: UserService,
        private authService: KeycloakService
    ) { }

    profile: User;
    currentUser: User;
    isUser: boolean;

    ngOnInit() {
        this.route.data.pipe(
            concatMap(data => {
                this.profile = data.profile;
                this.profile.mobile = data.profile.attributes.mobile;
                // Load the current user's data.
                return this.userService.currentUser.pipe(tap(
                    (userData: User) => {
                        this.currentUser = userData;
                        this.isUser = (this.currentUser.username === this.profile.username);
                    }
                ));
            })
        ).subscribe();
    }

    onClickSettings() {
        this.authService.settingsPage();
    }

}