import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from 'src/app/user.service';

@Component({
  selector: 'app-follow-list',
  templateUrl: './follow-list.component.html',
  styleUrls: ['./follow-list.component.scss']
})
export class FollowListComponent {

  users: any[] = [];

  constructor(private userService: UserService,private router:Router) {}

  async ngOnInit() {
    this.users = await this.userService.suggestion();
  }

  navigateToUser(username: string) {
    this.router.navigate([`/${username}`]);
  }
  
}
