import { NgModule } from '@angular/core';

import { AdComponent } from './ad.component';
import { AdResolver } from './ad-resolver.service';
import { SharedModule } from '../shared';
import { AdRoutingModule } from './ad-routing.module';
import { CommonModule } from '@angular/common';

@NgModule({
    imports: [
        SharedModule,
        AdRoutingModule,
        CommonModule
    ],
    declarations: [
        AdComponent
    ],
    providers: [
        AdResolver
    ]
})
export class AdModule { }