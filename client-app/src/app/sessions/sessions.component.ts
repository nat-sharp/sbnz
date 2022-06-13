import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CalendarService } from '../services/calendar.service';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

export interface Session {
  id: number,
  dateAndTime: string,
  durationInHours: number,
  obligationname: string,
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
    },
    {
      columnDef: 'obligationname',
      header: 'Obligation',
      cell: (element: Session) => `${element.obligationname}`,
    },
    {
      columnDef: 'studyHours',
      header: "Duration in hours",
      cell: (element: Session) => `${element.durationInHours}h`,
    },
    {
      columnDef: 'priotity',
      header: "Priority",
      cell: (element: Session) => `${element.priority}`,
    }
  ]

  dataSource: Session[] = [];
  displayedColumns = this.columns.map(c => c.columnDef);
  username: any = '';

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
    let currentSessions : any[]= [];
    console.log()
    this.calendarService.getSessionsForStudent(this.username).subscribe(
      data => {
        currentSessions = data;
      }, error => {
        
        
        this.calendarService.createSessionsForStudent(this.username).subscribe(
          data => {
            currentSessions = data;
            this.dataSource = data;
            return
          }, error => {
            console.log(error.error);
            this.openSnackBar("Error while creating sessions")
          }
        )
        
      }
    )
    
      
    

    this.dataSource = currentSessions;
  }


  back() {
    this.router.navigate(['/student']);
  }

  openSnackBar(msg: string) {
    this.snackBar.open(msg, 'x', {
      duration: 3000,
      verticalPosition: 'top',
      panelClass: 'panel-snackbar'
    });
  }

}
