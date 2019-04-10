import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PostComponent } from './post/post.component';
import {Routes} from '@angular/router';
import {RouterModule} from '@angular/router';
import { AuthGuard} from './services/auth-guard.service';


import {AuthService} from './services/auth.service';
import{PostsService} from './services/posts.service';
import { FourOhFourComponent } from './four-oh-four/four-oh-four.component';
import { PostViewComponent } from './post-view/post-view.component';
import { NewPostComponent } from './new-post/new-post.component';
import { MenuComponent } from './menu/menu.component';
import { NewUserComponent } from './new-user/new-user.component';
import { UserService } from './services/user.service';


const appRoutes: Routes = [

  {path: 'posts', component: PostViewComponent},
  /*{path: 'users', component: UserListComponent},*/
  {path: 'new-post', component: NewPostComponent },
  {path: 'new-user', component: NewUserComponent },
  {path: '', component: PostViewComponent},
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
    NewUserComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
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
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
