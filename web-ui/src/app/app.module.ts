import { CommonModule } from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PostComponent } from './post/post.component';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './services/auth-guard.service';

import { AuthService } from './services/auth.service';
import { PostsService } from './services/posts.service';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { PostViewComponent } from './post-view/post-view.component';
import { NewPostComponent } from './new-post/new-post.component';
import { MenuComponent } from './menu/menu.component';
import { NewUserComponent } from './new-user/new-user.component';
import { UserService } from './services/user.service';
import { SinglePostViewComponent } from './single-post-view/single-post-view.component';
import { CategoryMenuComponent } from './category-menu/category-menu.component';
import { CategoryService } from './services/category.service';
import { ConnexionComponent } from './connexion/connexion.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

import * as $ from "jquery";
import { AddCategoryComponent } from './add-category/add-category.component';

const appRoutes: Routes = [

  {path: 'posts', component: PostViewComponent},
  {path: 'posts/:id', component: SinglePostViewComponent},
  {path: 'new-post', component: NewPostComponent },
  {path: 'add-category', component: AddCategoryComponent },
  {path: 'connexion', component: ConnexionComponent },
  {path: 'new-user', component: NewUserComponent },
  {path: 'user-profile',canActivate:[AuthGuard], component: UserProfileComponent },
  {path: '', redirectTo: 'posts',pathMatch:'full'},
  {path: 'not-found', component:FourOhFourComponent},
  {path: '**', redirectTo: 'not-found'}
];
@NgModule({
  declarations: [
    AppComponent,
    PostComponent,
    FourOhFourComponent,
    PostComponent,
    PostViewComponent,
    NewPostComponent,
    MenuComponent,
    NewUserComponent,
    SinglePostViewComponent,
    CategoryMenuComponent,
    ConnexionComponent,
    UserProfileComponent,
    AddCategoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
  ],
  providers: [
    PostComponent,
    AuthService,
    AuthGuard,
    PostsService,
    UserService,
    CategoryService,
    KeycloakService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
