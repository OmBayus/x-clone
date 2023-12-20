import { Component,ViewEncapsulation } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class RegisterDialogComponent {

  name: string = '';
  email: string = '';
  username: string = '';
  birthdate: Date | null = null;
  password: string = '';

  constructor(public dialogRef: MatDialogRef<RegisterDialogComponent>) { }

  changeDate(event: any): void {
    console.log(event.value);
    this.birthdate = event.value;
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}
