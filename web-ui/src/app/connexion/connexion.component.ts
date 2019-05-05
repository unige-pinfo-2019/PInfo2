import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss']
})
export class ConnexionComponent implements OnInit {

  constructor(private authService:AuthService,
              private router:Router) { }

  ngOnInit() {
    
  }
  onSignIn(){
    console.log("Signing in");
    
    this.authService.signIn();
    this.router.navigate(['/user-profile']);
  }

}
