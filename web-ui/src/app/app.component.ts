import { Component, OnInit } from '@angular/core';
import { UserService, KeycloakService } from './core';
import { KeycloakInstance } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'web-ui';

  constructor(
    private userService: UserService,
    public keycloak: KeycloakService
  ) {}

  ngOnInit() {
    this.keycloak.getKeycloakAuth();
    // if (this.keycloak.isLoggedIn() === false) {
    //   this.keycloak.login();
    // }
    this.userService.populate();
  }                                               
}
