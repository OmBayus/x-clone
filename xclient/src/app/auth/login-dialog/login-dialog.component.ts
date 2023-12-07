import { Component,ViewEncapsulation } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class LoginDialogComponent {

  email: string = '';
  password: string = '';

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>) { }


  closeDialog(): void {
    this.dialogRef.close();
  }

}
