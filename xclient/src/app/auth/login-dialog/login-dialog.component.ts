import { Component, ViewEncapsulation } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AuthService } from 'src/app/auth.service';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginDialogComponent {
  username: string = '';
  password: string = '';

  constructor(
    public dialogRef: MatDialogRef<LoginDialogComponent>,
    private authService: AuthService
  ) {}

  login(): void {
    if (!this.username || !this.password) {
      return;
    }
    this.authService.login(this.username, this.password, () =>
      this.dialogRef.close()
    );
  }

  closeDialog(): void {
    this.dialogRef.close();
  }
}
