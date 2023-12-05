import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LandingComponent } from './landing.component';
import {MatGridListModule} from '@angular/material/grid-list';
import { LandingAuthComponent } from './landing-auth/landing-auth.component';
import { XcomponentsModule } from '../xcomponents/xcomponents.module';
import { LandingFooterComponent } from './landing-footer/landing-footer.component';



@NgModule({
  declarations: [
    LandingComponent,
    LandingAuthComponent,
    LandingFooterComponent,
  ],
  imports: [
    CommonModule,
    MatGridListModule,
    XcomponentsModule
  ],
  exports: [LandingComponent]
})
export class LandingModule { }
