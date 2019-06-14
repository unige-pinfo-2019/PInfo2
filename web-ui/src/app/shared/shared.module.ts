import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

import { AdListComponent } from './ad-helpers/ad-list.component';
import { AdPreviewComponent } from './ad-helpers/ad-preview.component';
import { AdMiniListComponent } from './ad-helpers/ad-minilist.component';
import { AdMiniPreviewComponent } from './ad-helpers/ad-minipreview.component';

import { RouterModule } from '@angular/router';
import { ShowAuthedDirective } from './show-authed.directive';
import { ListErrorsComponent } from './list-errors.component';

@NgModule({
  declarations: [
    AdListComponent,
    AdPreviewComponent,
    AdMiniListComponent,
    AdMiniPreviewComponent,
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
    AdMiniListComponent,
    AdMiniPreviewComponent,
    ShowAuthedDirective,
    ListErrorsComponent
  ]
})

export class SharedModule { }
