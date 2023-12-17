import { Component,ViewEncapsulation } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterDialogComponent {

  email: string = '';
  password: string = '';

  constructor(public dialogRef: MatDialogRef<RegisterDialogComponent>) { }


  closeDialog(): void {
    this.dialogRef.close();
  }

}
