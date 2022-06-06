import { Component, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { SubjectService } from '../services/subject.service';
import { AddSubjectDialogComponent } from './add-subject-dialog/add-subject-dialog.component';

export interface Subject {
  name: string;
  earnedPoints: number;
  finished: boolean;
  passed: boolean;
  grade: number;
}

const SUBJECTS: Subject[] = [
  {
    name: "Machine learning",
    earnedPoints: 0.0,
    finished: false,
    passed: false,
    grade: 0
  }
];

@Component({
  selector: 'app-subjects',
  templateUrl: './subjects.component.html',
  styleUrls: ['./subjects.component.css']
})
export class SubjectsComponent implements OnInit {
  columns = [
    {
      columnDef: 'name',
      header: 'Name',
      cell: (element: Subject) => `${element.name}`,
    },
    {
      columnDef: 'earnedPoints',
      header: 'Earned points',
      cell: (element: Subject) => `${element.earnedPoints}`,
    },
    {
      columnDef: 'finished',
      header: 'Finished',
      cell: (element: Subject) => `${element.finished}`,
    },
    {
      columnDef: 'passed',
      header: 'Passed',
      cell: (element: Subject) => `${element.passed}`,
    },
    {
      columnDef: 'grade',
      header: 'Grade',
      cell: (element: Subject) => `${element.grade}`,
    }
  ];
  dataSource: Subject[] = [];
  displayedColumns = this.columns.map(c => c.columnDef);
  username: any = '';
  name: string = '';

  constructor(private router: Router, private service: SubjectService, public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username');
    if (this.username == null) {
      this.username = '';
    }

    this.loadData();
  }

  loadData() {
    this.service.getSubjectsForStudent(this.username).subscribe(
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
    this.dialog.open(AddSubjectDialogComponent, {
      data: { name: this.name }
    }).afterClosed().subscribe(result => {
      if (result != undefined) {
        this.name = result;
        this.service.addSubject(this.username, this.name).subscribe(
          data => {
            this.loadData();
            this.openSnackBar(data);
          },
        )
      }
      this.name = '';
    });
  }

  obligations() {
    this.router.navigate(['/obligations']);
  }

  back() {
    this.router.navigate(['/student']);
  }
}
