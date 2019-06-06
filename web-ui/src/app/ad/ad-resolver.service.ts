import { Injectable, } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Ad, AdService } from '../core';
import { catchError, take } from 'rxjs/operators';

@Injectable()
export class AdResolver implements Resolve<Ad> {
    constructor(
        private adService: AdService,
        private router: Router
    ) { }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<any> {
        return this.adService.get(route.params['id'])
            .pipe(catchError((err) => this.router.navigateByUrl('/')));
    }
}