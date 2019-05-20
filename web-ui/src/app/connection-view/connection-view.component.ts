import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connexion',
  templateUrl: './connection-view.component.html',
  styleUrls: ['./connection-view.component.scss']
})

export class ConnectionViewComponent implements OnInit {

  constructor(private authService:AuthService, private router:Router) {

  }
  
  ngOnInit() {

  }

  onSignIn(){
    console.log("Signing in");
    this.authService.signIn();
    this.router.navigate(['/user-profile']);
  }
}
