
import { Injectable } from '@angular/core';
import { BehaviorSubject, ReplaySubject, Observable } from 'rxjs';

import { User } from '../models';
import { environment } from '../../../environments/environment';

import { distinctUntilChanged } from 'rxjs/operators';
import { KeycloakService } from './keycloak.service';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class UserService {
    private currentUserSubject = new BehaviorSubject<User>({} as User);
    public currentUser = this.currentUserSubject.asObservable().pipe(distinctUntilChanged());

    private isAuthenticatedSubject = new ReplaySubject<boolean>(1);
    public isAuthenticated = this.isAuthenticatedSubject.asObservable();

    constructor(
        private authService: KeycloakService,
        private http: HttpClient
    ) {}

    populate() {
        if (this.authService.isLoggedIn()) {
            this.authService.getKeycloakAuth().loadUserProfile().success(
                data => this.setUser(data)
            );
        } else {
            // Set current user to an empty object
            this.currentUserSubject.next({} as User);
            // Set auth status to false
            this.isAuthenticatedSubject.next(false);
        }
    }

    setUser(user: User) {
        // Set current user data into observable
        this.currentUserSubject.next(user);
        // Set isAuthenticated to true
        this.isAuthenticatedSubject.next(true);
    }

    purgeUser() {
        this.authService.logout();
        // Set current user to an empty object
        this.currentUserSubject.next({} as User);
        // Set auth status to false
        this.isAuthenticatedSubject.next(false);
    }

    getCurrentUser(): User {
        return this.currentUserSubject.value;
    }

    getCurrentUserId(): string {
        return this.authService.getKeycloakAuth().subject;
    }

    getUser(id: string): Observable<User> {
        return this.http.get<User>(`${environment.keycloak.url}/admin/realms/${environment.keycloak.realm}/users/${id}`);
    }

}