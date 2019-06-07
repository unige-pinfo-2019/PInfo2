import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs';

import { Ad, AdService, UserService } from '../core';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class EditableAdResolver implements Resolve<Ad> {
    constructor(
        private adService: AdService,
        private router: Router,
        private userService: UserService
    ) { }

    resolve(
        route: ActivatedRouteSnapshot,
        state: RouterStateSnapshot
    ): Observable<any> {

        return this.adService.get(route.params['id'])
            .pipe(
                map(
                    ad => {
                        if (this.userService.getCurrentUserId() === ad.userId) {
                            return ad;
                        } else {
                            this.router.navigateByUrl('/');
                        }
                    }
                ),
                catchError((err) => this.router.navigateByUrl('/'))
            );
    }
}