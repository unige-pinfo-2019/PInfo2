import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AdListComponent } from './ad-helpers/ad-list.component';
import { AdPreviewComponent } from './ad-helpers/ad-preview.component';

import { RouterModule } from '@angular/router';
import { ShowAuthedDirective } from './show-authed.directive';

@NgModule({
  declarations: [
    AdListComponent, 
    AdPreviewComponent,
    ShowAuthedDirective
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule
  ],
  exports: [
    AdListComponent,
    AdPreviewComponent,
    ShowAuthedDirective
  ]
})

export class SharedModule { }
