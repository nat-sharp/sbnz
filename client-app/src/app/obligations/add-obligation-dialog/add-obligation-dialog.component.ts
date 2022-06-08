import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-obligation-dialog',
  templateUrl: './add-obligation-dialog.component.html',
  styleUrls: ['./add-obligation-dialog.component.css']
})
export class AddObligationDialogComponent implements OnInit {
  form: FormGroup;

  constructor(public dialogRef: MatDialogRef<AddObligationDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      name: ['', [Validators.required]],
      obligationType: ['', [Validators.required]],
      dateAndTime: ['', [Validators.required, this.dateValidator]],
      studyStartDate: ['', [Validators.required, this.dateValidator]],
      studyEndDate: ['', [Validators.required, this.dateValidator]],
      studyHours: ['', [Validators.required]],
      priority: ['', [Validators.required]],
      maxPoints: ['', [Validators.required]],
      corrigible: ['', [Validators.required]]
    });
  }

  dateValidator(formControl: FormControl) {
    let today = new Date();
    let chosen = new Date(formControl.value);

    if (chosen < today) {
      return { dateValidator: { valid: false } };
    }
    return null;
  }

  close() {
    this.dialogRef.close();
  }
}
