import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login-admin',
  templateUrl: './login-admin.component.html',
  styleUrls: ['./login-admin.component.css']
})
export class LoginAdminComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;

  constructor(private router: Router, private formBuilder: FormBuilder, private service: AdminService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: 'top',
      panelClass: 'panel-snackbar'
    });
  }

  login() {
    this.service.login(this.loginForm.value).subscribe(
      data => {
        this.openSnackBar(data, this.RESPONSE_OK);
        this.router.navigate(['/admin']);
        sessionStorage.setItem('username', this.loginForm.value.username);
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      });
  }

  back() {
    this.router.navigate(['']);
  }
}
