import { User } from '../models/User.model';
import { Subject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable()
export class UserService {
  usersSubject = new Subject<any[]>();
  private users=[];




  httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json',


    }),
};

  constructor(private httpClient: HttpClient){}

  userSubject = new Subject<User[]>();

  emitUsers() {
    this.userSubject.next(this.users.slice());
  }

  addUser(user: User) {
    this.users.push(user);
    console.log(user.firstName);
    this.httpClient
    .post('http://localhost/ad',
    this.users).subscribe(
      () => {
          console.log('User enregistré! '+this.users);
      },
      (error) => {
          console.log('Erreur lors de l enregistrement : '+ error);
      }

    )
    this.emitUsers();
  }



}
