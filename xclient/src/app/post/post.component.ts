import { Component, Input } from '@angular/core';
import { PostService } from '../post.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent {
  @Input() post:any;

  constructor(private postService: PostService,private router:Router) {}

  navigateToUser(username: string) {
    this.router.navigate([`/${username}`]);
  }


}
