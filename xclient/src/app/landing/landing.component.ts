import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.scss']
})
export class LandingComponent {


  constructor(private authService:AuthService,private router:Router) {}
  ngOnInit() {
    if (this.authService.currentUser()) {
      this.router.navigateByUrl('/home');
    }
  }
}
