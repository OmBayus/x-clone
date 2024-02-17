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

  async isAuthenticated(): Promise<boolean> {
    if (this.currentUser()) {
      return true;
    }
    const token = this.cookieService.get('token');
    if (token) {
      await this.authenticate();
    }
    if (this.currentUser()) {
      return true;
    }
    return false;
  }

  async authenticate(): Promise<void> {
    return new Promise((resolve, reject) => {
      const token = this.cookieService.get('token');
      if (token) {
        this.http
          .get<any>('http://localhost:8080/app/v1/auth/', {
            headers: {
              Authorization: `${token}`,
            },
          })
          .subscribe((response) => {
            this.setUser({
              name: response.name,
              email: response.email,
              username: response.username,
              birthDate: response.birthDate,
              bio: response.bio,
              token,
            });
            resolve();
          });
      } else {
        resolve();
        this.currentUser.set(null);
      }
    });
  }

  setUser(user: User): void {
    this.currentUser.set(user);
    this.cookieService.set('token', user.token, 1);
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
        this.setUser({
          name: response.user.name,
          email: response.user.email,
          username: response.user.username,
          birthDate: response.user.birthDate,
          bio: response.user.bio,
          token: response.access_token,
        });
        this.router.navigateByUrl('/home');
        callback();
      });
  }

  login(username: string, password: string, callback = () => {}): void {
    this.http
      .post<any>('http://localhost:8080/app/v1/auth/login', {
        username,
        password,
      })
      .subscribe((response) => {
        this.setUser({
          name: response.user.name,
          email: response.user.email,
          username: response.user.username,
          birthDate: response.user.birthDate,
          bio: response.user.bio,
          token: response.access_token,
        });
        this.router.navigateByUrl('/home');
        callback();
      });
  }

  logout(): void {
    this.currentUser.set(null);
    this.cookieService.delete('token');
    this.router.navigateByUrl('/');
  }
}
