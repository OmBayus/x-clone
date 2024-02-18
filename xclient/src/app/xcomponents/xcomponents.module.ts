import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { XbuttonComponent } from './xbutton/xbutton.component';
import { MatButtonModule } from '@angular/material/button';



@NgModule({
  declarations: [
    XbuttonComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
  ],
  exports: [
    XbuttonComponent
  ]
})
export class XcomponentsModule { }
