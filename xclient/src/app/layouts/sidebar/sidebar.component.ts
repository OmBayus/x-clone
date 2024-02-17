import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss']
})
export class SidebarComponent {
  navigationList = [
    {
      name: 'Home',
      route: '/home',
      icon: 'home'
    },
    {
      name: 'Explore',
      route: '/explore',
      icon: 'explore'
    },
    {
      name: "Notifications",
      route: '/notifications',
      icon: 'notifications'
    },
    {
      name: 'Messages',
      route: '/messages',
      icon: 'message'
    },
    {
      name: 'Profile',
      route: '/profile',
      icon: 'person'
    },
    {
      name: 'More',
      route: '/more',
      icon: 'more_horiz'
    }
  ]

  profile_content_open = false;

  constructor(private authService:AuthService) { }

  toggleProfileContent() {
    this.profile_content_open = !this.profile_content_open;
  }

  logout() {
    this.toggleProfileContent();
    this.authService.logout();
  }
}
