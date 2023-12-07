import { Component } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent {

  constructor(public dialogRef: MatDialogRef<LoginDialogComponent>) { }


  closeDialog(): void {
    this.dialogRef.close();
  }

}
