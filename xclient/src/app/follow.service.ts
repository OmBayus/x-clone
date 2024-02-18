import { Injectable, signal } from '@angular/core';
import { User } from './models/user.interface';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class FollowService {
  currentUserId = signal<number | string | undefined | null>(undefined);
  constructor(private http: HttpClient) {}

  followUser(id: any, callback = () => {}) {
    this.http
      .post('http://localhost:8080/app/v1/follow/' + id, {})
      .subscribe((response) => {
        callback();
        this.currentUserId.set(null);
        this.currentUserId.set(id);
      });
  }

  unfollowUser(id: any, callback = () => {}) {
    this.http
      .delete('http://localhost:8080/app/v1/follow/unfollow?id=' + id)
      .subscribe((response) => {
        callback();
        this.currentUserId.set(null);
        this.currentUserId.set(id);
      });
  }

  async isFollowing(username: any): Promise<boolean> {
    return new Promise((resolve, reject) => {
      this.http
        .get<any>('http://localhost:8080/app/v1/follow/isFollows/' + username)
        .subscribe((response) => {
          resolve(response);
        })
        .add(() => {
          reject(false);
        });
    });
  }
}
