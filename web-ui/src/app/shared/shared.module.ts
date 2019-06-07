import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AdListComponent } from './ad-helpers/ad-list.component';
import { AdPreviewComponent } from './ad-helpers/ad-preview.component';

import { RouterModule } from '@angular/router';
import { ShowAuthedDirective } from './show-authed.directive';
import { ListErrorsComponent } from './list-errors.component';

@NgModule({
  declarations: [
    AdListComponent, 
    AdPreviewComponent,
    ShowAuthedDirective,
    ListErrorsComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    HttpClientModule
  ],
  exports: [
    AdListComponent,
    AdPreviewComponent,
    ShowAuthedDirective,
    ListErrorsComponent
  ]
})

export class SharedModule { }
