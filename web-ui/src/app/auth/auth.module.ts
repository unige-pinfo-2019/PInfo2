import { NgModule } from '@angular/core';

import { SharedModule } from '../shared';
import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './login.component';
import { LogoutComponent } from './logout.component';
import { RegisterComponent } from './register.component';

@NgModule({
    imports: [
        SharedModule,
        AuthRoutingModule
    ],
    declarations: [
        LoginComponent,
        LogoutComponent,
        RegisterComponent
    ],
    providers: []
})
export class AuthModule { }