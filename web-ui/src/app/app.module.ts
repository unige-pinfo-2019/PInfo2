import { CommonModule } from '@angular/common';

import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { KeycloakService } from './services/keycloak/keycloak.service';
import { KeycloakInterceptorService } from './services/keycloak/keycloak.interceptor.service';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes } from '@angular/router';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './services/auth-guard.service';
// Services =======================================================================================
import { AuthService } from './services/auth.service';
import { CategoryService } from './services/category.service';
import { PostsService } from './services/posts.service';
import { UserService } from './services/user.service';
// Component ======================================================================================
import { AddCategoryComponent } from './add-category/add-category.component';
import { CategoriesComponent } from './categories/categories.component';
import { ConnectionViewComponent } from './connection-view/connection-view.component';
import { Error404ViewComponent } from './error-404-view/error-404-view.component';
import { MenuComponent } from './menu/menu.component';
import { MinipostItemComponent } from './minipost-item/minipost-item.component';
import { NewPostViewComponent } from './new-post-view/new-post-view.component';
import { PostItemComponent } from './post-item/post-item.component';
import { PasswordRecoveryViewComponent } from './password-recovery-view/password-recovery-view.component';
import { NewUserViewComponent } from './new-user-view/new-user-view.component';
import { PostsViewComponent } from './posts-view/posts-view.component';
import { SinglePostViewComponent } from './single-post-view/single-post-view.component';
import { UserViewComponent } from './user-view/user-view.component';

import * as $ from "jquery";

const appRoutes: Routes = [

  {path: 'posts-view', component: PostsViewComponent },
  {path: 'posts/:id', component: SinglePostViewComponent },
  {path: 'new-post-view', component: NewPostViewComponent },
  {path: 'add-category', component: AddCategoryComponent },
  {path: 'connection-view', component: ConnectionViewComponent },
  {path: 'new-user-view', component: NewUserViewComponent },
  {path: 'password-recovery-view', component: PasswordRecoveryViewComponent },
  {path: 'user-view', canActivate: [AuthGuard], component: UserViewComponent },
  {path: '', redirectTo: 'posts-view', pathMatch:'full'},
  {path: 'not-found', component: Error404ViewComponent},
  {path: '**', redirectTo: 'not-found'}
];
@NgModule({
  declarations: [
    AddCategoryComponent,
    AppComponent,
    CategoriesComponent,
    ConnectionViewComponent,
    Error404ViewComponent,
    MenuComponent,
    MinipostItemComponent,
    NewPostViewComponent,
    NewUserViewComponent,
    PasswordRecoveryViewComponent,
    PostItemComponent,
    PostsViewComponent,
    SinglePostViewComponent,
    UserViewComponent,
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
    PostItemComponent,
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
