
import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ProfileAdsComponent } from './profile-ads.component';
import { ProfileComponent } from './profile.component';
import { ProfileResolver } from './profile-resolver.service';
import { SharedModule } from '../shared';
import { ProfileRoutingModule } from './profile-routing.module';
import { CommonModule } from '@angular/common';

@NgModule({
    imports: [
        SharedModule,
        ProfileRoutingModule,
        CommonModule
    ],
    declarations: [
        ProfileAdsComponent,
        ProfileComponent
    ],
    providers: [
        ProfileResolver
    ]
})
export class ProfileModule { }