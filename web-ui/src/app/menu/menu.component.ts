import { Component, OnInit } from '@angular/core';
import { AuthGuard } from '../services/auth-guard.service';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  appTitle: string = 'Agility';
  
  constructor(private authService:AuthService) { }

  ngOnInit() {
    if(this.authService.isAuth){
      console.log("we're logged in!");
      var connexionButton= document.getElementById("connexionButton");
      connexionButton.style.color="black";
    }
  }

}
