import { Component, OnInit } from '@angular/core';
import { KeycloakService } from '../services/keycloak/keycloak.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})

export class MenuComponent implements OnInit {

  appTitle: string = 'Agility';

  constructor(private authService: KeycloakService) {}

  ngOnInit() {
    if(this.authService.isLoggedIn()){
      var connexionButton= document.getElementById("connexionButton");
      connexionButton.style.color="black";
    }
  }
}
