import { Component, ElementRef, ViewChild } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
  auto_grow(event: any) {
    event.target.style.height = '5px';
    event.target.style.height = event.target.scrollHeight + 'px';
  }
}
