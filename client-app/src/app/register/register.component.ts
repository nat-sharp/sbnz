import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  hide = true;
  registerForm: FormGroup;
  RESPONSE_OK: number = 0;
  RESPONSE_ERROR: number = -1;

  constructor(private router: Router, private formBuilder: FormBuilder, private service: StudentService, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      username: ['', [Validators.required]],
      password: ['', [Validators.required]],
      concentratedStudyHours: ['true', [Validators.required]],
      category: ['', [Validators.required]]
    });
  }

  openSnackBar(msg: string, responseCode: number) {
    this.snackBar.open(msg, 'x', {
      duration: responseCode === this.RESPONSE_OK ? 3000 : 20000,
      verticalPosition: 'top',
      panelClass: 'panel-snackbar'
    });
  }

  register() {
    this.service.register(this.registerForm.value).subscribe(
      data => {
        this.openSnackBar(data, this.RESPONSE_OK);
        this.router.navigate(['/student']);
        sessionStorage.setItem('username', this.registerForm.value.username);
      },
      error => {
        this.openSnackBar(error.error, this.RESPONSE_ERROR);
      });
  }

  back() {
    this.router.navigate(['/loginStudent']);
  }
}
