import { Component, OnInit } from '@angular/core';
import { UserService } from '../core';
import { Router } from '@angular/router';

@Component({})
export class LogoutComponent implements OnInit {

    constructor(
        private userService: UserService,
        private router: Router
    ) { }

    ngOnInit() {
        this.userService.purgeUser();
        this.router.navigateByUrl('/');
    }
}