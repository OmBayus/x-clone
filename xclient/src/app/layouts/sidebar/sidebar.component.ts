import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/auth.service';
@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.scss'],
})
export class SidebarComponent {
  navigationList = [
    {
      name: 'Home',
      route: '/home',
      icon: 'home',
      disabled: false,
    },
    {
      name: 'Explore',
      route: '/explore',
      icon: 'explore',
      disabled: true,
    },
    {
      name: 'Notifications',
      route: '/notifications',
      icon: 'notifications',
      disabled: true,
    },
    {
      name: 'Messages',
      route: '/messages',
      icon: 'message',
      disabled: true,
    },
    {
      name: 'Profile',
      route: '/user',
      icon: 'person',
      disabled: false,
    },
    {
      name: 'More',
      route: '/more',
      icon: 'more_horiz',
      disabled: true,
    },
  ];

  profile_content_open = false;

  constructor(private authService: AuthService, private router: Router) {}

  toggleProfileContent() {
    this.profile_content_open = !this.profile_content_open;
  }

  navigateTo(route: string) {
    if (route === '/user') {
      this.router.navigateByUrl('/' + this.authService.currentUser()?.name);
    } else {
      this.router.navigateByUrl(route);
    }
  }

  logout() {
    this.toggleProfileContent();
    this.authService.logout();
  }
}
