import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { ObligationService } from '../services/obligation.service';

export interface Obligation {
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
      columnDef: 'obligationType',
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
      cell: (element: Obligation) => `${
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
  subject: any = '';

  constructor(private route: ActivatedRoute, private router: Router, private service: ObligationService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.subject = this.route.snapshot.paramMap.get('subject');
    if (this.subject == null) {
      this.subject = '';
    }

    this.loadData();
  }

  loadData() {
    this.service.getObligationsForSubject(this.subject).subscribe(
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
    
  }

  finish(row: any) {
    
  }

  back() {
    this.router.navigate(['/subjects']);
  }
}
