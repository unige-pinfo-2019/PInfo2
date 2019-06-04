import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ApiService } from './api.service';
import { Ad, AdQuery } from '../models';
import { map } from 'rxjs/operators';

@Injectable()
export class AdService {
    constructor(
        private apiService: ApiService
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

    save(ad): Observable<Ad> {
        // If we're updating an existing ad
        if (ad.id) {
            return this.apiService.put('/ad/' + ad.id, { ad: ad })
                .pipe(map(data => data.ad));
        // Otherwise, create a new ad
        } else {
            return this.apiService.post('/ad/', { ad: ad })
                .pipe(map(data => data.ad));
        }
    }

}