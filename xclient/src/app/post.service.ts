import { Injectable, signal } from '@angular/core';
import { User } from './models/user.interface';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  constructor(
    private cookieService: CookieService,
    private http: HttpClient
  ) {}


  post(content: string,callback=()=>{}) {
    const token = this.cookieService.get('token');
    if (token) {
      this.http
        .post<any>('http://localhost:8080/app/v1/posting/', {
          text:content,
        })
        .subscribe(() => {
          callback();
        });
    }
  }

  async getPosts(): Promise<any>{
    return new Promise((resolve) => {
      const token = this.cookieService.get('token');
      if (token) {
        this.http
          .get<any>('http://localhost:8080/app/v1/posting/all')
          .subscribe((response) => {
            resolve(response.payload);
          });
      } else {
        resolve([]);
      }
    });
  }

  async getUserPosts(username:string):Promise<any> {
    return new Promise((resolve) => {
      const token = this.cookieService.get('token');
      if (token) {
        this.http
          .get<any>(`http://localhost:8080/app/v1/posting/all/${username}`)
          .subscribe((response) => {
            resolve(response.payload);
          });
      } else {
        resolve([]);
      }
    });
  }

}
