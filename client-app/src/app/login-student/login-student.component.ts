import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-login-student',
  templateUrl: './login-student.component.html',
  styleUrls: ['./login-student.component.css']
})
export class LoginStudentComponent implements OnInit {
  hide = true;
  loginForm: FormGroup;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;

  constructor(private router: Router, private formBuilder: FormBuilder, private service: StudentService, private snackBar: MatSnackBar) { }

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
        this.router.navigate(['/student']);
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
