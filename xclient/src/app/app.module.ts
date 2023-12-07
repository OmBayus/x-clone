import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingModule } from './landing/landing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginDialogComponent } from './auth/login-dialog/login-dialog.component';
import { MatIconModule } from '@angular/material/icon';

@NgModule({
  declarations: [AppComponent, LoginDialogComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    LandingModule,
    BrowserAnimationsModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
