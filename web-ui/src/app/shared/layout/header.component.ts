import { Component, OnInit } from '@angular/core';
import { UserService, User, KeycloakService } from 'src/app/core';

@Component({
  selector: 'app-layout-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent implements OnInit {
  constructor(
    private userService: UserService,
    private authService: KeycloakService
  ) {}
  
  currentUser: User;

  ngOnInit() {
    this.userService.currentUser.subscribe(
      (userData) => {
        this.currentUser = userData;
      }
    );
  }

  onClickSettings() {
    this.authService.settingsPage();
  }
}

