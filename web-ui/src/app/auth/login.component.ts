import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../core';
import { Router } from '@angular/router';

@Component({
  template: ''
})
export class LoginComponent implements OnInit {

    constructor(
        private authService: KeycloakService,
        private router: Router
    ) { }

    ngOnInit() {
      this.authService.login();
      this.router.navigateByUrl('/');
    }
}