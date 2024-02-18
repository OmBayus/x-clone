import { Component, ViewEncapsulation } from '@angular/core';
import { LoginDialogComponent } from 'src/app/auth/login-dialog/login-dialog.component';
import { MatDialog } from '@angular/material/dialog';
import { RegisterDialogComponent } from 'src/app/auth/register-dialog/register-dialog.component';

@Component({
  selector: 'app-landing-auth',
  templateUrl: './landing-auth.component.html',
  styleUrls: ['./landing-auth.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LandingAuthComponent {
  constructor(public dialog: MatDialog) {}

  openSignInDialog() {
    console.log('open sign in dialog');
    const dialogRef = this.dialog.open(LoginDialogComponent, {
      width: '585px',
      height: '90vh',
      panelClass: 'custom-dialog-container',
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log(`Dialog result: ${result}`);
    
    });
  }

  openSignUpDialog() {
    console.log('open sign in dialog');
    const dialogRef = this.dialog.open(RegisterDialogComponent, {
      width: '585px',
      height: '90vh',
      panelClass: 'custom-dialog-container',
    });

    dialogRef.afterClosed().subscribe((result:any) => {
      console.log(`Dialog result: ${result}`);
    
    });
  }
}
