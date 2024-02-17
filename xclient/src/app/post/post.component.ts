import { Component, Input } from '@angular/core';
import { PostService } from '../post.service';
import { Router } from '@angular/router';
import { LikeService } from '../like.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent {
  @Input() post:any;
  likes: any[] = [];
  isLiked: boolean = false;

  constructor(private authService:AuthService,private likeService: LikeService,private router:Router) {}

  navigateToUser(username: string) {
    this.router.navigate([`/${username}`]);
  }

  ngOnInit() {
    this.getLikes();
  }

  async like() {
    await this.likeService.like(this.post.id,() => {
      this.getLikes();
    });
  }

  async getLikes() {
    const likeResponse = await this.likeService.getLikes(this.post.id);
    this.likes = likeResponse.payload;
    this.post.likes = likeResponse.message;
    this.isLiked = this.likes.some((like) => like === this.authService.currentUser()?.username);
    
  }


}
