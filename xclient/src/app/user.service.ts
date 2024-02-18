import { Injectable, signal } from '@angular/core';
import { User } from './models/user.interface';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(private http: HttpClient) {}

  getUser(username: string): Promise<User> {
    return new Promise((resolve) => {
      this.http
        .get<any>(`http://localhost:8080/app/v1/user/${username}`)
        .subscribe((response) => {
          resolve(response);
        });
    });
  }

  suggestion(): Promise<any> {
    return new Promise((resolve) => {
      this.http
        .get<any>('http://localhost:8080/app/v1/user/suggestion')
        .subscribe((response) => {
          resolve(response.payload);
        });
    });
  }

  stats(username: string): Promise<any> {
    return new Promise((resolve) => {
      this.http
        .get<any>(`http://localhost:8080/app/v1/user/stats/${username}`)
        .subscribe((response) => {
          resolve(response);
        });
    });
  }
}
