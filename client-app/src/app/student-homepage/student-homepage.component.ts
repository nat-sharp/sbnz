import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CalendarService } from '../services/calendar.service';

@Component({
  selector: 'app-student-homepage',
  templateUrl: './student-homepage.component.html',
  styleUrls: ['./student-homepage.component.css']
})
export class StudentHomepageComponent implements OnInit {

  statusPresent = false;
  status:string = "";
  username: any = '';
  constructor(private router: Router,
    private service: CalendarService) { }


  ngOnInit(): void { 
    this.username = sessionStorage.getItem('username');
    if (this.username == null) {
      this.username = '';
    }
  }

  calendar() {
    this.router.navigate(['/sessions']);
  }

  subjects() {
    this.router.navigate(['/subjects']);
  }

  showStatus() {
    this.service.getStudentStatus(this.username).subscribe(
      data => {
        this.status = data;
        this.statusPresent = true;
      }
    )
  }

  logOut() {
    this.router.navigate(['']);
    sessionStorage.setItem('username', '');
  }
}