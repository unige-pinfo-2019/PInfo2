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
        this.router.navigateByUrl('/');
    }
}