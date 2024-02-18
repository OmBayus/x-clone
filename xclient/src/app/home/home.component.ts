import { Component } from '@angular/core';
import { PostService } from '../post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  postContent: string = '';
  posts: any[] = [];

  constructor(private postService: PostService,private router:Router) {}
  auto_grow(event: any) {
    event.target.style.height = '5px';
    event.target.style.height = event.target.scrollHeight + 'px';
  }

  ngOnInit() {
    this.getPosts();
  }

  async getPosts() {
    this.posts = await this.postService.getPosts();
    this.posts = this.posts.reverse();
  }

  post() {
    this.postService.post(this.postContent, () => {
      this.postContent = '';
      this.getPosts();
    });
  }

  navigateToUser(username: string) {
    this.router.navigate([`/${username}`]);
  }
}
