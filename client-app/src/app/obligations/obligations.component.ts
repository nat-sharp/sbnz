import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ObligationService } from '../services/obligation.service';
import { AddObligationDialogComponent } from './add-obligation-dialog/add-obligation-dialog.component';
import { FinishObligationDialogComponent } from './finish-obligation-dialog/finish-obligation-dialog.component';

export interface Obligation {
  id: number;
  name: string;
  dateAndTime: string;
  priority: number;
  obligationType: string;
  maxPoints: number;
  earnedPoints: number;
  skipped: boolean;
  passed: boolean;
  finished: boolean;
  corrigible: boolean;
}

export interface CreatedObligation {
  name: string;
  dateAndTime: Date;
  studyStartDate: Date;
  studyEndDate: Date;
  studyHours: number;
  priority: number;
  obligationType: string;
  maxPoints: number;
  corrigible: boolean;
  subjectId: number;
}

export interface FinishedObligation {
  id: number;
  skipped: boolean;
  earnedPoints: number;
}

const OBLIGATIONS: Obligation[] = [];

@Component({
  selector: 'app-obligations',
  templateUrl: './obligations.component.html',
  styleUrls: ['./obligations.component.css']
})
export class ObligationsComponent implements OnInit {
  columns = [
    {
      columnDef: 'name',
      header: 'Name',
      cell: (element: Obligation) => `${element.name}`,
    },
    {
      columnDef: 'type',
      header: 'Type',
      cell: (element: Obligation) => `${element.obligationType.charAt(0) + element.obligationType.toLowerCase().slice(1)}`,
    },
    {
      columnDef: 'priority',
      header: 'Priority',
      cell: (element: Obligation) => `${element.priority}`,
    },
    {
      columnDef: 'dateAndTime',
      header: 'Date & Time',
      cell: (element: Obligation) => `${element.dateAndTime == null ? '/' :
        element.dateAndTime.split('T')[0].split('-')[2] + '.' +
        element.dateAndTime.split('T')[0].split('-')[1] + '.' +
        element.dateAndTime.split('T')[0].split('-')[0] + '. ' +
        element.dateAndTime.split('T')[1].slice(0, -3)
        }`,
    },
    {
      columnDef: 'finished',
      header: 'Finished',
      cell: (element: Obligation) => `${element.finished ? 'Yes' : 'No'}`,
    },
    {
      columnDef: 'skipped',
      header: 'Skipped',
      cell: (element: Obligation) => `${element.skipped ? 'Yes' : 'No'}`,
    },
    {
      columnDef: 'passed',
      header: 'Passed',
      cell: (element: Obligation) => `${element.passed ? 'Yes' : 'No'}`,
    },
    {
      columnDef: 'earnedPoints',
      header: 'Earned points',
      cell: (element: Obligation) => `${element.earnedPoints + '/' + element.maxPoints}`,
    },
    {
      columnDef: 'corrigible',
      header: 'Corrigible',
      cell: (element: Obligation) => `${element.corrigible ? 'Yes' : 'No'}`,
    }
  ];
  dataSource: Obligation[] = [];
  displayedColumns = this.columns.map(c => c.columnDef);
  subjectId: any = '';
  skipped: boolean = false;
  earnedPoints: number = 0.0;
  created: CreatedObligation;

  constructor(private route: ActivatedRoute, private router: Router, private service: ObligationService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.subjectId = this.route.snapshot.paramMap.get('subjectId');
    if (this.subjectId == null) {
      this.subjectId = '';
    }

    this.loadData();
  }

  loadData() {
    this.service.getObligationsForSubject(this.subjectId).subscribe(
      data => {
        this.dataSource = data;
      }
    );
  }

  openSnackBar(msg: string) {
    this.snackBar.open(msg, 'x', {
      duration: 3000,
      verticalPosition: 'top',
      panelClass: 'panel-snackbar'
    });
  }

  add() {
    this.dialog.open(AddObligationDialogComponent, {
      data: { obligation: this.created },
      autoFocus: false
    }).afterClosed().subscribe(result => {
      if (result != undefined) {
        this.created = {
          name: result.name,
          dateAndTime: result.dateAndTime,
          studyStartDate: result.studyStartDate,
          studyEndDate: result.studyEndDate,
          studyHours: result.studyHours,
          priority: result.priority,
          obligationType: result.obligationType,
          maxPoints: result.maxPoints,
          corrigible: result.corrigible,
          subjectId: this.subjectId
        }

        this.service.addObligation(this.created).subscribe(
          data => {
            this.loadData();
            this.openSnackBar(data);
          },
        )
      }
    });
  }

  finish(row: Obligation) {
    this.dialog.open(FinishObligationDialogComponent, {
      data: { skipped: this.skipped, earnedPoints: this.earnedPoints },
      autoFocus: false
    }).afterClosed().subscribe(result => {
      if (result != undefined) {
        this.skipped = result.skipped;
        this.earnedPoints = result.earnedPoints;
        let finished: FinishedObligation = {
          id: row.id,
          skipped: this.skipped,
          earnedPoints: this.earnedPoints
        }

        this.service.finishObligation(finished).subscribe(
          data => {
            this.loadData();
            this.openSnackBar(data);
          },
        )
      }
      this.skipped = false;
      this.earnedPoints = 0.0;
    });
  }

  back() {
    this.router.navigate(['/subjects']);
  }
}
