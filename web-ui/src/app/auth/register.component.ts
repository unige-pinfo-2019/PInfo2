import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../core';
import { Router } from '@angular/router';

@Component({
    template: ''
})
export class RegisterComponent implements OnInit {

    constructor(
        private authService: KeycloakService,
        private router: Router
    ) { }

    ngOnInit() {
        if (!this.authService.isLoggedIn()) {
            this.authService.login({action: 'register'});
        } else {
            this.router.navigateByUrl('/');
        }
    }
}