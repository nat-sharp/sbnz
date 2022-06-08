import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-finish-obligation-dialog',
  templateUrl: './finish-obligation-dialog.component.html',
  styleUrls: ['./finish-obligation-dialog.component.css']
})
export class FinishObligationDialogComponent implements OnInit {
  
  constructor(public dialogRef: MatDialogRef<FinishObligationDialogComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
  }

  close() {
    this.dialogRef.close();
  }
}
