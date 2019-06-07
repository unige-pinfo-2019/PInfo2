
import { ModuleWithProviders, NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { EditorComponent } from './editor.component';
import { EditableAdResolver } from './editable-ad-resolver.service';
import { AuthGuard } from '../core';
import { SharedModule } from '../shared';
import { EditorRoutingModule } from './editor-routing.module';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

import { ImageUploaderModule } from 'ngx-image-uploader';
import { DropdownListModule } from 'ngx-dropdown-list';

@NgModule({
    imports: [
        SharedModule, 
        EditorRoutingModule,
        ReactiveFormsModule,
        CommonModule,
        ImageUploaderModule,
        DropdownListModule
    ],
    declarations: [EditorComponent],
    providers: [EditableAdResolver]
})
export class EditorModule { }