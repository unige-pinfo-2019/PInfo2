import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { APP_BASE_HREF } from '@angular/common';
import { KeycloakInterceptorService } from './interceptors/keycloak.interceptor.service'

import { ApiService, AdService, CategoryService, KeycloakService, UserService } from './services';

@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ],
  providers: [
    ApiService,
    AdService,
    UserService,
    CategoryService,
    KeycloakService,
    { 
      provide: HTTP_INTERCEPTORS, 
      useClass: KeycloakInterceptorService, 
      multi: true 
    },
    { 
      provide: APP_BASE_HREF, 
      useValue: '/' 
    }
  ]
})
export class CoreModule { }
