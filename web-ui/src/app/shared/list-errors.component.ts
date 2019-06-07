import { Component, Input } from '@angular/core';

import { Errors } from '../core';

@Component({
    selector: 'app-list-errors',
    templateUrl: './list-errors.component.html',
    styleUrls: ['./list-errors.component.scss']
})
export class ListErrorsComponent {
    formattedErrors: Array<string> = [];

    @Input()
    set errors(errorList: Errors) {
        this.formattedErrors = Object.keys(errorList.errors || {})
            .map(key => `${key} ${errorList.errors[key]}`);
    }

    get errorList() { return this.formattedErrors; }


}