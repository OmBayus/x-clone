import { Component, Input, effect } from '@angular/core';
import { Router } from '@angular/router';
import { FollowService } from 'src/app/follow.service';
import { User } from 'src/app/models/user.interface';

@Component({
  selector: 'app-follow-user',
  templateUrl: './follow-user.component.html',
  styleUrls: ['./follow-user.component.scss'],
})
export class FollowUserComponent {
  @Input() user!: User;
  hovered = false;
  isFollowing = false;

  constructor(private router: Router, private followService: FollowService) {
    effect(async () => {
      this.followService.currentUserId();
      if (this.user && this.user.username) {
        this.isFollowing = await this.followService.isFollowing(
          this.user.username
        );
      }
    });
  }

  async ngOnInit() {
    if (this.user) {
      this.isFollowing = await this.followService.isFollowing(
        this.user.username
      );
    }
  }

  navigateToUser(username: string) {
    this.router.navigate([`/${username}`]);
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
}
