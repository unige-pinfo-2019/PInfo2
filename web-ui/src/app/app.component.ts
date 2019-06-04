import { Component, OnInit } from '@angular/core';
import { UserService, KeycloakService } from './core';
import { KeycloakInstance } from 'keycloak-js';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  title = 'web-ui';
  public keycloakAuth: KeycloakInstance;

  constructor(
    private userService: UserService,
    public keycloak: KeycloakService
  ) {}

  ngOnInit() {
    this.keycloakAuth = this.keycloak.getKeycloakAuth();
    if (this.keycloak.isLoggedIn() === false) {
      this.keycloak.login();
      console.log("connexion essaye")
    }
    if (this.keycloak.isLoggedIn() == true) {
      console.log("connecter");
      console.log(this.keycloakAuth);
    }
  }                                               
}
