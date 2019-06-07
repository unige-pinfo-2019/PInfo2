import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { map, filter } from 'rxjs/operators';

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

    getFormatedCategories(): Observable<any[]> {
        return this.getAll().pipe(
            map(data => {
                var formatedArray = [];
                let parents = data.filter(elem => elem.parentId === null);
                for (let parent of parents) {
                    let currItems = data.filter(
                        elem => elem.parentId == parent.id
                        ).map(
                            children => {
                                return {
                                    id: children.id,
                                    value: children.id,
                                    text: children.name
                                }
                            }
                        );
                    formatedArray.push({
                        group: parent.name,
                        items: currItems
                    });
                }
                return formatedArray;
            }
        ));
    }

}