import { Injectable } from '@angular/core';
import { HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiService } from './api.service';
import { Ad, AdQuery } from '../models';
import { UserService } from './user.service';

@Injectable()
export class AdService {
    constructor(
        private apiService: ApiService,
        private userService: UserService
    ) { }

    query(config: AdQuery): Observable<Ad[]> {
        const params = {};
        Object.keys(config.filters)
            .forEach((key) => {
                params[key] = config.filters[key];
        });
        params['q'] = config.query;

        return this.apiService.get('/search/ad', new HttpParams({fromObject: params}));
    }

    get(id): Observable<Ad> {
        return this.apiService.get('/ad/' + id);
    }

    getAll(): Observable<Ad[]> {
        return this.apiService.get('/ad/');
    }

    destroy(id) {
        return this.apiService.delete('/ad/' + id);
    }

    save(ad): Observable<any> {
        let headers =  new HttpHeaders({
            'Content-Type': 'application/json',
        });
        // If we're updating an existing ad
        if (ad.id) {
            return this.apiService.put('/ad/' + ad.id, ad, headers);
        // Otherwise, create a new ad
        } else {
            ad.userId = this.userService.getCurrentUserId();
            return this.apiService.post('/ad/', ad, headers);
        }
    }

}