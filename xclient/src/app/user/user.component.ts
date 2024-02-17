import { Component, ViewChild, effect } from '@angular/core';
import { AuthService } from '../auth.service';
import { PostService } from '../post.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../user.service';
import { LikeService } from '../like.service';
import { FollowService } from '../follow.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserComponent {
  contentType: 'posts' | 'likes' = 'posts';
  hovered = false;
  posts: any[] = [];
  likedPosts: any[] = [];
  isFollowing = false;
  user: any = {
    id: '',
    username: '',
    name: '',
    birthDate: '',
    bio: '',
    postCount: 0,
  };
  constructor(
    private likeService: LikeService,
    private postService: PostService,
    private userService: UserService,
    private route: ActivatedRoute,
    public authService: AuthService,
    private followService: FollowService,
    private router: Router
  ) {
    this.route.params.subscribe((params) => {
      this.getUser(params['id']);
      this.contentType = 'posts';
    });
    effect(async () => {
      this.followService.currentUserId();
      if (this.user && this.user.username) {
        this.isFollowing = await this.followService.isFollowing(
          this.user.username
        );
      }
    });
  }

  async getUser(username: string) {
    const _user = await this.userService.getUser(username);
    this.user = {
      id: _user.id,
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
    this.likedPosts = await this.likeService.getLikedPosts(username);
    this.user.postCount = this.posts.length;
    if (username) {
      this.isFollowing = await this.followService.isFollowing(username);
    }
  }

  changeContentType(contentType: 'posts' | 'likes') {
    this.contentType = contentType;
  }

  onMouseEnterFollowing() {
    this.hovered = true;
  }

  onMouseLeaveFollowing() {
    this.hovered = false;
  }

  follow() {
    this.followService.followUser(this.user.id, () => {
      this.isFollowing = true;
    });
  }

  unfollow() {
    this.followService.unfollowUser(this.user.id, () => {
      this.isFollowing = false;
    });
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
