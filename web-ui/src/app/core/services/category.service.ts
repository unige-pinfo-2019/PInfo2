import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ApiService } from './api.service';
import { Category } from '../models/';

@Injectable()
export class CategoryService {
    constructor(
        private apiService: ApiService
    ) { }

    get(id): Observable<Category> {
        return this.apiService.get('/category/' + id);
    }

    getChildren(id): Observable<Category> {
        return this.apiService.get('/category/' + id + '/children/');
    }

    getAll(): Observable<Category[]> {
        return this.apiService.get('/category/');
    }

    getAllParent(): Observable<Category[]> {
        return this.getAll().pipe(
            map(data => data.filter(data => data.parentId === null)
        ));
    }

}