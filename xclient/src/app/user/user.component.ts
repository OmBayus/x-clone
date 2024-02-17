import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { PostService } from '../post.service';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent {
  posts: any[] = [];
  user: any = {
    username: '',
    name: '',
    birthDate: '',
    bio: '',
    postCount: 0,
  };
  constructor(
    private authService: AuthService,
    private postService: PostService,
    private userService: UserService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe((params) => {
      console.log(params['id']);
      this.getUser(params['id']);
    });
  }

  async getUser(username: string) {
    const _user = await this.userService.getUser(username);
    console.log(_user);
    this.user = {
      name: _user.name,
      username: _user.username,
      bio: _user.bio,
      birthDate: _user.birthDate,
      postCount: this.posts.length,
    };
    await this.getPosts(username);
  }

  async getPosts(username: string) {
    this.posts = await this.postService.getUserPosts(username);
    this.user.postCount = this.posts.length;
  }
}
