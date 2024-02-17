import { Injectable, signal } from '@angular/core';
import { User } from './models/user.interface';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  currentUser = signal<User | undefined | null>(undefined);

  constructor(
    private router: Router,
    private cookieService: CookieService,
    private http: HttpClient
  ) {
    // const token = localStorage.getItem('token');
    // if (token) {
    //   this.currentUser.set({ token });
    // }
  }

  register(
    name: string,
    email: string,
    username: string,
    birthdate: string,
    bio: string,
    password: string,
    callback = () => {}
  ): void {
    this.http
      .post<any>('http://localhost:8080/app/v1/auth/register', {
        name,
        username,
        password,
        email,
        bio,
        birthDate: birthdate,
      })
      .subscribe((response) => {
        console.log(response);
        this.setUser({
          name,
          email,
          username,
          birthDate: birthdate,
          bio,
          token: response!.access_token,
        });
        this.router.navigateByUrl('/home');
        callback();
      });
  }

  setUser(user: User): void {
    this.currentUser.set(user);
    this.cookieService.set('token', user.token, 1);
  }

  login(user: User): void {
    this.currentUser.set(user);
    this.cookieService.set('token', user.token, 1);
    this.router.navigateByUrl('/');
  }

  logout(): void {
    this.currentUser.set(null);
    this.cookieService.delete('token');
    this.router.navigateByUrl('/');
  }
}
