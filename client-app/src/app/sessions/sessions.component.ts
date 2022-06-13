import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CalendarService } from '../services/calendar.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

export interface Session {
  id: number,
  dateAndTime: string,
  durationInHours: number,
  obligationName: string,
  priority: number  //TODO MOZDA BOJA KAO PRIORITET
}

const SESSIONS: Session[] = [];

@Component({
  selector: 'app-sessions',
  templateUrl: './sessions.component.html',
  styleUrls: ['./sessions.component.css']
})
export class SessionsComponent implements OnInit {

  columns = [
    {
      columnDef: 'dateAndTime',
      header: 'Date',
      cell: (element: Session) => `${element.dateAndTime == null ? '/' :
        element.dateAndTime.split('T')[0].split('-')[2] + '.' +
        element.dateAndTime.split('T')[0].split('-')[1] + '.' +
        element.dateAndTime.split('T')[0].split('-')[0] + '. ' 
        }`,
    },,
    {
      columnDef: 'obligationName',
      header: 'Obligation',
      cell: (element: Session) => `${element.obligationName}`,
    },
    {
      columnDef: 'studyHours',
      header: "Duration in hours",
      cell: (element: Session) => `${element.durationInHours} + "h"`,
    },
    {
      columnDef: 'priotity',
      header: "Priority",
      cell: (element: Session) => `${element.priority}`
    }
  ]

  dataSource: Session[] = [];
  displayedColumns = this.columns.map(c => c?.columnDef);
  username: any = "";

  constructor(private router: Router, 
    private calendarService:  CalendarService,
    public dialog: MatDialog, private snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.username = sessionStorage.getItem('username');
    if (this.username == null) {
      this.username = '';
    }

    this.loadData();
  }


  loadData() {
    this.calendarService.createSessionsForStudent(this.username).subscribe(
      data => {
        this.dataSource = data;
      }
    )
  }

  openSnackBar(msg: string) {
    this.snackBar.open(msg, 'x', {
      duration: 3000,
      verticalPosition: 'top',
      panelClass: 'panel-snackbar'
    });
  }

}
