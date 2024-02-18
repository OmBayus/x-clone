import { Component, ViewEncapsulation } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from '../../auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class RegisterDialogComponent {
  name: string = '';
  email: string = '';
  username: string = '';
  birthdate: any = null;
  bio: string = '';
  password: string = '';
  confirmpassword: string = '';

  constructor(
    public dialogRef: MatDialogRef<RegisterDialogComponent>,
    private authService: AuthService,
    private router: Router
  ) {}

  changeDate(event: any): void {
    this.birthdate = event.value;
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

  register(): void {
    if (this.confirmpassword !== this.password) {
      return;
    }
    if (
      !this.name ||
      !this.email ||
      !this.username ||
      !this.birthdate ||
      !this.password
    ) {
      return;
    }
    this.authService.register(
      this.name,
      this.email,
      this.username,
      this.birthdate.toDate(),
      this.bio,
      this.password,
      ()=>this.dialogRef.close()
    );
  }
}
