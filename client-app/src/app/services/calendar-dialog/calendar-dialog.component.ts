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
  }


  close() {
    this.dialogRef.close();
  }
}
