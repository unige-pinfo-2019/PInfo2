import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {
  FooterComponent,
  HeaderComponent,
  SharedModule
} from './shared';
import { HomeComponent } from './home/home.component';
import { CoreModule } from './core/core.module'
import { AuthModule } from './auth/auth.module';

import { DropdownListModule } from 'ngx-dropdown-list';

@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    HomeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    SharedModule,
    CoreModule,
    FormsModule,
    AuthModule,
    ReactiveFormsModule,
    DropdownListModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
