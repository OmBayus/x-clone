import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class LikeService {
  constructor(private http: HttpClient) {}

  like(postId: string, callback = () => {}) {
    this.http
      .post<any>('http://localhost:8080/app/v1/like/' + postId, {})
      .subscribe(() => {
        callback();
      });
  }

  async getLikes(postId: string): Promise<any> {
    return new Promise((resolve) => {
      this.http
        .get<any>('http://localhost:8080/app/v1/like/' + postId)
        .subscribe((response) => {
          resolve(response);
        });
    });
  }

  async getLikedPosts(username: string): Promise<any> {
    return new Promise((resolve) => {
      this.http
        .get<any>('http://localhost:8080/app/v1/like/all/' + username)
        .subscribe((response) => {
          resolve(response.payload);
        });
    });
  }
}
